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
		List<EventDTO> events = new ArrayList<EventDTO>();
		ISchedulingService schedulingService = ServiceUtil.getService(ISchedulingService.class,
	            "http://scheduling-service.movixla.com/scheduling/rest/ejb");
		List<SchedulingEntryPro> schedulingEntries = schedulingService.getSchedulingEntriesPro();
//		List<SchedulingEntryPro> schedulingEntries = new ArrayList<SchedulingEntryPro>();
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d0-h0-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "LU", "15:00", "17:00", "-1"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d0-h1-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "17:00", "19:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d0-h0-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d0-h2-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "19:00", "21:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d0-h1-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d0-h3-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "21:00", "23:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d0-h2-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d0-h4-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "23:00", "01:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d0-h3-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d0-h5-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+1d", "01:00", "02:30", "SUS:29:sus3_TigoSV_77321/0.19:_:d0-h4-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d0-h6-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+1d", "02:30", "03:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d0-h5-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d1-h0-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "MA", "15:00", "17:00", "-1"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d1-h1-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "17:00", "19:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d1-h0-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d1-h2-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "19:00", "21:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d1-h1-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d1-h3-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "21:00", "23:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d1-h2-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d1-h4-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "23:00", "01:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d1-h3-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d1-h5-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+1d", "01:00", "02:30", "SUS:29:sus3_TigoSV_77321/0.19:_:d1-h4-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d1-h6-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+1d", "02:30", "03:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d1-h5-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d2-h0-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "MI", "15:00", "17:00", "-1"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d2-h1-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "17:00", "19:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d2-h0-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d2-h2-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "19:00", "21:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d2-h1-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d2-h3-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "21:00", "23:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d2-h2-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d2-h4-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "23:00", "01:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d2-h3-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d2-h5-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+1d", "01:00", "02:30", "SUS:29:sus3_TigoSV_77321/0.19:_:d2-h4-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d2-h6-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+1d", "02:30", "03:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d2-h5-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d3-h0-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "JU", "15:00", "17:00", "-1"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d3-h1-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "17:00", "19:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d3-h0-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d3-h2-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "19:00", "21:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d3-h1-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d3-h3-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "21:00", "23:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d3-h2-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d3-h4-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "23:00", "01:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d3-h3-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d3-h5-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+1d", "01:00", "02:30", "SUS:29:sus3_TigoSV_77321/0.19:_:d3-h4-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d3-h6-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+1d", "02:30", "03:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d3-h5-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d4-h0-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "VI", "15:00", "17:00", "-1"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d4-h1-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "17:00", "19:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d4-h0-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d4-h2-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "19:00", "21:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d4-h1-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d4-h3-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "21:00", "23:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d4-h2-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d4-h4-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "23:00", "01:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d4-h3-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d4-h5-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+1d", "01:00", "02:30", "SUS:29:sus3_TigoSV_77321/0.19:_:d4-h4-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d4-h6-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+1d", "02:30", "03:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d4-h5-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d5-h0-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "SA", "15:00", "17:00", "-1"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d5-h1-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "17:00", "19:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d5-h0-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d5-h2-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "19:00", "21:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d5-h1-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d5-h3-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "21:00", "23:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d5-h2-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d5-h4-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "23:00", "01:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d5-h3-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d5-h5-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+1d", "01:00", "02:30", "SUS:29:sus3_TigoSV_77321/0.19:_:d5-h4-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d5-h6-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+1d", "02:30", "03:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d5-h5-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d6-h0-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "DO", "15:00", "17:00", "-1"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d6-h1-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "17:00", "19:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d6-h0-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d6-h2-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "19:00", "21:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d6-h1-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d6-h3-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "21:00", "23:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d6-h2-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d6-h4-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+0d", "23:00", "01:00", "SUS:29:sus3_TigoSV_77321/0.19:_:d6-h3-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:29:sus3_TigoSV_77321/0.19:_:d6-h5-SENDING", Type.SENDING, "SUS", 29, "sus3_TigoSV_77321/0.19", "NULL", "NULL", "+1d", "01:00", "02:30", "SUS:29:sus3_TigoSV_77321/0.19:_:d6-h4-SENDING"));
//		schedulingEntries.add(new SchedulingEntryPro("SUS:8:sus3_MovPE_7700_cobro_SAT_MARGINAL/1.0:_:d6-h3-CHARGE", Type.CHARGE, "SUS", 29, "sus3_MovPE_7700_cobro_SAT_MARGINAL/1.0", "NULL", "NULL", "LU", "01:15", "02:15", "SUS:8:sus3_MovPE_7700_cobro_SAT_MARGINAL/1.0:_:d6-h2-CHARGE"));
		
		SchedulingEntryPro last = new SchedulingEntryPro();
		String hourSchedule = "", lastDay = "";
		if(!schedulingEntries.isEmpty()){
			last = schedulingEntries.get(0);
			lastDay = last.getDayPattern();
		}
		EventDTO newEvent = new EventDTO();
		BiMap<String, String> daysMap = HashBiMap.create(7);
		for(SchedulingEntryPro entry : schedulingEntries){
			if(!last.getServicePrice().equals(entry.getServicePrice())){
				if(!daysMap.containsValue(hourSchedule)){
					daysMap.put(lastDay, hourSchedule);
				} else {
					String oldKey = daysMap.inverse().get(hourSchedule);
					String newKey = oldKey + ", " + lastDay;
					daysMap.remove(oldKey);
					daysMap.put(newKey, hourSchedule);
				}
				newEvent.setDias(daysMap);
				newEvent.setKey(last.getKey());
				newEvent.setOperador(Operador.getOperadorPorIdBD(last.getOperator()));
				newEvent.setProducto(last.getProduct());
				newEvent.setSp(last.getServicePrice());
				newEvent.setTipo(last.getKey().substring(last.getKey().lastIndexOf("-") + 1));
				events.add(newEvent);
				lastDay = last.getDayPattern();
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
							String newKey = oldKey + ", " + lastDay;
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
			String newKey = oldKey + ", " + lastDay;
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
}
