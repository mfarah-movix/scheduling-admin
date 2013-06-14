package com.movix.adminSP.model.dto;

import com.movix.shared.Operador;


public class BillServicePriceDTO extends ServicePriceDTO {

	private String args;
	private boolean cache;
	private Estrategia estrategia;
	//TODO : Canal con CanalDTO
	private String canal;
	
	
	public BillServicePriceDTO(){
		super();
	}
	
	public BillServicePriceDTO(int id, Operador operador , String servicio, double precio,Estrategia estrategia, String canal, String args,boolean cache){
		super();
		this.id=id;
		this.tipo="Billing";
		this.operador=operador;
		this.servicio=servicio;
		this.precio=precio;
		this.estado= Estado.TESTING;
		this.args= args;
		this.estrategia= estrategia;
		//TODO : Canal con CanalDTO
		this.canal = canal;
		this.cache=cache;
	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	public Estrategia getEstrategia() {
		return estrategia;
	}

	public void setEstrategia(Estrategia estrategia) {
		this.estrategia = estrategia;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public boolean hasCache() {
		return cache;
	}

	public void setCache(boolean cache) {
		this.cache = cache;
	}
	
		
}
