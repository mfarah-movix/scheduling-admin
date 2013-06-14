package com.movix.adminSP.model.dto;

import com.movix.shared.Operador;

public class EnvServicePriceDTO extends ServicePriceDTO {


	private String args;
	private boolean cache;
	//TODO : Canal con CanalDTO
	private String canal;
	private Estrategia estrategia;
	
	public enum TipoEnv{
		SMSWP,MMS,VSMS
	}
	
	private TipoEnv tipoEnv;
	
	public EnvServicePriceDTO(){
		super();
	}
	
	public EnvServicePriceDTO(int id,Operador operador,TipoEnv tipo,String servicio,double precio,Estrategia estrategia,String canal,String args,boolean cache){
		super();
		this.id=id;
		this.tipo="Envio";
		this.operador = operador;
		this.servicio=servicio;
		this.precio=precio;
		this.estado= Estado.TESTING;
		this.args= args;
		this.estrategia= estrategia;
		//TODO : Canal con CanalDTO
		this.canal = canal;
		this.tipoEnv=tipo;
		this.cache=cache;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
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

	public TipoEnv getTipoEnv() {
		return tipoEnv;
	}

	public void setTipoEnv(TipoEnv tipoEnv) {
		this.tipoEnv = tipoEnv;
	}
	
	public boolean hasCache() {
		return cache;
	}

	public void setCache(boolean cache) {
		this.cache = cache;
	}
	
}
