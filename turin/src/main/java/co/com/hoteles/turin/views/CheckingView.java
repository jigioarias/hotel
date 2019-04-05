package co.com.hoteles.turin.views;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.primefaces.PrimeFaces;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

import co.com.hoteles.turin.dtos.CheckingDTO;
import co.com.hoteles.turin.entities.AcompanantesChecking;
import co.com.hoteles.turin.entities.Ckecking;
import co.com.hoteles.turin.entities.Cliente;
import co.com.hoteles.turin.entities.Habitacion;
import co.com.hoteles.turin.entities.HabitacionesChecking;
import co.com.hoteles.turin.entities.Servicio;
import co.com.hoteles.turin.entities.ServiciosCkeking;
import co.com.hoteles.turin.entities.Usuario;
import co.com.hoteles.turin.services.AcompanantesCkeckingService;
import co.com.hoteles.turin.services.CkeckingService;
import co.com.hoteles.turin.services.ClienteService;
import co.com.hoteles.turin.services.HabitacionService;
import co.com.hoteles.turin.services.HabitacionesCkeckingService;
import co.com.hoteles.turin.services.ServicioService;
import co.com.hoteles.turin.services.ServiciosCkeckingService;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;



@ManagedBean(name="checkingView")
@ViewScoped
public class CheckingView extends GenericBB implements Serializable {



	private Cliente cliente;
	private List<Cliente> acompanantes;
	private List<Servicio> servicios;
	private Date fechaEntrada;
	private Date fechaSalida;
	private String formatFechaSalida;
	private String formatFechaEntrada;
	private int numeroPersonas;
	
	private Date todayDate = new Date();
	

	private Servicio servicioSeleccionado;
	private String[] habitaciones;
	private List<String> habitacionesDisponibles;
	private List<String> extranjeros; 
	private Map<String,String> tiposDocumento;
	
	private Cliente clienteBusqueda;
	private Cliente acompananteBusqueda;
	private Servicio servicioBusqueda;
	
	
	
	private List<Habitacion> habitacionSeleccionada;

	private Date fechaNacimiento =new Date();
	private String extranjero="N";
	private String documento=" ";
	private String correo =" ";
	private String celular=" " ;
	private String nombre =" ";
	private String tipoDocumento= " ";



	 
	 public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public String getExtranjero() {
		return extranjero;
	}


	public void setExtranjero(String extranjero) {
		this.extranjero = extranjero;
	}


	public List<Habitacion> completeHabitacion(String query) {
	        List<Habitacion> habitacionesDisponibles;
	        List<Habitacion> filteredHabitacion = new ArrayList<Habitacion>();;
			try {
				habitacionesDisponibles = HabitacionService.getInstance().listarDisponibles();
		     
	        for (int i = 0; i < habitacionesDisponibles.size(); i++) {
	            Habitacion habitacion = habitacionesDisponibles.get(i);
	            if(habitacion.getNombre().toLowerCase().contains(query)) {
	            	filteredHabitacion.add(habitacion);
	            }
	        }
			} catch (Exception e) {
				e.printStackTrace();
			}
			 HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	     	 session.setAttribute("listaHabitaciones",filteredHabitacion);
			
	        return filteredHabitacion;
	    }
	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getAcompanantes() {
		return acompanantes;
	}

