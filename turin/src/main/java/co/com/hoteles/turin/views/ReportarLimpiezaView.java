package co.com.hoteles.turin.views;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UISelectMany;
import javax.faces.component.UISelectOne;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import co.com.hoteles.turin.entities.Habitacion;
import co.com.hoteles.turin.entities.Insumo;
import co.com.hoteles.turin.entities.InsumosChecking;
import co.com.hoteles.turin.services.HabitacionService;
import co.com.hoteles.turin.services.HabitacionesCkeckingService;
import co.com.hoteles.turin.services.InsumoHabitacionService;
import co.com.hoteles.turin.services.InsumosCheckingService;

import javax.faces.bean.ViewScoped;



@ManagedBean(name="reportarLimpiezaView")
@ViewScoped
public class ReportarLimpiezaView extends GenericBB implements Serializable {


	private String[] habitaciones;
	
	private List<SelectItem> habitacionesOcupadas;
	private String[] selectedHabitaciones;
	private List<String> listaInsumos;

	private String codigoHabitacion;
	public List<SelectItem> getHabitacionesOcupadas() {
		return habitacionesOcupadas;
	}

	public void setHabitacionesOcupadas(List<SelectItem> habitacionesOcupadas) {
		this.habitacionesOcupadas = habitacionesOcupadas;
	}

		
    public void buscarHabitacion() {
    	
    	
    }

	
	
	

	
	private static final long serialVersionUID = 1L;

	public String[] getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(String[] habitaciones) {
		this.habitaciones = habitaciones;
	}





	public ReportarLimpiezaView() {
		
	     listaInsumos = null; 
	     listaInsumos = new ArrayList<String>(); 

	}
	


	public void guardar() {

		try {
			
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage("messages",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, " Guardar Checking", ""));

	}



	
	 private boolean value1;  
	 private boolean value2;
	 
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
		List<Habitacion> habitaciones= HabitacionService.getInstance().listarOcupadas();
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
       
    /*  SelectItemGroup g2 = new SelectItemGroup("Habitaciones California");
      g2.setSelectItems(new SelectItem[] {new SelectItem("c1", "Habitacion 1 c"), new SelectItem("c2", "Habitacion 2 c"), new SelectItem("c3", "Habitacion 3 c")});
      */ 
      habitacionesOcupadas = new ArrayList<SelectItem>();
      habitacionesOcupadas.add(g1);
  //   habitacionesOcupadas.add(g2);	
   

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

public List<String> getListaInsumos() {
	return listaInsumos;
}

public void setListaInsumos(List<String> listaInsumos) {
	this.listaInsumos = listaInsumos;
}




public void buscarInsumos() {
	
   // listaInsumos = null; 

	    try {
			List<Insumo> listaInsumosConsultados=InsumoHabitacionService.getInstance().listar(Integer.parseInt(codigoHabitacion));

			for (Insumo insumo : listaInsumosConsultados) {
				listaInsumos.add(insumo.getCodigo()+"- "+insumo.getNombre()+"- Cantidad:"+insumo.getCantidad());

			}
	
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  
	     
}


public void adicionar(AjaxBehaviorEvent event) {
	
    String id = event.getComponent().toString();
}  

public void confirmar() {
	
   
    
  if (listaInsumos.size() != selectedHabitaciones.length) {
       for (String i:selectedHabitaciones) {
    	   
		  listaInsumos.remove(i);
	    }
	
    
       if(listaInsumos.size()>0) {

    	   for (String insumo : listaInsumos) {

    		   String[] datos = insumo.split("-");
    		   String codigoInsumo =datos[0];
    		   InsumosChecking insumosChecking = new InsumosChecking();
    		   int cantidad = Integer.parseInt(datos[2].split(":")[1]);
    		   insumosChecking.setCantidad(cantidad);
    		   try {
    			   int codigoCheking =HabitacionesCkeckingService.getInstance().getFindXHabitacion(Integer.parseInt(codigoHabitacion),this.getHotelSession().getCodigo());

    			   insumosChecking.setIdCkecking(codigoCheking);
    			   insumosChecking.setIdInsumo(Integer.parseInt(codigoInsumo));

    			   InsumosCheckingService.getInstance().actualizar(insumosChecking);
    		   } catch (Exception e) {

    			   e.printStackTrace();
    		   }

    	   }
       }

  }  
	Habitacion h;
	try {
		h = HabitacionService.getInstance().find(Integer.parseInt(codigoHabitacion));
		h.setEstado("RPF");
		HabitacionService.getInstance().actualizar(h);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}













}