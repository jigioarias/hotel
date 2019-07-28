package co.com.hoteles.turin.views;

import java.util.List;

import javax.faces.bean.ManagedBean;

import co.com.hoteles.turin.entities.Habitacion;
import co.com.hoteles.turin.services.HabitacionService;

@ManagedBean(name = "indexView")
public class IndexView  extends GenericBB {
  
	private static final long serialVersionUID = 1L;

	private List<Habitacion> habitaciones;
     
    private List<Habitacion> habitaciones2;
     
    private List<Habitacion> habitaciones3;
     
    private Habitacion selectedHabitacion;
     
   
    public Habitacion getSelectedHabitacion() {
		return selectedHabitacion;
	}


	public void setSelectedHabitacion(Habitacion selectedHabitacion) {
		this.selectedHabitacion = selectedHabitacion;
	}


	
    public IndexView () {
        try {
			habitaciones = HabitacionService.getInstance().listar("DIS");
		
           habitaciones2 =  HabitacionService.getInstance().listar("OCU");
           setHabitaciones3(HabitacionService.getInstance().listar("FAC"));

        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


	public List<Habitacion> getHabitaciones() {
		return habitaciones;
	}


	public void setHabitaciones(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}


	public List<Habitacion> getHabitaciones2() {
		return habitaciones2;
	}


	public void setHabitaciones2(List<Habitacion> habitaciones2) {
		this.habitaciones2 = habitaciones2;
	}


	public List<Habitacion> getHabitaciones3() {
		return habitaciones3;
	}


	public void setHabitaciones3(List<Habitacion> habitaciones3) {
		this.habitaciones3 = habitaciones3;
	}


 
   
}