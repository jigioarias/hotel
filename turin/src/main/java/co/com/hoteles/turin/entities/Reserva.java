package co.com.hoteles.turin.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the reservas database table.
 * 
 */
@Entity
@Table(name="reservas")
@NamedQuery(name="Reserva.findAll", query="SELECT r FROM Reserva r where r.hotel=:hotels")
@NamedQuery(name="Reserva.findEstado", query="SELECT r FROM Reserva r where activa=:activo and hotel=:hotel")

public class Reserva implements Serializable {
	private static final long serialVersionUID = 1L;
	

	public Reserva(int idreserva, String celular, String correo, Date fechaEntrada, Date fechaSalida, String nombre,
			int numeroAdultos, int numeroHabitaciones, int numeroNinos,int hotel) {
		super();
		this.idreserva = idreserva;
		this.celular = celular;
		this.correo = correo;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.nombre = nombre;
		this.numeroAdultos = numeroAdultos;
		this.numeroHabitaciones = numeroHabitaciones;
		this.numeroNinos = numeroNinos;
		this.hotel = hotel;
		
	}

	
	
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idreserva;

	private String celular;

	private String correo;

	@Temporal(TemporalType.DATE)
	private Date fechaEntrada;

	@Temporal(TemporalType.DATE)
	private Date fechaSalida;

	private String nombre;

	private int numeroAdultos;

	private int numeroHabitaciones;

	private int numeroNinos;
	
	private String activa;
	
	private String metodo;
	
	private int abono;
	
	private int hotel;

	private String habitaciones;
	

	public Reserva() {
	}

	public int getIdreserva() {
		return this.idreserva;
	}

	public void setIdreserva(int idreserva) {
		this.idreserva = idreserva;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFechaEntrada() {
		return this.fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return this.fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumeroAdultos() {
		return this.numeroAdultos;
	}

	public void setNumeroAdultos(int numeroAdultos) {
		this.numeroAdultos = numeroAdultos;
	}

	public int getNumeroHabitaciones() {
		return this.numeroHabitaciones;
	}

	public void setNumeroHabitaciones(int numeroHabitaciones) {
		this.numeroHabitaciones = numeroHabitaciones;
	}

	public int getNumeroNinos() {
		return this.numeroNinos;
	}

	public void setNumeroNinos(int numeroNinos) {
		this.numeroNinos = numeroNinos;
	}

	public String getActiva() {
		return activa;
	}

	public void setActiva(String activa) {
		this.activa = activa;
	}

	public int getHotel() {
		return hotel;
	}

	public void setHotel(int hotel) {
		this.hotel = hotel;
	}

	public String getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(String habitaciones) {
		this.habitaciones = habitaciones;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public int getAbono() {
		return abono;
	}

	public void setAbono(int abono) {
		this.abono = abono;
	}

}