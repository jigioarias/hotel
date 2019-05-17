package co.com.hoteles.turin.views;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import co.com.hoteles.turin.entities.Cliente;
import co.com.hoteles.turin.services.ClienteService;

@ManagedBean
public class ReporteClientesView extends  GenericBB {

	private Date fechaInicio;
	private Date fechaFin;

	private String mensaje;
	private List<Cliente> listaClientes;
	private Date todayDate = new Date();


	
	public ReporteClientesView(){
		
	}
	  
	public void buscar() {

	    ClienteService clienteService = ClienteService.getInstance();
		try {
			
           setListaClientes(clienteService.listarExtranjeros("S", fechaInicio, fechaFin));

		} catch (Exception e) {

			e.printStackTrace();
		}
	}


	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public List<Cliente> getListaClientes() {
		return listaClientes;
	}


	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getTodayDate() {
		return todayDate;
	}

	public void setTodayDate(Date todayDate) {
		this.todayDate = todayDate;
	}     


}