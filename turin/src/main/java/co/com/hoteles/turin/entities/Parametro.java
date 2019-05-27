package co.com.hoteles.turin.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the parametros database table.
 * 
 */
@Entity
@Table(name="parametros")
@NamedQuery(name="Parametro.findAll", query="SELECT p FROM Parametro p where p.hotel=:hotel")
@NamedQuery(name="Parametro.findNombre", query="SELECT p FROM Parametro p where p.nombreParametro=:nombre")

public class Parametro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nombreParametro;

	private String tipo;

	private String valor;

	private String valorMaximo;

	private String valorMinimo;
	
	private int hotel;


	public Parametro() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreParametro() {
		return this.nombreParametro;
	}

	public void setNombreParametro(String nombreParametro) {
		this.nombreParametro = nombreParametro;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getValorMaximo() {
		return this.valorMaximo;
	}

	public void setValorMaximo(String valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public String getValorMinimo() {
		return this.valorMinimo;
	}

	public void setValorMinimo(String valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public int getHotel() {
		return hotel;
	}

	public void setHotel(int hotel) {
		this.hotel = hotel;
	}

}