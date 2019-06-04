package co.com.hoteles.turin.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



/**
 * The persistent class for the insumoshabitacion database table.
 * 
 */
@Entity
@NamedQuery(name="Insumoshabitacion.findAll", query="SELECT i FROM Insumoshabitacion i")
@NamedQuery(name="Insumoshabitacion.findHabitacion", query="SELECT i FROM Insumoshabitacion i where id.habitacion=:habitacion")
@NamedQuery(name="Insumoshabitacion.findId", query="SELECT i FROM Insumoshabitacion i where id.habitacion=:habitacion and id.insumo=:insumo")
@Table(name="insumoshabitacion")
public class Insumoshabitacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private InsumoshabitacionPK id;

	private int cantidad;
	


	public Insumoshabitacion() {
	}

	public InsumoshabitacionPK getId() {
		return this.id;
	}

	public void setId(InsumoshabitacionPK id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	

}