package co.com.hoteles.turin.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="clientes")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
@NamedQuery(name="Cliente.findDocumento", query="SELECT c FROM Cliente c where documento=:documento")
@NamedQuery(name="Cliente.findId", query="SELECT c FROM Cliente c where id=:id")
@NamedQuery(name="Cliente.findExtranjeros", query="SELECT c FROM Cliente c where c.extranjero=:extranjero and c.fechaRegistro>=:fechaInicio and c.fechaRegistro<=:fechaFin and c.hotel=:hotel")

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "tipoDocumento")
	private String tipoDocumento;
	
	@Column(name = "documento")
	private String documento;
	
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	private int hotel;

	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "celular")
	private String celular;
	
	@Column(name = "fechaRegistro")
	private Date fechaRegistro;
	
		
	@Column(name = "fechaNacimiento")
	private Date fechaNacimiento;
	
	@Column(name = "correo")
	private String correo;
	
	private String usuarioIngreso;


	public Cliente(String tipoDocumento, String documento, String nombre, String celular, Date fechaRegistro,
			Date fechaNacimiento, String correo) {
		super();
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.nombre = nombre;
		this.celular = celular;
		this.fechaRegistro = fechaRegistro;
		this.fechaNacimiento = fechaNacimiento;
		this.correo = correo;
	}

	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	private String extranjero;

  public Cliente() {}

public String getExtranjero() {
	return extranjero;
}

public void setExtranjero(String extranjero) {
	this.extranjero = extranjero;
}

public String getUsuarioIngreso() {
	return usuarioIngreso;
}

public void setUsuarioUIngreso(String usuarioIngreso) {
	this.usuarioIngreso = usuarioIngreso;
}

public int getHotel() {
	return hotel;
}

public void setHotel(int hotel) {
	this.hotel = hotel;
}	
	
}
