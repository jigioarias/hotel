package co.com.hoteles.turin.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
@Entity
@NamedQuery(name="Servicio.findAll", query="SELECT c FROM Servicio c")
@NamedQuery(name="Servicio.findXNombre", query="SELECT c FROM Servicio c where c.nombre=:nombre")

public class Servicio  implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	private String nombre;
	private int valor;
	private String estado;
	
	 transient int cantidad; // not persistent because of transient
	
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
