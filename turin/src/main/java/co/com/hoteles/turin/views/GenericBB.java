package co.com.hoteles.turin.views;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import co.com.hoteles.turin.entities.Hotel;
import co.com.hoteles.turin.entities.Usuario;




/**
 * Codigo Realizado por Natali Monsalve Ramirez 
   Derechos reservados de Natali Monsalve Ramirez 
 */
public class GenericBB {



    private Usuario usuarioSession;

    private Hotel hotelSession;
  

     public  void guardarUsuario(FacesContext facesContext,Usuario usuario){

         facesContext.getExternalContext().getSessionMap().put("usuario",usuario);
         usuarioSession = usuario;
     }

     
     public  void guardarHotel(FacesContext facesContext,Hotel hotel){

         facesContext.getExternalContext().getSessionMap().put("hotel",hotel);
         hotelSession = hotel;
     }
     public  void borrarSession(FacesContext facesContext){

         facesContext.getExternalContext().invalidateSession();


     }

 public  Usuario getUsuario(FacesContext facesContext){

        Usuario x= (Usuario)facesContext.getExternalContext().getSessionMap().get("usuario");
	    return (Usuario)facesContext.getExternalContext().getSessionMap().get("usuario");

     }

 public  Hotel getHotel(FacesContext facesContext){

     Hotel x= (Hotel)facesContext.getExternalContext().getSessionMap().get("hotel");
	    return (Hotel)facesContext.getExternalContext().getSessionMap().get("hotel");

  }

 

 public Usuario getUsuarioSession() {
	 
	 return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
}


public Hotel getHotelSession() {
	 
	 return (Hotel) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hotel");
}



public void setUsuarioSession(Usuario usuarioSession) {
    this.usuarioSession = usuarioSession;
}

public void setHotelSession(Hotel hotelSession) {
    this.hotelSession = hotelSession;
}
}