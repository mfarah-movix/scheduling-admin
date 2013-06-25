package com.movix.AdminScheduling.model.dto;

import java.util.Map;

import com.movix.shared.Operador;

public class EventDTO {
	private int id;
	private Operador operador;
	private String producto;
	private int productId;
	private String sp;
	private String tipo;
	private String key;
	Map<String, String> dias;

	public EventDTO(){
		
	}
	public EventDTO(int id, Operador operador, String producto, int productId,
			String sp, String tipo, String key, Map<String, String> dias, String horario) {
		super();
		this.id = id;
		this.operador = operador;
		this.producto = producto;
		this.productId = productId;
		this.sp = sp;
		this.tipo = tipo;
		this.key = key;
		this.dias = dias;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Operador getOperador() {
		return operador;
	}
	public void setOperador(Operador operador) {
		this.operador = operador;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getSp() {
		return sp;
	}
	public void setSp(String sp) {
		this.sp = sp;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Map<String, String> getDias() {
		return dias;
	}
	public void setDias(Map<String, String> dias) {
		this.dias = dias;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventDTO other = (EventDTO) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
}