	public void setAcompanantes(List<Cliente> acompanantes) {
		this.acompanantes = acompanantes;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String[] getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(String[] habitaciones) {
		this.habitaciones = habitaciones;
	}

	public List<String> getHabitacionesDisponibles() {
		return habitacionesDisponibles;
	}

	public void setHabitacionesDisponibles(List<String> habitacionesDisponibles) {
		this.habitacionesDisponibles = habitacionesDisponibles;
	}



	public List<String> getExtranjeros() {
		return extranjeros;
	}

	public void setExtranjeros(List<String> extranjeros) {
		this.extranjeros = extranjeros;
	}

	public CheckingView(){

		



	}

	public Map<String, String> getTiposDocumento() {
		return tiposDocumento;
	}

	public void setTiposDocumento(Map<String, String> tiposDocumento) {
		this.tiposDocumento = tiposDocumento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


	private boolean skip;

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

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public Servicio getServicioSeleccionado() {
		return servicioSeleccionado;
	}

	public void setServicioSeleccionado(Servicio servicioSeleccionado) {
		this.servicioSeleccionado = servicioSeleccionado;
	}


	
	
	public String onFlowProcess(FlowEvent event) {
		if(skip) {
			skip = false;   //reset in case user goes back
			return "confirm";
		}
		else {
			return event.getNewStep();
		}
	}
	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}



	public void guardar() {

		try {
			
			   cliente.setFechaRegistro(new Date());
			   ClienteService.getInstance().ingresar(cliente);
			   Cliente ClienteIngresado = null;
			   if(clienteBusqueda != null) {
				    ClienteIngresado = clienteBusqueda;

			   }else {
			     ClienteIngresado = ClienteService.getInstance().getFindXDocumento(cliente.getDocumento());
			   }
			   Ckecking ck = new Ckecking();
			   ck.setEstado("A");
			   ck.setFechaEntrada(fechaEntrada);
			   ck.setFechaRegistro(new Date());
			   ck.setFechaSalida(fechaSalida);
			   ck.setIdCliente(ClienteIngresado.getId());
			   ck.setNumeroPersonas(numeroPersonas);
			   ck.setUsuario(getUsuarioSession().getLogin());
			   		   
			   CkeckingService.getInstance().ingresar(ck);
			   System.out.println("clienteBusqueda.getId()>>>>>>:::"+clienteBusqueda.getId()); 
			   System.out.println("ClienteIngresado.getId()>>>>>>:::"+ClienteIngresado.getId()); 

			  Ckecking CkeckingConsultado =  CkeckingService.getInstance().getFindXCliente(ClienteIngresado.getId());
			   System.out.println("CkeckingConsultado.getId()::::::"+CkeckingConsultado.getId());
			  if(CkeckingConsultado!= null) {
			   for (Habitacion habitacion : habitacionSeleccionada) {
				   System.out.println("habitacion.getId()::::::"+habitacion.getId());

				  HabitacionesChecking h = new HabitacionesChecking(CkeckingConsultado.getId(),habitacion.getId());  
				  HabitacionesCkeckingService.getInstance().ingresar(h);
				  habitacion.setEstado("OCU");
				  HabitacionService.getInstance().actualizar(habitacion);
			   }
			   
			   for (Servicio servicio : servicios) {
				   	 
				    Servicio servicioConsultado = null;
				    if(servicio.getId() <1  )	{	
				   	    servicio.setEstado("A");
				   		ServicioService.getInstance().ingresar(servicio);
				    	servicioConsultado=	ServicioService.getInstance().getFindXNombre(servicio.getNombre().toUpperCase());

				   	 }else {
				   		servicioConsultado = servicio;
				   	 }
				   	

				   	 ServiciosCkeking s = new ServiciosCkeking(servicio.getCantidad(),CkeckingConsultado.getId(),servicioConsultado.getId());  
					 ServiciosCkeckingService.getInstance().ingresar(s);
				 }
			   boolean hayExtrajeros = false;
			   for (Cliente acompanante : acompanantes) {
					 
					 ClienteService.getInstance().ingresar(acompanante);
					 if(acompanante.getExtranjero().equals("S")) {
						 	hayExtrajeros =true;
					 }
					 Cliente acompananteConsultado =ClienteService.getInstance().getFindXDocumento(acompanante.getDocumento());
					 AcompanantesCkeckingService.getInstance().ingresar(new AcompanantesChecking(CkeckingConsultado.getId(),acompananteConsultado.getId()));
					 
				 }
		        String mensaje ="";
			    if(hayExtrajeros) {
			    	mensaje ="EL cheking se guardo con exito,Recuerde que debe reportar al final de mes los extranjeros";

			    }else {
			    	
			    	mensaje ="EL cheking se guardo con exito";
			    }
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Grabacion de Checking", mensaje);
		        PrimeFaces.current().dialog().showMessageDynamic(message);

			  }else {
			      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Grabacion de Checking", "error al grabar el checking. valide con el administrador");
			        PrimeFaces.current().dialog().showMessageDynamic(message);
  
			  }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage("messages",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, " Guardar Checking", ""));

	}


	public void adicionarAcompanante() {

		Cliente acompanante = new Cliente();
		acompanante.setTipoDocumento(tipoDocumento);
		acompanante.setDocumento(documento.toUpperCase());
		acompanante.setCorreo(correo);
		acompanante.setNombre(nombre.toUpperCase());
		acompanante.setCelular(celular);
		acompanante.setFechaNacimiento(fechaNacimiento);
		acompanante.setExtranjero(extranjero);
		acompanante.setFechaRegistro(new Date());
		acompanantes.add(acompanante);
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("adicionado", true);



	}


	public void adicionarServicio() {


		servicios.add(servicioSeleccionado);
		servicioSeleccionado = new Servicio();
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("adicionado", true);



	}
	public void editar(){

		FacesContext.getCurrentInstance().addMessage("messages",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, " Editar Checking", ""));



	}





