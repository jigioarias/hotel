package co.com.hoteles.turin.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the servicios_ckeking database table.
 * 
 */
@Entity
@Table(name="servicios_ckeking")
@NamedQuery(name="ServiciosCkeking.findAll", query="SELECT s FROM ServiciosCkeking s")
@NamedQuery(name="ServiciosChecking.findxCkecking", query="SELECT s FROM ServiciosCkeking s where s.idChecking=:id" )
@NamedQuery(name="ServiciosChecking.findxCkeckingxServicio", query="SELECT s FROM ServiciosCkeking s where s.idChecking=:idChecking and s.idServicio=:idServicio" )

public class ServiciosCkeking implements Serializable {
	private static final long serialVersionUID = 1L;


	public ServiciosCkeking(int cantidad, int idChecking, int idServicio) {
		super();
		this.cantidad = cantidad;
		this.idChecking = idChecking;
		this.idServicio = idServicio;
	}

	@Id
	private int id;

	private int cantidad;

	@Column(name="id_checking")
	private int idChecking;

	@Column(name="id_servicio")
	private int idServicio;

	public ServiciosCkeking() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getIdChecking() {
		return this.idChecking;
	}

	public void setIdChecking(int idChecking) {
		this.idChecking = idChecking;
	}

	public int getIdServicio() {
		return this.idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

}