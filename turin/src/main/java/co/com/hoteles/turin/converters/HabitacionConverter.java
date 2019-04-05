package co.com.hoteles.turin.converters;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpSession;

import co.com.hoteles.turin.entities.Cliente;
import co.com.hoteles.turin.entities.Habitacion;
import co.com.hoteles.turin.services.HabitacionService;

@FacesConverter("habitacionConverter")
public class HabitacionConverter implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        
    	
    	if(value != null && value.trim().length() > 0) {
            try {
            	HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
            	List<Habitacion> lista =(List<Habitacion>) session.getAttribute("listaHabitaciones");
            	for (Habitacion habitacionLista : lista) {
            		 if (habitacionLista.getId()==Integer.parseInt(value))	{

    					 return habitacionLista;
    				 }	
				}
                return lista.get(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "habitacion no es valido."));
            }catch (Exception e) {
				e.printStackTrace();
			}
        }
        else {
            return null;
        }
		return null;
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Habitacion) object).getId());
        }
        else {
            return null;
        }
    }   
}