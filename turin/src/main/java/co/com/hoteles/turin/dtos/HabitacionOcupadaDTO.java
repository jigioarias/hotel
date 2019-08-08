package co.com.hoteles.turin.dtos;

import co.com.hoteles.turin.entities.Habitacion;

public class HabitacionOcupadaDTO {
	
	private Habitacion habitacion;
	private String cliente;
	public Habitacion getHabitacion() {
		return habitacion;
	}
	
	public HabitacionOcupadaDTO(Habitacion habitacion, String cliente) {
		super();
		this.habitacion = habitacion;
		this.cliente = cliente;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

}
