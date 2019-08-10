package co.com.hoteles.turin.views;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import co.com.hoteles.turin.entities.Rol;
import co.com.hoteles.turin.entities.Usuario;
import co.com.hoteles.turin.services.RolService;
import co.com.hoteles.turin.services.UsuarioService;

@ManagedBean
public class UsuarioView extends  GenericBB {

	private Usuario usuario;
	private String mensaje;
	private List<Usuario> listaUsuarios;
	private List<String> listaRoles;



	
	
	public UsuarioView(){
		usuario = new Usuario();
		try {
			listaUsuarios= UsuarioService.getInstance().listar();
			listaRoles = new ArrayList<String>();
		    List<Rol> roles =  RolService.getInstance().listar();	
		    for (Rol rol : roles) {
				listaRoles.add(rol.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
  


	public Usuario getUsuario() {
		return usuario;
	}




	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}




	public void enviar() {

		UsuarioService insumoService = UsuarioService.getInstance();
		try {
			usuario.setFechaCreacion(new Date());
			usuario.setUsuarioCreacion(this.getUsuarioSession().getId());
			usuario.setHotel(this.getHotelSession().getCodigo());
			insumoService.actualizar(usuario);
          
			FacesContext.getCurrentInstance().addMessage("messages",new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario	 se guardo con exito", ""));

		} catch (Exception e) {

			e.printStackTrace();
		}
	try {
			setListaUsuarios(UsuarioService.getInstance().listar());
		} catch (Exception e) {
			e.printStackTrace();
		}



	}     




	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}




	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}




	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios= listaUsuarios;
	}






	public List<String> getListaRoles() {
		return listaRoles;
	}




	public void setListaRoles(List<String> listaRoles) {
		this.listaRoles = listaRoles;
	}



}