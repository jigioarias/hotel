package co.com.hoteles.turin.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the hoteles database table.
 * 
 */
@Entity
@Table(name="hoteles")
@NamedQuery(name="Hotel.findAll", query="SELECT h FROM Hotel h")
public class Hotel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigo;

	private int altitud;

	private String correo;

	private String direccion;

	private int latitud;

	private String nit;

	private String nomgre;

	private String redessociales;

	private String telefono;

	public Hotel() {
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getAltitud() {
		return this.altitud;
	}

	public void setAltitud(int altitud) {
		this.altitud = altitud;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getLatitud() {
		return this.latitud;
	}

	public void setLatitud(int latitud) {
		this.latitud = latitud;
	}

	public String getNit() {
		return this.nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getNomgre() {
		return this.nomgre;
	}

	public void setNomgre(String nomgre) {
		this.nomgre = nomgre;
	}

	public String getRedessociales() {
		return this.redessociales;
	}

	public void setRedessociales(String redessociales) {
		this.redessociales = redessociales;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}