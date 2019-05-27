package co.com.hoteles.turin.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the insumos_checking database table.
 * 
 */
@Entity
@Table(name="insumos_checking")
@NamedQuery(name="InsumosChecking.findAll", query="SELECT i FROM InsumosChecking i")
@NamedQuery(name="InsumosChecking.findChecking", query="SELECT i FROM InsumosChecking i where i.idCkecking=:idChecking")

public class InsumosChecking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;


	private int cantidad;

	private int hotel;


	@Column(name="id_ckecking")
	private int idCkecking;

	
	@Column(name="id_insumo")
	private int idInsumo;

	
	public InsumosChecking() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public int getIdCkecking() {
		return idCkecking;
	}


	public void setIdCkecking(int idCkecking) {
		this.idCkecking = idCkecking;
	}


	public int getIdInsumo() {
		return idInsumo;
	}


	public void setIdInsumo(int idInsumo) {
		this.idInsumo = idInsumo;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public int getHotel() {
		return hotel;
	}


	public void setHotel(int hotel) {
		this.hotel = hotel;
	}

	
}