package co.com.hoteles.turin.converters;

import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpSession;

import co.com.hoteles.turin.entities.Servicio;

 

@FacesConverter("servicioConverter")
public class ServicioConverter implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
       
    	if(value != null && value.trim().length() > 0) {
            try {
            	
            	
            	HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
            	List<Servicio> lista =(List<Servicio>) session.getAttribute("listaServicios");
            	
            	for (Servicio servicioLista : lista) {
            		
				 if (servicioLista.getId() == Integer.parseInt(value))	{

					 return servicioLista;
				 }
				}
            	
            	return null;
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Acompanante no valido."));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Servicio) object).getId());
        }
        else {
            return null;
        }
    }   
}     