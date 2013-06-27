package com.movix.AdminScheduling.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.LoadingCache;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.movix.AdminScheduling.model.dto.EventDTO;
import com.movix.shared.Operador;
import com.movixla.service.scheduling.common.ISchedulingService;
import com.movixla.service.scheduling.common.SchedulingEntryPro;
import com.movixla.shared.util.ServiceUtil;

public class EventCache {

	final private String everythingKey = "Everything";
	final private String backupKey = "Old";
	LoadingCache<String, List<EventDTO>> cache;
	private Date lastUpdate;
	private String[] daysPatterns = {"LU", "MA", "MI", "JU", "VI", "SA", "DO"};
	
	public void init(){
	}
	
	public void start() throws ExecutionException{
		
	}

	public List<EventDTO> getEvents(){
		String a1 = "LU,MA,SA,DO";
		String a2 = "LU,MA,MI,VI,DO";
		String a3 = "LU,MA,VI,SA,DO";
		System.out.println(a1 + "=>" + getShortDays(a1));
		System.out.println(a2 + "=>" + getShortDays(a2));
		System.out.println(a3 + "=>" + getShortDays(a3));
		List<EventDTO> events = new ArrayList<EventDTO>();
		ISchedulingService schedulingService = ServiceUtil.getService(ISchedulingService.class,
	            "http://scheduling-service/scheduling/rest/ejb");
		List<SchedulingEntryPro> schedulingEntries = schedulingService.getSchedulingEntriesPro();
		
		SchedulingEntryPro last = new SchedulingEntryPro();
		String hourSchedule = "", lastDay = "", lastServicePrice = "";
		EventDTO newEvent = new EventDTO();
		BiMap<String, String> daysMap = HashBiMap.create(7);
		boolean first = true;
		for(SchedulingEntryPro entry : schedulingEntries){
			lastServicePrice = last.getServicePrice() == null ? "Todos" : last.getServicePrice();
			if(first){
				last = entry;
				lastDay = entry.getDayPattern();
				first = false;
			}
			if(mustCreateEvent(last.getKey(), entry.getKey())){
				if(!daysMap.containsValue(hourSchedule)){
					daysMap.put(lastDay, hourSchedule);
				} else {
					String oldKey = daysMap.inverse().get(hourSchedule);
					String newKey = oldKey + "," + lastDay;
					newKey = getShortDays(newKey);
					daysMap.remove(oldKey);
					daysMap.put(newKey, hourSchedule);
				}
				newEvent.setDias(daysMap);
				newEvent.setKey(last.getKey());
				newEvent.setOperador(Operador.getOperadorPorIdBD(last.getOperator()));
				newEvent.setProducto(last.getProduct());
				newEvent.setSp(lastServicePrice);
				newEvent.setTipo(last.getKey().substring(last.getKey().lastIndexOf("-") + 1));
				events.add(newEvent);
				lastDay = entry.getDayPattern();
				hourSchedule = entry.getStart() + " - " + entry.getEnd();
				newEvent = new EventDTO();
				daysMap = HashBiMap.create(7);
			} else {
				String dayPattern = entry.getDayPattern();
				if(Arrays.asList(daysPatterns).contains(dayPattern)){
					if(dayPattern.equals(lastDay)){
						hourSchedule += entry.getStart() + " - " + entry.getEnd();
					} else {
						if(!daysMap.containsValue(hourSchedule)){
							daysMap.put(lastDay, hourSchedule);
						} else {
							String oldKey = daysMap.inverse().get(hourSchedule);
							String newKey = oldKey + "," + lastDay;
							daysMap.remove(oldKey);
							daysMap.put(newKey, hourSchedule);
						}
						hourSchedule = entry.getStart() + " - " + entry.getEnd();
					}
					lastDay = dayPattern;
				} else {
					int daysOff = Integer.parseInt(dayPattern.substring(1,2));
					int daysPassed = hourSchedule.length() - hourSchedule.replace("|", "").length();
					String schedule = entry.getStart() + " - " + entry.getEnd();
					if(daysOff > daysPassed){
						schedule = " | " + schedule;
					}
					hourSchedule += ", " + schedule;
				}
			}
			last = entry;
		}
		lastDay = last.getDayPattern();
		if(!daysMap.containsValue(hourSchedule)){
			daysMap.put(lastDay, hourSchedule);
		} else {
			String oldKey = daysMap.inverse().get(hourSchedule);
			String newKey = oldKey + "," + lastDay;
			newKey = getShortDays(newKey);
			daysMap.remove(oldKey);
			daysMap.put(newKey, hourSchedule);
		}
		hourSchedule = last.getStart() + " - " + last.getEnd();
		newEvent.setDias(daysMap);
		newEvent.setKey(last.getKey());
		newEvent.setOperador(Operador.getOperadorPorIdBD(last.getOperator()));
		newEvent.setProducto(last.getProduct());
		newEvent.setSp(last.getServicePrice());
		newEvent.setTipo(last.getKey().substring(last.getKey().lastIndexOf("-") + 1));
		events.add(newEvent);
		return events;
	}

	public List<EventDTO> get(String key) throws ExecutionException{
		return cache.get(key);
	}

	public Date getLastUpdate(){
		return lastUpdate;
	}

	public List<EventDTO> getAll() throws ExecutionException{
		return cache.get(everythingKey);
	}

	//Funci�n que refresca el cache y pone el antiguo en la variable old para futura referencia.
	public void refreshEventCache(){
		try {
			cache.put(backupKey, cache.get(everythingKey));
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		lastUpdate = new Date();
		cache.refresh(everythingKey);
	}
	
	private boolean mustCreateEvent(String lastKey, String entryKey){
		boolean different = false;
		String[] lastKeyTokens = lastKey.split(":");
		String[] entryKeyTokens = entryKey.split(":");
		for(int i = 0; i < lastKeyTokens.length; i++){
			if(i == lastKeyTokens.length - 1){
				lastKeyTokens[i] = lastKeyTokens[i].replaceAll("[0-9]","");
				entryKeyTokens[i] = entryKeyTokens[i].replaceAll("[0-9]","");
			}
			if(!lastKeyTokens[i].equals(entryKeyTokens[i])){
				different = true;
			}
		}
		return different;
	}
	
	private String getShortDays(String days){
		if(days.length() == 0){
			return "";
		}
		String shortDays = "";
		int i = 0, initial = -1;
		String[] daysTokens = days.split(",");
		for(i = 0; i < daysPatterns.length; i++){
			boolean exists = false;
			for(int j = 0; j < daysTokens.length; j++){
				if(daysTokens[j].equals(daysPatterns[i])){
					exists = true;
					break;
				}
			}
			if(exists){
				if(initial == -1){
					initial = i;					
				}
			}
			if(initial >= 0 && !exists){
				if(i - initial == 1){
					shortDays += daysPatterns[i - 1];
				} else if(i - initial == 2){
					shortDays += daysPatterns[i - 2] + ", " + daysPatterns[i - 1]; 
				} else {
					shortDays += daysPatterns[initial] + " a " + daysPatterns[i - 1];
				}
				shortDays += ", ";
				initial = -1;
			}
		}
		if(initial >= 0){
			if(i - initial == 1){
				shortDays += daysPatterns[i - 1];
			} else if(i - initial == 2){
				shortDays += daysPatterns[i - 2] + ", " + daysPatterns[i - 1]; 
			} else {
				shortDays += daysPatterns[initial] + " a " + daysPatterns[i - 1];
			}
			shortDays += ", ";
		}
		return shortDays.substring(0, shortDays.length() - 2);
	}
}
