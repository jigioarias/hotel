package co.com.hoteles.turin.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the facturas database table.
 * 
 */
@Entity
@Table(name="facturas")
@NamedQuery(name="Factura.findAll", query="SELECT f FROM Factura f")
@NamedQuery(name="Factura.findDocumento", query="SELECT f FROM Factura f where f.numero=:documento")

public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int numero;

	private String cliente;

	private String direccion;

	private String direccionCliente;

	private String documentoCliente;

	private Date fecha;

	private int hotel;

	private String nit;
	
    private Date fechaEntrada;
    
    private Date fechaSalida;
    
    private int checking;
	private String razonSocial;

	private String resolucion;

	private String telefono;

	private String telefonoCliente;

	private String tipodocumentoCliente;

	private int total;

	public Factura() {
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDireccionCliente() {
		return this.direccionCliente;
	}

	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public String getDocumentoCliente() {
		return this.documentoCliente;
	}

	public void setDocumentoCliente(String documentoCliente) {
		this.documentoCliente = documentoCliente;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getHotel() {
		return this.hotel;
	}

	public void setHotel(int hotel) {
		this.hotel = hotel;
	}

	public String getNit() {
		return this.nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getResolucion() {
		return this.resolucion;
	}

	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTelefonoCliente() {
		return this.telefonoCliente;
	}

	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

	public String getTipodocumentoCliente() {
		return this.tipodocumentoCliente;
	}

	public void setTipodocumentoCliente(String tipodocumentoCliente) {
		this.tipodocumentoCliente = tipodocumentoCliente;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public int getChecking() {
		return checking;
	}

	public void setChecking(int checking) {
		this.checking = checking;
	}

}