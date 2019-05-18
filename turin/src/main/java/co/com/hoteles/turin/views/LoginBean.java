package co.com.hoteles.turin.views;


import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import co.com.hoteles.turin.entities.Hotel;
import co.com.hoteles.turin.entities.Usuario;
import co.com.hoteles.turin.services.HotelService;
import co.com.hoteles.turin.services.UsuarioService;



/**
 * Codigo Realizado por Natali Monsalve Ramirez 
   Derechos reservados de Natali Monsalve Ramirez 
 */
@ManagedBean(name = "loginBean")
public class LoginBean extends GenericBB {

	UsuarioService usuarioService;

	private Usuario usuario;
	
	private Hotel hotel;
    

	public LoginBean() {
		usuarioService = UsuarioService.getInstance();
		usuario = new Usuario();
		hotel = new Hotel();

	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public String ingresar() {

		usuarioService = UsuarioService.getInstance();

		// colocar todos los valores de la clase cajero
		Usuario usuarioC;
		Hotel hotelC;
		
		try {
			usuarioC = usuarioService.findXClave(usuario);
			hotelC =  HotelService.getInstance().find(hotel.getCodigo());
		
		if (usuarioC != null) {
			usuario = usuarioC;
			this.guardarUsuario(FacesContext.getCurrentInstance(), usuarioC);
			this.guardarHotel(FacesContext.getCurrentInstance(), hotelC);

			return "/menu.xhtml?faces-redirect=true";	
			
			
		} else {
			this.borrarSession(FacesContext.getCurrentInstance());

		}
		  FacesContext.getCurrentInstance().addMessage("messages",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Usuario y Clave Incorrectos", ""));
		  return "/login.xhtml?faces-redirect=true";	
	} catch (Exception e) {
		// TODO Auto-generated catch block
	  e.printStackTrace();
	  return "/login.xhtml?faces-redirect=true";	
	}
	
	}


	
	public String salir() {

		this.borrarSession(FacesContext.getCurrentInstance());
		 return "/login.xhtml?faces-redirect=true";	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	

}
