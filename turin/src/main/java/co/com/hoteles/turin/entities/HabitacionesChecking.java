package co.com.hoteles.turin.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the habitaciones_checking database table.
 * 
 */
@Entity
@Table(name="habitaciones_checking")
@NamedQuery(name="HabitacionesChecking.findAll", query="SELECT h FROM HabitacionesChecking h")
@NamedQuery(name="HabitacionesChecking.findxCkecking", query="SELECT h FROM HabitacionesChecking h where h.idCkecking=:id")
@NamedQuery(name="HabitacionesChecking.findxCkeckingxHabitacion", query="SELECT h FROM HabitacionesChecking h where h.idCkecking=:idCkecking and h.idHabitacion=:idHabitacion")
@NamedQuery(name="HabitacionesChecking.findxHabitacion", query="SELECT h.idCkecking FROM HabitacionesChecking h where  h.idHabitacion=:idHabitacion")

public class HabitacionesChecking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="id_ckecking")
	private int idCkecking;

	@Column(name="id_habitacion")
	private int idHabitacion;
	
	private int hotel;

	public HabitacionesChecking() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCkecking() {
		return this.idCkecking;
	}

	public void setIdCkecking(int idCkecking) {
		this.idCkecking = idCkecking;
	}

	public HabitacionesChecking(int idCkecking, int idHabitacion) {
		super();
		this.idCkecking = idCkecking;
		this.idHabitacion = idHabitacion;
	}

	public int getIdHabitacion() {
		return this.idHabitacion;
	}

	public void setIdHabitacion(int idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	public int getHotel() {
		return hotel;
	}

	public void setHotel(int hotel) {
		this.hotel = hotel;
	}

}