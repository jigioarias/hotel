package co.com.hoteles.turin.views;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.primefaces.PrimeFaces;

import co.com.hoteles.turin.entities.Cliente;
import co.com.hoteles.turin.entities.Reserva;

@ManagedBean
public class ClienteView extends GenericBB {
     
    private String nombre;
    private String correo;
    private String celular;
    private String tipoDocumento;
    private String documento;
    public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	private Date fechaRegistro;
    private Date fechaNacimiento;
    private String mensaje;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}

	 public void enviar() {
		 
		 EntityManagerFactory emf = Persistence
	                .createEntityManagerFactory("HotelPU");
	        em = emf.createEntityManager();
		 
		    em.getTransaction().begin();
	        Cliente cliente = new Cliente();
	        cliente.setCelular(celular);
	        cliente.setCorreo(correo);
	        cliente.setFechaRegistro(fechaRegistro);
	        cliente.setFechaNacimiento(fechaNacimiento);
	        cliente.setDocumento(documento);
	        cliente.setTipoDocumento(tipoDocumento);
	        cliente.setNombre(nombre);
	        cliente.setHotel(this.getHotelSession().getCodigo());
	        
	        em.persist(cliente);
	        em.getTransaction().commit();
	        
	        this.mensaje ="El cliente fue creado con exito";
	        
			 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Gracias", "reservado señor:"+nombre);
			 FacesContext.getCurrentInstance().addMessage(null, message);

		 
	 }     
    
	 
	    public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


		private static EntityManager em;

 
   
}