package co.com.hoteles.turin.views;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import co.com.hoteles.turin.entities.Habitacion;
import co.com.hoteles.turin.services.HabitacionService;



@ManagedBean(name="limpiezaView")

public class LimpiezaView extends GenericBB implements Serializable {


	private String[] habitaciones;

	private List<SelectItem> habitacionesOcupadas;
	private String[] selectedHabitaciones;

	private String codigoHabitacion;
	private boolean value1;  
	private boolean value2;

	private static final long serialVersionUID = 1L;

	public String[] getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(String[] habitaciones) {
		this.habitaciones = habitaciones;
	}


	public LimpiezaView() {


	}




	public boolean isValue1() {
		return value1;
	}

	public void setValue1(boolean value1) {
		this.value1 = value1;
	}

	public boolean isValue2() {
		return value2;
	}

	public void setValue2(boolean value2) {
		this.value2 = value2;
	}

	public void addMessage() {
		String summary = value2 ? "Checked" : "Unchecked";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
	}		



	@PostConstruct
	public void init(){

		SelectItemGroup g1 = new SelectItemGroup("Habitaciones Turin");
		SelectItem[] habitacionesOcu= null;

		try {
			List<Habitacion> habitaciones= HabitacionService.getInstance().listar("FAC",this.getHotelSession().getCodigo());
			habitacionesOcu= new SelectItem[habitaciones.size()];
			int contador =0;
			for (Habitacion habitacion : habitaciones) {
				habitacionesOcu[contador] =new SelectItem(habitacion.getId(), habitacion.getNombre());
				contador++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		g1.setSelectItems(habitacionesOcu);


		habitacionesOcupadas = new ArrayList<SelectItem>();
		habitacionesOcupadas.add(g1);


	}

	public List<SelectItem> getHabitacionesOcupadas() {
		return habitacionesOcupadas;
	}

	public void setHabitacionesOcupadas(List<SelectItem> habitacionesOcupadas) {
		this.habitacionesOcupadas = habitacionesOcupadas;
	}

	public String getCodigoHabitacion() {
		return codigoHabitacion;
	}

	public void setCodigoHabitacion(String codigoHabitacion) {
		this.codigoHabitacion = codigoHabitacion;
	}

	public String[] getSelectedHabitaciones() {
		return selectedHabitaciones;
	}

	public void setSelectedHabitaciones(String[] selectedHabitaciones) {
		this.selectedHabitaciones = selectedHabitaciones;
	}



	public void liberar() {



		Habitacion h;
		try {
			h = HabitacionService.getInstance().find(Integer.parseInt(codigoHabitacion));
			h.setEstado("DIS");
			HabitacionService.getInstance().actualizar(h,this.getHotelSession().getCodigo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	 public String enviarHome() {
		 return "/index.xhtml?faces-redirect=true";	
	   }











}