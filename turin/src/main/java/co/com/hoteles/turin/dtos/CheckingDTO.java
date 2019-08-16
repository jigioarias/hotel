package co.com.hoteles.turin.dtos;

import java.util.List;

import co.com.hoteles.turin.entities.Cliente;
import co.com.hoteles.turin.entities.Habitacion;
import co.com.hoteles.turin.entities.Insumo;
import co.com.hoteles.turin.entities.Servicio;

public class CheckingDTO {
	
	private List<Servicio> servicios;
	private List<Habitacion> habitaciones;
	private List<Cliente> acompanantes;
	private List<VentaDTO> ventas;
	private List<InsumoDTO> insumos;
	private List<CheckinDTO> chechins;

	public List<Servicio> getServicios() {
		return servicios;
	}
	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}
	public List<Habitacion> getHabitaciones() {
		return habitaciones;
	}
	public void setHabitaciones(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}
	public List<Cliente> getAcompanantes() {
		return acompanantes;
	}
	public void setAcompanantes(List<Cliente> acompanantes) {
		this.acompanantes = acompanantes;
	}
	public List<InsumoDTO> getInsumos() {
		return insumos;
	}
	public void setInsumos(List<InsumoDTO> insumos) {
		this.insumos = insumos;
	}
	public List<VentaDTO> getVentas() {
		return ventas;
	}
	public void setVentas(List<VentaDTO> ventas) {
		this.ventas = ventas;
	}
	public List<CheckinDTO> getChechins() {
		return chechins;
	}
	public void setChechins(List<CheckinDTO> chechins) {
		this.chechins = chechins;
	}
	
		
	

}
