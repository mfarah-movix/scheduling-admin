package com.movix.adminSP.cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.movix.adminSP.model.dao.ServicePriceDAO;
import com.movix.adminSP.model.dao.ServicePriceDAOFactory;
import com.movix.adminSP.model.dto.BillServicePriceDTO;
import com.movix.adminSP.model.dto.EnvServicePriceDTO;
import com.movix.adminSP.model.dto.RecServicePriceDTO;
import com.movix.adminSP.model.dto.ServicePriceDTO;

public class SPCache {

	LoadingCache<String, List<ServicePriceDTO>> cache;

	private ServicePriceDAO servicepriceDAO;
  
	private Date lastUpdate;
	
	public void init(){
	
		cache = CacheBuilder.newBuilder().
				maximumSize(2).
				expireAfterAccess(1, TimeUnit.MINUTES).
				removalListener(
					new RemovalListener<String, List<ServicePriceDTO>>(){
	                                public void onRemoval(RemovalNotification<String, List<ServicePriceDTO>> notification) {
	                                    System.out.println("Se removera el arreglo");
	                                    
	                                    if(notification.getCause()==RemovalCause.EXPIRED)
	                                    	System.out.println("La siguiente entrada expiro: "+notification.getKey());
	                                    else
	                                    	System.out.println("La siguiente entrada fue borrada intencionalmente: "+notification.getKey());
	                                    
											refreshCacheSP();
	                                }}
				).
				build(
					new CacheLoader<String, List<ServicePriceDTO>>() {
						public List<ServicePriceDTO> load(String key) { // TODO: no checked exception
								servicepriceDAO = ServicePriceDAOFactory.getServicePriceDAO();
								return servicepriceDAO.findALL();
						}
				});
	}
	
	//TODO: revisar
	public void start() throws ExecutionException{
		lastUpdate = new Date();
		cache.get("Actual");
	}

	public List<ServicePriceDTO> get(String key) throws ExecutionException{
		return cache.get(key);
	}
	
	public Date getLastUpdate(){
		return lastUpdate;
	}
	
	public List<ServicePriceDTO> getAll() throws ExecutionException{
		return cache.get("Actual");
	}
	
	public List<EnvServicePriceDTO> getAllEnv() throws ExecutionException{
		List<ServicePriceDTO> sps= cache.get("Actual");
		List<EnvServicePriceDTO> envSps = new ArrayList<EnvServicePriceDTO>();
		for(ServicePriceDTO sp : sps){
			if(sp.getClass().equals(EnvServicePriceDTO.class))
				envSps.add((EnvServicePriceDTO) sp);
		}
		return envSps;
	}
	
	public List<BillServicePriceDTO> getAllBill() throws ExecutionException{
		List<ServicePriceDTO> sps= cache.get("Actual");
		List<BillServicePriceDTO> billSps = new ArrayList<BillServicePriceDTO>();
		for(ServicePriceDTO sp : sps){
			if(sp.getClass().equals(BillServicePriceDTO.class))
				billSps.add((BillServicePriceDTO) sp);
		}
		return billSps;
	}
	
	public List<RecServicePriceDTO> getAllRec() throws ExecutionException{
		List<ServicePriceDTO> sps= cache.get("Actual");
		List<RecServicePriceDTO> recSps = new ArrayList<RecServicePriceDTO>();
		for(ServicePriceDTO sp : sps){
			if(sp.getClass().equals(RecServicePriceDTO.class))
				recSps.add((RecServicePriceDTO) sp);
		}
		return recSps;
	}
	
	//Función que refresca el cache y pone el antiguo en la variable old para futura referencia.
	public void refreshCacheSP(){
		try {
			cache.put("Old", cache.get("Actual"));
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lastUpdate = new Date();
		cache.refresh("Actual");
		
	}
	
	//Retorna un sp del cache a partir del id del sp, si no lo encuentra, retorna null.
	public ServicePriceDTO getSP(int id){
		List<ServicePriceDTO> sps = new ArrayList<ServicePriceDTO>();
		try {
			sps = this.get("Actual");
			
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(ServicePriceDTO sp : sps){
			if(sp.getId()==id)
				return sp;
		}
		
		return null;
		
	}
	
	//Retorna un sp del cache a partir de la tupla servicio/precio, si no lo encuentra, retorna null.
	public ServicePriceDTO getSP(String service,int price){
		List<ServicePriceDTO> sps = new ArrayList<ServicePriceDTO>();
		try {
			sps = this.get("Actual");
			
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(ServicePriceDTO sp : sps){
			if(sp.getServicio().equals(service)&&sp.getPrecio()==price)
				return sp;
		}
		
		return null;
		
	}


	public int agregarSP(ServicePriceDTO sp){
		servicepriceDAO = ServicePriceDAOFactory.getServicePriceDAO();
		int idsp =servicepriceDAO.agregar(sp);
		this.refreshCacheSP();
		return idsp;
		
	}
	
	public void actualizarSP(ServicePriceDTO sp){
		servicepriceDAO = ServicePriceDAOFactory.getServicePriceDAO();
		servicepriceDAO.actualizar(sp);
		
		this.refreshCacheSP();
		
	}
	
	
	
}