	public void buscar() {

		FacesContext.getCurrentInstance().addMessage("messages",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, " Buscar Checking", ""));




	}

	
	
	
	

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

public List<Cliente> completeCliente(String query) {
		
		
		List<Cliente> listaClientes;
		List<Cliente> filteredCliente = null;
		try {
			listaClientes = ClienteService.getInstance().listar();
		
		  filteredCliente = new ArrayList<Cliente>();
		 int contador=0;
		 for (Iterator<Cliente> iterator = listaClientes.iterator(); iterator.hasNext();) {
			Cliente cliente =  iterator.next();
			
			  if(cliente.getNombre().toUpperCase().contains(query.toUpperCase())|| cliente.getDocumento().toUpperCase().contains(query.toUpperCase())) {
				 
				  filteredCliente.add(cliente);
	            }
			
		}
		
		 HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
     	 session.setAttribute("listaClientes",filteredCliente);
     	 
		} catch (Exception e) {
			
			e.printStackTrace();
		}
        return filteredCliente;
    }

public List<Cliente> completeAcompanante(String query) {
	
	
	List<Cliente> listaClientes;
	List<Cliente> filteredCliente = null;
	try {
		listaClientes = ClienteService.getInstance().listar();
	
	  filteredCliente = new ArrayList<Cliente>();
	 int contador=0;
	 for (Iterator<Cliente> iterator = listaClientes.iterator(); iterator.hasNext();) {
		Cliente cliente =  iterator.next();
		
		  if(cliente.getNombre().toUpperCase().contains(query.toUpperCase())|| cliente.getDocumento().toUpperCase().contains(query.toUpperCase())) {
			 
			  filteredCliente.add(cliente);
            }
		
	}
	
	 HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
 	 session.setAttribute("listaAcompanantes",filteredCliente);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
    return filteredCliente;
}




public List<Servicio> completeServicio(String query) {
	
	
	List<Servicio> listaServicios;
	List<Servicio> filteredServicio= null;
	try {
		listaServicios = ServicioService.getInstance().listar();
	
	  filteredServicio = new ArrayList<Servicio>();
	 int contador=0;
	 for (Iterator<Servicio> iterator = listaServicios.iterator(); iterator.hasNext();) {
		Servicio servicio =  iterator.next();
		
		  if(servicio.getNombre().toUpperCase().contains(query.toUpperCase())) {
			 
			  filteredServicio.add(servicio);
            }
		
	}
	
	 HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
 	 session.setAttribute("listaServicios",filteredServicio);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
    return filteredServicio;
}


public void buscarReserva() {
	
	cliente = clienteBusqueda;
	Ckecking checking = null;
	try {
		checking = CkeckingService.getInstance().getFindXCliente(cliente.getId());
		System.out.println("ID en buscar:>>>>>>>>>>>>>:::"+cliente.getId());
		if(checking !=null) {
			fechaSalida =checking.getFechaSalida();  
			fechaEntrada = checking.getFechaEntrada();
			numeroPersonas = checking.getNumeroPersonas();
			habitacionSeleccionada = HabitacionesCkeckingService.getInstance().getFindXChecking(checking.getId());
			 HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	     	 session.setAttribute("listaHabitaciones",habitacionSeleccionada);

			servicios = ServiciosCkeckingService.getInstance().getInstance().getFindXChecking(checking.getId());
			acompanantes = AcompanantesCkeckingService.getInstance().getFindXChecking(checking.getId());
		}      
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
       

}

@PostConstruct
 public void init(){

	acompanantes = new ArrayList<Cliente>();
	cliente = new Cliente();	
	servicioSeleccionado = new Servicio();
	servicioSeleccionado.setNombre("ingrese servicio");
	servicioSeleccionado.setValor(0);
	servicios = new ArrayList<Servicio>();
	habitacionesDisponibles = new ArrayList<String>();
	
	extranjeros = new ArrayList<String>();
	extranjeros.add("S");
	extranjeros.add("N");
	tiposDocumento  = new HashMap<String, String>();
	tiposDocumento.put("Cedula", "CC");
	tiposDocumento.put("Pasaporte", "PP");
	tiposDocumento.put("Cedula Extranjeria", "CE");
	
	guardarUsuario(FacesContext.getCurrentInstance(), new Usuario("jinni"));
   
}

public Cliente getClienteBusqueda() {
	return clienteBusqueda;
}

public void setClienteBusqueda(Cliente clienteBusqueda) {
	this.clienteBusqueda = clienteBusqueda;
}


public List<Habitacion> getHabitacionSeleccionada() {
	return habitacionSeleccionada;
}


public void setHabitacionSeleccionada(List<Habitacion> habitacionSeleccionada) {
	this.habitacionSeleccionada = habitacionSeleccionada;
}


public int getNumeroPersonas() {
	return numeroPersonas;
}


public void setNumeroPersonas(int numeroPersonas) {
	this.numeroPersonas = numeroPersonas;
}


public Date getTodayDate() {
	return todayDate;
}


public void setTodayDate(Date todayDate) {
	this.todayDate = todayDate;
}


public void borrarAcompanante(String documento) {
	
	Cliente clienteBorrar = null;
	for (Cliente cliente : acompanantes) {
	
	 if(cliente.getDocumento().equals(documento)) {
		   clienteBorrar = cliente;
	  }
		
	}
	if(clienteBorrar!=null) {
	acompanantes.remove(clienteBorrar);
	}
}

public void addAcompananteBuscar(){

	acompanantes.add(acompananteBusqueda);
	
}


public void addServicioBuscar(){

	servicios.add(servicioBusqueda);
	
}



public void borrarServicio(int id) {
	
	Servicio servicioBorrar = null;
	for (Servicio servicio : servicios) {
	
	 if(servicio.getId()==id) {
		   servicioBorrar = servicio;
	  }
		
	}
	if(servicioBorrar!=null) {
	servicios.remove(servicioBorrar);
	}
}


public Cliente getAcompananteBusqueda() {
	return acompananteBusqueda;
}


public void setAcompananteBusqueda(Cliente acompananteBusqueda) {
	this.acompananteBusqueda = acompananteBusqueda;
}


public Servicio getServicioBusqueda() {
	return servicioBusqueda;
}


public void setServicioBusqueda(Servicio servicioBusqueda) {
	this.servicioBusqueda = servicioBusqueda;
}

public  void generarChecking() {
	
	guardar();
	
	JRBeanCollectionDataSource beancollection=new JRBeanCollectionDataSource(null);
    String realpath=FacesContext.getCurrentInstance().getExternalContext().getInitParameter("ruta_reportes")+"checkingTable.jasper";
    String realpathImagenes=FacesContext.getCurrentInstance().getExternalContext().getInitParameter("ruta_imagenes")+"";
    System.out.println("realpath::"+realpath);
    try {
    	HashMap<String,Object> parametros =new HashMap<String,Object>();
    	parametros.put("tipoDocumentoCliente", cliente.getTipoDocumento());
    	parametros.put("documentoCliente", cliente.getDocumento());
    	parametros.put("nombreCliente", cliente.getNombre());
    	parametros.put("correo", cliente.getCorreo());
    	parametros.put("celular", cliente.getCelular()); 
     	parametros.put("fechaEntrada", fechaEntrada);
    	parametros.put("fechaSalida", fechaSalida);
    	parametros.put("numeroPersonas",numeroPersonas);
    	parametros.put("rutaImagen",realpathImagenes );
    	parametros.put("rutaReportes",FacesContext.getCurrentInstance().getExternalContext().getInitParameter("ruta_reportes") );
    	
    	
    	CheckingDTO checkingDTO = new CheckingDTO();
    	checkingDTO.setAcompanantes(acompanantes);
    	checkingDTO.setHabitaciones(habitacionSeleccionada);
    	checkingDTO.setServicios(servicios);
    	List<CheckingDTO> lista = new ArrayList<CheckingDTO>();
    	lista.add(checkingDTO);

    	 JRBeanCollectionDataSource beanColDataSource =  new JRBeanCollectionDataSource(lista);

		JasperPrint jasperprint=JasperFillManager.fillReport(realpath,parametros,beanColDataSource);
		HttpServletResponse httpservlet=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
	    httpservlet.addHeader("Content-disposition", "attachment;filename=checking_"+cliente.getDocumento()+".pdf");
	    ServletOutputStream servletout=httpservlet.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(jasperprint, servletout);
	    FacesContext.getCurrentInstance().responseComplete();
	} catch (Exception e) {
		e.printStackTrace();
	}
}



public String getFormatFechaEntrada() {
    return new SimpleDateFormat("dd-MM-yyyy").format(fechaEntrada);
}

public String getFormatFechaSalida() {
    return new SimpleDateFormat("dd-MM-yyyy").format(fechaSalida);
}

public void setFormatFechaSalida(String formatFechaSalida) {
	this.formatFechaSalida = formatFechaSalida;
}



public void setFormatFechaEntrada(String formatFechaEntrada) {
	this.formatFechaEntrada = formatFechaEntrada;
}


public  void generarFactura() {
	
	JRBeanCollectionDataSource beancollection=new JRBeanCollectionDataSource(null);
    String realpath=FacesContext.getCurrentInstance().getExternalContext().getInitParameter("ruta_reportes")+"FacturaTable.jasper";
    String realpathImagenes=FacesContext.getCurrentInstance().getExternalContext().getInitParameter("ruta_imagenes")+"";
    System.out.println("realpath::"+realpath);
    try {
    	HashMap<String,Object> parametros =new HashMap<String,Object>();
    	parametros.put("tipoDocumentoCliente", cliente.getTipoDocumento());
    	parametros.put("documentoCliente", cliente.getDocumento());
    	parametros.put("nombreCliente", cliente.getNombre());
    	parametros.put("correo", cliente.getCorreo());
    	parametros.put("celular", cliente.getCelular()); 
     	parametros.put("fechaEntrada", fechaEntrada);
    	parametros.put("fechaSalida", fechaSalida);
		int dias=(int) ((fechaSalida.getTime()-fechaEntrada.getTime())/86400000);
		dias = dias+1;
        parametros.put("dias", (dias));
        int consecutivo = 2222;
        parametros.put("consecutivo", consecutivo);

        parametros.put("numeroPersonas",numeroPersonas);
    	parametros.put("rutaImagen",realpathImagenes );
    	parametros.put("rutaReportes",FacesContext.getCurrentInstance().getExternalContext().getInitParameter("ruta_reportes") );
        
    	int subtotal = 0;
    	for (Servicio servicio : servicios) {
    		
    		subtotal += servicio.getCantidad()*servicio.getValor();
			
		}
        for (Habitacion habitacion : habitacionSeleccionada) {
    		
    		subtotal += habitacion.getPrecio()*dias;
			
		}
    	
    	parametros.put("subtotal", subtotal);
    	CheckingDTO checkingDTO = new CheckingDTO();
    	checkingDTO.setHabitaciones(habitacionSeleccionada);
    	checkingDTO.setServicios(servicios);
    	List<CheckingDTO> lista = new ArrayList<CheckingDTO>();
    	lista.add(checkingDTO);

    	 JRBeanCollectionDataSource beanColDataSource =  new JRBeanCollectionDataSource(lista);

		JasperPrint jasperprint=JasperFillManager.fillReport(realpath,parametros,beanColDataSource);
		HttpServletResponse httpservlet=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
	    httpservlet.addHeader("Content-disposition", "attachment;filename=factura_"+cliente.getDocumento()+".pdf");
	    ServletOutputStream servletout=httpservlet.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(jasperprint, servletout);
	    FacesContext.getCurrentInstance().responseComplete();
	} catch (Exception e) {
		e.printStackTrace();
	}
}


}