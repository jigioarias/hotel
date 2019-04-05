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

import co.com.hoteles.turin.entities.Cliente;

 

@FacesConverter("acompananteConverter")
public class AcompananteConverter implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
       
    	if(value != null && value.trim().length() > 0) {
            try {
            	
            	
            	HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
            	List<Cliente> lista =(List<Cliente>) session.getAttribute("listaAcompanantes");
            	
            	for (Cliente clienteLista : lista) {
            		
				 if (clienteLista.getId() == Integer.parseInt(value))	{

					 return clienteLista;
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
            return String.valueOf(((Cliente) object).getId());
        }
        else {
            return null;
        }
    }   
}     