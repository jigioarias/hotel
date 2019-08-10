package co.com.hoteles.turin.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
@NamedQuery(name="Usuario.findLogin", query="SELECT u FROM Usuario u where u.id=:login")
@NamedQuery(name="Usuario.findClave", query="SELECT u FROM Usuario u where u.id=:login and u.clave=:clave")


public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String activado;

	private String clave;

	private String correo;
	
	private int hotel;
	

	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;

	
	private String rol;
	
	private String usuarioCreacion;

	public Usuario() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActivado() {
		return this.activado;
	}

	public void setActivado(String activado) {
		this.activado = activado;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	

	public Usuario(String id) {
		this.id = id;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public int getHotel() {
		return hotel;
	}

	public void setHotel(int hotel) {
		this.hotel = hotel;
	}

	
}