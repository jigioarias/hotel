package co.com.hoteles.turin.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the acompanantes_checking database table.
 * 
 */
@Entity
@Table(name="acompanantes_checking")
@NamedQuery(name="AcompanantesChecking.findAll", query="SELECT a FROM AcompanantesChecking a")
@NamedQuery(name="AcompanantesChecking.findxCkecking", query="SELECT a FROM AcompanantesChecking a where a.idChecking=:id")
@NamedQuery(name="AcompanantesChecking.findxCkeckingxCliente", query="SELECT a FROM AcompanantesChecking a where a.idChecking=:idChecking and a.idCliente=:idCliente")

public class AcompanantesChecking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	public AcompanantesChecking(int idChecking, int idCliente) {
		super();
		this.idChecking = idChecking;
		this.idCliente = idCliente;
	}

	@Column(name="id_checking")
	private int idChecking;

	@Column(name="id_cliente")
	private int idCliente;
	

	public AcompanantesChecking() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdChecking() {
		return this.idChecking;
	}

	public void setIdChecking(int idChecking) {
		this.idChecking = idChecking;
	}

	public int getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}



}