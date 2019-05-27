package co.com.hoteles.turin.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the insumos database table.
 * 
 */
@Entity
@Table(name="insumos")
@NamedQuery(name="Insumo.findAll", query="SELECT i FROM Insumo i where i.hotel=:hotel")
@NamedQuery(name="Insumo.findNombre", query="SELECT i FROM Insumo i where i.nombre=:nombre")

public class Insumo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	private int codigo;

	private String nombre;
	
	private int valor;
	
	private int hotel;

	
	 public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	transient int cantidad;

	public Insumo() {
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Insumo(int codigo, String nombre, int cantidad) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.cantidad = cantidad;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getHotel() {
		return hotel;
	}

	public void setHotel(int hotel) {
		this.hotel = hotel;
	}

}