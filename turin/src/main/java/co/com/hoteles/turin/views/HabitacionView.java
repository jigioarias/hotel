package co.com.hoteles.turin.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import co.com.hoteles.turin.enums.TipoHabitacion;
import co.com.hoteles.turin.services.AcompanantesCkeckingService;
import co.com.hoteles.turin.services.CkeckingService;
import co.com.hoteles.turin.services.HabitacionService;
import co.com.hoteles.turin.services.HabitacionesCkeckingService;
import co.com.hoteles.turin.services.InsumoHabitacionService;
import co.com.hoteles.turin.services.InsumoService;
import co.com.hoteles.turin.services.ServiciosCkeckingService;

@ManagedBean
public class HabitacionView extends GenericBB {

	private Habitacion habitacion;
	private String mensaje;
	private Habitacion  habitacionBusqueda;
	private List<Habitacion> listaHabitaciones;
    private DualListModel<String> insumos;
      
	private Map<String, String> tipoHabitaciones;



	public Habitacion getHabitacionBusqueda() {
		return habitacionBusqueda;
	}
	public void setHabitacionBusqueda(Habitacion habitacionBusqueda) {
		this.habitacionBusqueda = habitacionBusqueda;
	}
	
	public HabitacionView(){
		habitacion = new Habitacion();
		try {
			listaHabitaciones = HabitacionService.getInstance().listar(this.getHotelSession().getCodigo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    @PostConstruct
    public void init() {
       
    	List<String> insumosDisponibles = new ArrayList<String>();
    	 List<TipoHabitacion> listatiposHabitaciones = new ArrayList<TipoHabitacion>(Arrays.asList(TipoHabitacion.values()));
    	
		try {
			for(Insumo i:InsumoService.getInstance().listar(this.getHotelSession().getCodigo())) {
				insumosDisponibles.add(i.getNombre());
			}
			tipoHabitaciones = new HashMap<String, String>();
			 for (TipoHabitacion tipoHabitacion : listatiposHabitaciones) {

				 tipoHabitaciones.put(tipoHabitacion.getDescripcion(), tipoHabitacion.getCodigo());	
		      }
		    List<String> insumosSeleccionados = new ArrayList<String>();
            insumos = new DualListModel<String>(insumosDisponibles, insumosSeleccionados);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public DualListModel<String> getInsumos() {
		return insumos;
	}
	public void setInsumos(DualListModel<String> insumos) {
		this.insumos = insumos;
	}
	public void enviar() {


		HabitacionService habitacionService = HabitacionService.getInstance();
		InsumoHabitacionService insumoHabitacionService = InsumoHabitacionService.getInstance();
		
		habitacion.setHotel(this.getHotelSession().getCodigo());
		try {
			habitacionService.actualizar(habitacion,insumos.getTarget(),this.getHotelSession().getCodigo());
          

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error",
					"La habitacion se guardo con exito"));

		} catch (Exception e) {

			e.printStackTrace();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error",
					"La habitacion no se guardo con exito"));

		}
	try {
			listaHabitaciones = HabitacionService.getInstance().listar(this.getHotelSession().getCodigo());
		} catch (Exception e) {
			e.printStackTrace();
		}



	}     


	public Habitacion getHabitacion() {
		return habitacion;
	}


	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}


	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public List<Habitacion> completeHabitacion(String query) {
		List<Habitacion> habitacionesDisponibles;
		List<Habitacion> filteredHabitacion = new ArrayList<Habitacion>();;
		try {
			habitacionesDisponibles = HabitacionService.getInstance().listar(this.getHotelSession().getCodigo());

			for (int i = 0; i < habitacionesDisponibles.size(); i++) {
				Habitacion habitacion = habitacionesDisponibles.get(i);
				if(habitacion.getNombre().toLowerCase().contains(query)) {
					filteredHabitacion.add(habitacion);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("listaHabitaciones",filteredHabitacion);

		return filteredHabitacion;
	}


	public void buscarHabitacion() {

		habitacion = habitacionBusqueda;
		List<String> insumosDisponibles = new ArrayList<String>();
	    List<String> insumosSeleccionados = new ArrayList<String>();

		try {
            InsumoHabitacionService  insumoHabitacionService =InsumoHabitacionService.getInstance();
            List<Insumo> lista =insumoHabitacionService.listar(habitacion.getId());
            for (Insumo insumo : lista) {
				insumosSeleccionados.add(insumo.getNombre());
			}
			for(Insumo i:InsumoService.getInstance().listar(this.getHotelSession().getCodigo())) {
				
				insumosDisponibles.add(i.getNombre());
				
			}
		
			for(String i:insumosSeleccionados) {
				
				insumosDisponibles.remove(i);
				
			}
					

            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        insumos = new DualListModel<String>(insumosDisponibles, insumosSeleccionados);


	}
	public List<Habitacion> getListaHabitaciones() {
		return listaHabitaciones;
	}
	public void setListaHabitaciones(List<Habitacion> listaHabitaciones) {
		this.listaHabitaciones = listaHabitaciones;
	}
	public Map<String, String> getTipoHabitaciones() {
		return tipoHabitaciones;
	}
	public void setTipoHabitaciones(Map<String, String> tipoHabitaciones) {
		this.tipoHabitaciones = tipoHabitaciones;
	}

}