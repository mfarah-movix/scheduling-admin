package com.movix.adminSP.model.dto;

import com.movix.shared.Operador;

public class CanalDTO {
	
	//TODO: Revisar si tienen id
	//TODO: Ocupar operador de shared
	private int id;
	private String nombre;
	private Operador operador;
	
	public CanalDTO(){
		
	}
	
	public CanalDTO(int id, String nombre, int operador){
		this.id=id;
		this.nombre=nombre;
		this.operador = Operador.getOperadorPorIdBD(operador);
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Operador getOperador() {
		return operador;
	}


	

}
