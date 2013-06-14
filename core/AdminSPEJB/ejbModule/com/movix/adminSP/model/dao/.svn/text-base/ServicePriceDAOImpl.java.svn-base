package com.movix.adminSP.model.dao;

import java.util.ArrayList;
import java.util.List;

import com.movix.adminSP.model.dto.BillServicePriceDTO;
import com.movix.adminSP.model.dto.EnvServicePriceDTO;
import com.movix.adminSP.model.dto.EnvServicePriceDTO.TipoEnv;
import com.movix.adminSP.model.dto.RecServicePriceDTO;
import com.movix.adminSP.model.dto.RecServicePriceDTO.TipoRec;
import com.movix.adminSP.model.dto.ServicePriceDTO;
import com.movix.adminSP.model.dto.ServicePriceDTO.Estrategia;
import com.movix.shared.Operador;
import com.movixla.service.sp.client.SPsClient;
import com.movixla.service.sp.common.SPEntry;

public class ServicePriceDAOImpl implements ServicePriceDAO {

	@Override
	public int agregar(ServicePriceDTO sp) {
		return 0;
		// TODO : Pedir función

	}

	//TODO: notificar ventas default TRUE
	@Override
	public void actualizar(ServicePriceDTO sp) {
		// TODO : Pedir función

	}
	

	@Override
	public List<ServicePriceDTO> findALL() {
		SPsClient spInstance = SPsClient.getInstance();
		List<SPEntry> spEntries = spInstance.getServicePrices();
		
		List<ServicePriceDTO> sps = new ArrayList<ServicePriceDTO>();
		
		//Se asume que existe la función getId y getCache en SPEntry (NO existe al momento de implementar) TODO: PEDIR!
		for(SPEntry spEntry : spEntries){
			if(spEntry.getType().equals("rec")){
				
				String[] servicePrice = spEntry.getChannel().split("/");
				
				TipoRec tipoRec;
				if(spEntry.getPrice().equals("SMS"))
					tipoRec= TipoRec.SMS;
				if(spEntry.getPrice().equals("MMS"))
					tipoRec=TipoRec.MMS;
				
				sps.add(new RecServicePriceDTO(spEntry.getId(),Operador.getOperadorPorIdBD(spEntry.getOperator_id()),tipoRec,servicePrice[0],
												Double.parseDouble(servicePrice[1]),Integer.parseInt(spEntry.getService())));
				
			}
			
			if(spEntry.getType().equals("bill")){
				
				//Ver estrategias en bd
				Estrategia estrategia;
				if(spEntry.getStrategy().equals("ASCENDENTE"))
					estrategia= Estrategia.ASCENDENTE;
				if(spEntry.getStrategy().equals("DESCENDENTE"))
					estrategia= Estrategia.DESCENDENTE;
				if(spEntry.getStrategy().equals(""))
					estrategia= Estrategia.FULLPRICE;
				
				sps.add(new BillServicePriceDTO(spEntry.getId(),spEntry.getService(),
												Double.parseDouble(spEntry.getPrice()),estrategia,spEntry.getChannel(),
												spEntry.getArgs(),spEntry.getCache()));
				
			}
			
			if(spEntry.getType().equals("smswp")||spEntry.getType().equals("web-push")||spEntry.getType().equals("sms")||spEntry.getType().equals("mms")||spEntry.getType().equals("vsms")){
				
				Estrategia estrategia;
				if(spEntry.getStrategy().equals("ASCENDENTE"))
					estrategia= Estrategia.ASCENDENTE;
				if(spEntry.getStrategy().equals("DESCENDENTE"))
					estrategia= Estrategia.DESCENDENTE;
				if(spEntry.getStrategy().equals(""))
					estrategia= Estrategia.FULLPRICE;
				
				TipoEnv tipoEnv;
				if(spEntry.getType().equals("smswp")||spEntry.getType().equals("web-push")||spEntry.getType().equals("sms"))
					tipoEnv = TipoEnv.SMSWP;
				if(spEntry.getType().equals("vsms"))
					tipoEnv = TipoEnv.VSMS;
				if(spEntry.getType().equals("mms"))
					tipoEnv = TipoEnv.MMS;
				
				sps.add(new EnvServicePriceDTO(spEntry.getId(),tipoEnv,spEntry.getService(),
												Double.parseDouble(spEntry.getPrice()),estrategia,
												spEntry.getChannel(),spEntry.getArgs(),spEntry.getCache()));
				
			}
		}
		
		return sps;
		
	}

	

}
