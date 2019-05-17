package co.com.hoteles.turin.views;


import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import co.com.hoteles.turin.entities.Usuario;
import co.com.hoteles.turin.services.UsuarioService;



/**
 * Codigo Realizado por Natali Monsalve Ramirez 
   Derechos reservados de Natali Monsalve Ramirez 
 */
@ManagedBean(name = "loginBean")
public class LoginBean extends GenericBB {

	UsuarioService usuarioService;

	private Usuario usuario;
    

	public LoginBean() {
		usuarioService = UsuarioService.getInstance();
		usuario = new Usuario();

	}

	public String ingresar() {

		usuarioService = UsuarioService.getInstance();

		// colocar todos los valores de la clase cajero
		Usuario usuarioC;
		try {
			usuarioC = usuarioService.findXClave(usuario);
		
		if (usuarioC != null) {
			usuario = usuarioC;
			this.guardarUsuario(FacesContext.getCurrentInstance(), usuarioC);
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
