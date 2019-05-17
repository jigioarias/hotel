package co.com.hoteles.turin.views;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import co.com.hoteles.turin.entities.Rol;
import co.com.hoteles.turin.services.RolService;

@ManagedBean
public class RolView {

	private Rol rol;
	private String mensaje;
	private List<Rol> listaRoles;



	
	
	public RolView(){
		rol = new Rol();
		try {
			listaRoles= RolService.getInstance().listar();
			System.out.println("lista roles:"+listaRoles.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
  


	public Rol getRol() {
		return rol;
	}




	public void setRol(Rol rol) {
		this.rol = rol;
	}




	public void enviar() {


		RolService insumoService = RolService.getInstance();
		try {
			insumoService.actualizar(rol);
          
			FacesContext.getCurrentInstance().addMessage("messages",new FacesMessage(FacesMessage.SEVERITY_ERROR, "El rol se guardo con exito", ""));

		} catch (Exception e) {

			e.printStackTrace();
		}
	try {
			setListaRoles(RolService.getInstance().listar());
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




	public List<Rol> getListaRoles() {
		return listaRoles;
	}




	public void setListaRoles(List<Rol> listaRoles) {
		this.listaRoles = listaRoles;
	}






	
	

}