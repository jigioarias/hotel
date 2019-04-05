package co.com.hoteles.turin.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the insumoshabitacion database table.
 * 
 */
@Embeddable
public class InsumoshabitacionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int habitacion;

	private int insumo;

	public InsumoshabitacionPK() {
	}
	public int getHabitacion() {
		return this.habitacion;
	}
	public void setHabitacion(int habitacion) {
		this.habitacion = habitacion;
	}
	public int getInsumo() {
		return this.insumo;
	}
	public void setInsumo(int insumo) {
		this.insumo = insumo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof InsumoshabitacionPK)) {
			return false;
		}
		InsumoshabitacionPK castOther = (InsumoshabitacionPK)other;
		return 
			(this.habitacion == castOther.habitacion)
			&& (this.insumo == castOther.insumo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.habitacion;
		hash = hash * prime + this.insumo;
		
		return hash;
	}
}