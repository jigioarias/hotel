package co.com.hoteles.turin.dtos;

import java.util.Date;
import java.util.List;

import co.com.hoteles.turin.entities.Cliente;
import co.com.hoteles.turin.entities.Habitacion;

public class ReservaDTO {

	private Cliente cliente;
	private List<Habitacion> habitaciones;
	private List<Cliente> Acompanantes;
	private Date fechaIngreso;
	private Date fechaEntrada;
	private Date fechaSalida;
	private int numeroAcompanantes;
	private String tipoDocumento;
	private String documento;
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Habitacion> getHabitaciones() {
		return habitaciones;
	}
	public void setHabitaciones(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}
	public List<Cliente> getAcompanantes() {
		return Acompanantes;
	}
	public void setAcompanantes(List<Cliente> acompanantes) {
		Acompanantes = acompanantes;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
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
	public int getNumeroAcompanantes() {
		return numeroAcompanantes;
	}
	public void setNumeroAcompanantes(int numeroAcompanantes) {
		this.numeroAcompanantes = numeroAcompanantes;
	}
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
	
	
	
	
	
	
}
