

package com.movix.adminSP.model.dto;

import com.movix.shared.Operador;

//Clase abstracta de ServicioPrecio
public abstract class ServicePriceDTO {

	protected int id;
	protected String tipo;
	protected String servicio;
	protected double precio;
	protected Operador operador;
	
	public enum Estrategia{
		ASCENDENTE,DESCENDENTE,FULLPRICE
	}
	
	
	public enum Estado {
	    TESTING, ACTIVO , INACTIVO 
	}
	
	protected Estado estado;
	
	
	public ServicePriceDTO(){
		
	}
	
	public ServicePriceDTO(int id, String tipo, String servicio, Integer precio , Operador operador){
		this.id=id;
		this.tipo = tipo;
		this.servicio = servicio;
		this.precio = precio;
		this.estado = Estado.TESTING;
		this.operador=operador;
		
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public double getPrecio() {
		return precio;
	}

	
	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public void activar(){
		this.estado= Estado.ACTIVO;
	}
	
	public void desactivar(){
		this.estado= Estado.INACTIVO;
	}
	
	public void probar(){
		this.estado= Estado.TESTING;
	}
	
	public Estado getEstado(){
		return this.estado;
	}	
	
	
}
