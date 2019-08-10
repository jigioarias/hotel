package co.com.hoteles.turin.views;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import co.com.hoteles.turin.entities.Cliente;
import co.com.hoteles.turin.entities.Pais;
import co.com.hoteles.turin.services.ClienteService;
import co.com.hoteles.turin.services.PaisService;

@ManagedBean
public class ClienteView extends GenericBB {
     
    private String nombre;
	private List<String> extranjeros;
	private Map<String, String> tiposDocumento;
	private Map<String, String> paises;

	
	public List<String> getExtranjeros() {
		return extranjeros;
	}

	public void setExtranjeros(List<String> extranjeros) {
		this.extranjeros = extranjeros;
	}

	public Map<String, String> getTiposDocumento() {
		return tiposDocumento;
	}

	public void setTiposDocumento(Map<String, String> tiposDocumento) {
		this.tiposDocumento = tiposDocumento;
	}


	private String extranjero;
    private String correo;
    private String celular;
    private String tipoDocumento;
    private String documento;
    private String nacionalidad;
    public String getTipoDocumento() {
		return tipoDocumento;
	}
    
    @PostConstruct
	public void init() {

		
		extranjeros = new ArrayList<String>();
		extranjeros.add("S");
		extranjeros.add("N");
		tiposDocumento = new HashMap<String, String>();
		tiposDocumento.put("Cedula", "CC");
		tiposDocumento.put("Pasaporte", "PP");
		tiposDocumento.put("Cedula Extranjeria", "CE");
		tiposDocumento.put("Tarjeta Identidad", "TI");
		tiposDocumento.put("Nit", "NI");
		tiposDocumento.put("Documento Extranjero", "DE");
		tiposDocumento.put("Registro Civil", "RC");
		
		paises = new HashMap<String, String>(); 
		try {
			List<Pais> listaPaises =PaisService.getInstance().listar();
			for (Pais pais : listaPaises) {
				paises.put(pais.getGentilicio(),pais.getCodigo());
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}

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
		 
	        Cliente cliente = new Cliente();
	        cliente.setCelular(celular);
	        cliente.setCorreo(correo);
	        cliente.setFechaRegistro(new Date());
	        cliente.setFechaNacimiento(fechaNacimiento);
	        cliente.setDocumento(documento);
	        cliente.setTipoDocumento(tipoDocumento);
	        cliente.setNombre(nombre);
	        cliente.setNacionalidad(nacionalidad);
	        cliente.setUsuarioUIngreso(this.getUsuarioSession().getId());
	        cliente.setExtranjero(extranjero);
	        cliente.setHotel(this.getHotelSession().getCodigo());
	        
	        try {
	        	
				ClienteService.getInstance().ingresar(cliente);
		        this.mensaje ="El cliente fue creado con exito";
		   	   FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente", "Cliente:"+nombre+" creado");
			   FacesContext.getCurrentInstance().addMessage(null, message);

	        } catch (Exception e) {
	        	e.printStackTrace();
	       	 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente", "Cliente:"+nombre+"no fue creado");
			 FacesContext.getCurrentInstance().addMessage(null, message);

			}
	       
	        
	        

		 
	 }     
    
	 
	    public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


		public String getExtranjero() {
		return extranjero;
	}

	public void setExtranjero(String extranjero) {
		this.extranjero = extranjero;
	}


		public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Map<String, String> getPaises() {
		return paises;
	}

	public void setPaises(Map<String, String> paises) {
		this.paises = paises;
	}


	
 
   
}