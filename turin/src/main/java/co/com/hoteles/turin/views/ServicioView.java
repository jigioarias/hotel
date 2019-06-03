package co.com.hoteles.turin.views;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import co.com.hoteles.turin.entities.Servicio;
import co.com.hoteles.turin.services.ServicioService;

@ManagedBean
public class ServicioView extends GenericBB {

	private Servicio servicio;
	private String mensaje;
	private List<Servicio> listaServicios;



	
	
	public ServicioView(){
		servicio = new Servicio();
		try {
			setListaServicios(ServicioService.getInstance().listar(this.getHotelSession().getCodigo()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
  


	public Servicio getServicio() {
		return servicio;
	}




	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}




	public void enviar() {


		ServicioService servicioService = ServicioService.getInstance();
		try {
			servicio.setHotel(this.getHotelSession().getCodigo());
			servicioService.actualizar(servicio);
          
			FacesContext.getCurrentInstance().addMessage("messages",new FacesMessage(FacesMessage.SEVERITY_ERROR, "El servicio se guardo con exito", ""));

		} catch (Exception e) {

			e.printStackTrace();
		}
	try {
			setListaServicios(ServicioService.getInstance().listar(this.getHotelSession().getCodigo()));
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




	public List<Servicio> getListaServicios() {
		return listaServicios;
	}




	public void setListaServicios(List<Servicio> listaServicios) {
		this.listaServicios = listaServicios;
	}




	


	
	

}