package co.com.hoteles.turin.dtos;

import java.util.Date;

public class VentaDTO {

	
	private int valor;
	Date fecha;
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}