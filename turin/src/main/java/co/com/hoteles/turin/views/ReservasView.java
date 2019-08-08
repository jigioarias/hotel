package co.com.hoteles.turin.views;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.primefaces.PrimeFaces;

import co.com.hoteles.turin.entities.Reserva;
import co.com.hoteles.turin.reportes.JPAUtility;
import co.com.hoteles.turin.services.ReservaService;

@ManagedBean
public class ReservasView extends GenericBB {
     
    private String nombre;
    private String correo;
    private String celular;
    private int numeroAdultos;
    private int numeroNinos;
    private Date fechaEntrada;
    private Date fechaSalida;
    private int numeroHabitaciones;
    private String mensaje;
    private int numeroReservas;
    private List<Reserva> listaReservas;
	private Date todayDate = new Date();

    
    public  ReservasView() {
    	

        ReservaService reservaService = ReservaService.getInstance();
        
        try {
			listaReservas =reservaService.listar("S",this.getHotelSession().getCodigo());
			numeroReservas =listaReservas.size();
	      
		} catch (Exception e) {
	       e.printStackTrace();

		}
        
    }

    public void borrarReserva(int idReserva) {
    	
    	try {
			ReservaService.getInstance().borrar(idReserva);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
 public void borrarReserva() {
    	
    	try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
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
	public int getNumeroAdultos() {
		return numeroAdultos;
	}
	public void setNumeroAdultos(int numeroAdultos) {
		this.numeroAdultos = numeroAdultos;
	}
	public int getNumeroNinos() {
		return numeroNinos;
	}
	public void setNumeroNinos(int numeroNinos) {
		this.numeroNinos = numeroNinos;
	}
	public Date getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public int getNumeroHabitaciones() {
		return numeroHabitaciones;
	}
	public void setNumeroHabitaciones(int numeroHabitaciones) {
		this.numeroHabitaciones = numeroHabitaciones;
	}
    
	 public void enviar() {
		 
	      
	        Reserva reserva = new Reserva();
	        reserva.setCelular(celular);
	        reserva.setCorreo(correo);
	        reserva.setFechaEntrada(fechaEntrada);
	        reserva.setFechaSalida(fechaSalida);
	        reserva.setNombre(nombre);
	        reserva.setNumeroAdultos(numeroAdultos);
	        reserva.setNumeroHabitaciones(numeroHabitaciones);
	        reserva.setNumeroNinos(numeroNinos);
	        reserva.setHotel(this.getHotelSession().getCodigo());
	        reserva.setActiva("S");
	        ReservaService reservaService = ReservaService.getInstance();
	        try {
				reservaService.ingresar(reserva);
		        this.mensaje ="La reserva fue creada con exito,señor(a) "+ nombre +" lo estaremos contactando";
				 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Gracias", "reservado señor:"+nombre);
				listaReservas =reservaService.listar("S",this.getHotelSession().getCodigo());


			} catch (Exception e) {
		        this.mensaje ="La reserva no fue creada por favor contacte al administrador : "+e.getMessage();
				 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", this.getMensaje());
				 FacesContext.getCurrentInstance().addMessage(null, message);

			}
		 
	 }     
    
	 
	    public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	
	 public int getNumeroReservas() {
        
		 
		      
	     return numeroReservas;
		 
	 }
	public List<Reserva> getListaReservas() {
		return listaReservas;
	}
	public void setListaReservas(List<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}

	public Date getTodayDate() {
		return todayDate;
	}

	public void setTodayDate(Date todayDate) {
		this.todayDate = todayDate;
	}    

		 

 
   
}