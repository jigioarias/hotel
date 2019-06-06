package co.com.hoteles.turin.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name="Pais.findAll", query="SELECT c FROM Pais c")
@Table(name="pais")
public class Pais  implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	@Id
	private String codigo;
	private String nombre;
	private String gentilicio;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getGentilicio() {
		return gentilicio;
	}
	public void setGentilicio(String gentilicio) {
		this.gentilicio = gentilicio;
	}



}
