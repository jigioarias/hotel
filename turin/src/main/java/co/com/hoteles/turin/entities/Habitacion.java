package co.com.hoteles.turin.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the habitacion database table.
 * 
 */
@Entity
@NamedQuery(name="Habitacion.findAll", query="SELECT h FROM Habitacion h")
@NamedQuery(name="Habitacion.findEstado", query="SELECT h FROM Habitacion h where estado=:estado")
@NamedQuery(name="Habitacion.findNombre", query="SELECT h FROM Habitacion h where nombre=:nombre")

public class Habitacion implements Serializable {
	public Habitacion(int capacidad, String descripcion, String estado, String nombre, int precio) {
		super();
		this.capacidad = capacidad;
		this.descripcion = descripcion;
		this.estado = estado;
		this.nombre = nombre;
		this.precio = precio;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int capacidad;

	private String descripcion;

	private String estado;

	private String nombre;

	private int precio;

	private int hotel;
	public Habitacion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacidad() {
		return this.capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecio() {
		return this.precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getHotel() {
		return hotel;
	}

	public void setHotel(int hotel) {
		this.hotel = hotel;
	}

}