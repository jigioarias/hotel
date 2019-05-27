package co.com.hoteles.turin.views;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;
import org.primefaces.model.DualListModel;

import co.com.hoteles.turin.entities.Ckecking;
import co.com.hoteles.turin.entities.Cliente;
import co.com.hoteles.turin.entities.Habitacion;
import co.com.hoteles.turin.entities.Insumo;
import co.com.hoteles.turin.entities.Reserva;
import co.com.hoteles.turin.services.AcompanantesCkeckingService;
import co.com.hoteles.turin.services.CkeckingService;
import co.com.hoteles.turin.services.HabitacionService;
import co.com.hoteles.turin.services.HabitacionesCkeckingService;
import co.com.hoteles.turin.services.InsumoHabitacionService;
import co.com.hoteles.turin.services.InsumoService;
import co.com.hoteles.turin.services.ServiciosCkeckingService;

@ManagedBean
public class InsumoView extends GenericBB {

	private Insumo insumo;
	private String mensaje;
	private List<Insumo> listaInsumos;



	
	
	public InsumoView(){
		insumo = new Insumo();
		try {
			listaInsumos= InsumoService.getInstance().listar(this.getHotelSession().getCodigo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
  


	public Insumo getInsumo() {
		return insumo;
	}




	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}




	public void enviar() {


		InsumoService insumoService = InsumoService.getInstance();
		try {
			insumoService.actualizar(insumo);
          
			FacesContext.getCurrentInstance().addMessage("messages",new FacesMessage(FacesMessage.SEVERITY_ERROR, "El insumo se guardo con exito", ""));

		} catch (Exception e) {

			e.printStackTrace();
		}
	try {
			setListaInsumos(InsumoService.getInstance().listar(this.getHotelSession().getCodigo()));
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




	public List<Insumo> getListaInsumos() {
		return listaInsumos;
	}




	public void setListaInsumos(List<Insumo> listaInsumos) {
		this.listaInsumos = listaInsumos;
	}



	
	

}