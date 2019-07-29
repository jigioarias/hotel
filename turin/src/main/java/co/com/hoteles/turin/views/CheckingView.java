package co.com.hoteles.turin.views;

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

import org.primefaces.PrimeFaces;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;

import co.com.hoteles.turin.dtos.CheckingDTO;
import co.com.hoteles.turin.dtos.InsumoDTO;
import co.com.hoteles.turin.entities.AcompanantesChecking;
import co.com.hoteles.turin.entities.Ckecking;
import co.com.hoteles.turin.entities.Cliente;
import co.com.hoteles.turin.entities.Factura;
import co.com.hoteles.turin.entities.Habitacion;
import co.com.hoteles.turin.entities.HabitacionesChecking;
import co.com.hoteles.turin.entities.Insumo;
import co.com.hoteles.turin.entities.InsumosChecking;
import co.com.hoteles.turin.entities.Pais;
import co.com.hoteles.turin.entities.Parametro;
import co.com.hoteles.turin.entities.Servicio;
import co.com.hoteles.turin.entities.ServiciosCkeking;
import co.com.hoteles.turin.services.AcompanantesCkeckingService;
import co.com.hoteles.turin.services.CkeckingService;
import co.com.hoteles.turin.services.ClienteService;
import co.com.hoteles.turin.services.FacturaService;
import co.com.hoteles.turin.services.HabitacionService;
import co.com.hoteles.turin.services.HabitacionesCkeckingService;
import co.com.hoteles.turin.services.InsumoService;
import co.com.hoteles.turin.services.InsumosCheckingService;
import co.com.hoteles.turin.services.PaisService;
import co.com.hoteles.turin.services.ParametroService;
import co.com.hoteles.turin.services.ServicioService;
import co.com.hoteles.turin.services.ServiciosCkeckingService;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean(name = "checkingView")
@ViewScoped
public class CheckingView extends GenericBB implements Serializable {

	private Cliente cliente;
	private List<Cliente> acompanantes;
	private List<Servicio> servicios;
	private Date fechaEntrada;
	private Date fechaSalida;
	private String formatFechaSalida;
	private String formatFechaEntrada;
	private List<String> codigosHabitacion;
	public Map<String, String> getPaises() {
		return paises;
	}

	public void setPaises(Map<String, String> paises) {
		this.paises = paises;
	}

	private int numeroPersonas;
	private DualListModel<String> habitacionesPickList;
	private boolean hayFactura;
	private Map<String, String> paises;


	private Date todayDate = new Date();

	private Servicio servicioSeleccionado;
	private String[] habitaciones;
	private List<String> habitacionesDisponibles;
	private List<String> extranjeros;
	private Map<String, String> tiposDocumento;

	private Cliente clienteBusqueda;
	private Cliente acompananteBusqueda;
	private Servicio servicioBusqueda;

	private List<Habitacion> habitacionSeleccionada;

	private Date fechaNacimiento = new Date();
	private String extranjero = "	";
	private String documento = " ";
	private String correo = "turin@gmail.com";
	private String celular = "3333333300";
	private String nombre = " ";
	private String tipoDocumento = " ";
	private String nacionalidad = " ";
	private String habitacionCliente="";
	

	public CheckingView() {

		List<String> habitacionesDisponibles = new ArrayList<String>();
		try {
			for (Habitacion i : HabitacionService.getInstance().listarDisponibles()) {
				habitacionesDisponibles
						.add(i.getNombre() + "-Capacidad:" + i.getCapacidad() + "-Precio:" + i.getPrecio());
			}

			List<String> habitacionesSeleccionados = new ArrayList<String>();
			habitacionesPickList = new DualListModel<String>(habitacionesDisponibles, habitacionesSeleccionados);
			setCodigosHabitacion(new ArrayList<String>());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Habitacion> completeHabitacion(String query) {
		List<Habitacion> habitacionesDisponibles;
		List<Habitacion> filteredHabitacion = new ArrayList<Habitacion>();
		;
		try {
			habitacionesDisponibles = HabitacionService.getInstance().listarDisponibles();

			for (int i = 0; i < habitacionesDisponibles.size(); i++) {
				Habitacion habitacion = habitacionesDisponibles.get(i);
				if (habitacion.getNombre().toLowerCase().contains(query)) {
					filteredHabitacion.add(habitacion);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("listaHabitaciones", filteredHabitacion);

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
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
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
			String usuarioIngreso = this.getUsuarioSession().getId();
			cliente.setFechaRegistro(new Date());
			cliente.setUsuarioUIngreso(usuarioIngreso);
			cliente.setHotel(this.getHotelSession().getCodigo());
			ClienteService.getInstance().ingresar(cliente);
			Cliente ClienteIngresado = null;
			if (clienteBusqueda != null) {
				ClienteIngresado = clienteBusqueda;

			} else {
				ClienteIngresado = ClienteService.getInstance().getFindXDocumento(cliente.getDocumento());
			}
			Ckecking ck = new Ckecking();
			ck.setEstado("A");
			ck.setFechaEntrada(fechaEntrada);
			ck.setFechaRegistro(new Date());
			ck.setFechaSalida(fechaSalida);
			ck.setIdCliente(ClienteIngresado.getId());
			ck.setNumeroPersonas(numeroPersonas);
			ck.setUsuario(getUsuarioSession().getId());
            ck.setHotel(this.getHotelSession().getCodigo());
           
            	ck.setHabitacion(habitacionCliente);
            
			CkeckingService.getInstance().ingresar(ck);
			

			Ckecking CkeckingConsultado = CkeckingService.getInstance().getFindXCliente(ClienteIngresado.getId(), "A",this.getHotelSession().getCodigo());
			if (CkeckingConsultado != null) {

				for (String habitacion : habitacionesPickList.getTarget()) {
					String[] datos = habitacion.split("-");
					List<Habitacion> h2 = HabitacionService.getInstance().findXNombre(datos[0]);
					Habitacion hc = h2.get(0);
					HabitacionesChecking h = new HabitacionesChecking(CkeckingConsultado.getId(), hc.getId());
					h.setHotel(this.getHotelSession().getCodigo());
					HabitacionesCkeckingService.getInstance().ingresar(h);
					hc.setEstado("OCU");
					
					HabitacionService.getInstance().actualizar(hc);
				}

				for (Servicio servicio : servicios) {

					Servicio servicioConsultado = null;
					if (servicio.getId() < 1) {
						servicio.setEstado("A");
						servicio.setHotel(this.getHotelSession().getCodigo());
						ServicioService.getInstance().ingresar(servicio);
						servicioConsultado = ServicioService.getInstance()
								.getFindXNombre(servicio.getNombre().toUpperCase());

					} else {
						servicioConsultado = servicio;
					}

					ServiciosCkeking s = new ServiciosCkeking(servicio.getCantidad(), CkeckingConsultado.getId(),
							servicioConsultado.getId());
					s.setHotel(this.getHotelSession().getCodigo());
					ServiciosCkeckingService.getInstance().ingresar(s);
				}
				boolean hayExtrajeros = false;
				
				if ("S".equals(cliente.getExtranjero())) {
					hayExtrajeros = true;

				}
				
				for (Cliente acompanante : acompanantes) {
					acompanante.setHotel(this.getHotelSession().getCodigo());
					ClienteService.getInstance().ingresar(acompanante);
					
					if ("S".equals(acompanante.getExtranjero())) {
						hayExtrajeros = true;
					}
					Cliente acompananteConsultado = ClienteService.getInstance()
							.getFindXDocumento(acompanante.getDocumento());
					
					
					AcompanantesChecking  a= new AcompanantesChecking(CkeckingConsultado.getId(), acompananteConsultado.getId(),acompanante.getHabitacion());
					AcompanantesCkeckingService.getInstance().ingresar(a
							);

				}

				String mensaje = "";
				if (hayExtrajeros) {
					mensaje = "EL cheking se guardo con exito,Recuerde que debe reportar al final de mes los extranjeros";

				} else {

					mensaje = "EL cheking se guardo con exito";
				}
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Grabacion de Checking", mensaje);
				PrimeFaces.current().dialog().showMessageDynamic(message);

			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Grabacion de Checking",
						"error al grabar el checking. valide con el administrador");
				PrimeFaces.current().dialog().showMessageDynamic(message);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

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
		acompanante.setNacionalidad(nacionalidad);

		acompanante.setFechaRegistro(new Date());
		acompanantes.add(acompanante);
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("adicionado", true);
		documento =""; 	
		correo ="";
		nombre ="";
		celular ="";
		extranjero=null;

	}

	public void adicionarServicio() {

		servicioSeleccionado.setCantidad(1);
		servicios.add(servicioSeleccionado);
		servicioSeleccionado = new Servicio();
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("adicionado", true);

	}

	public void editar() {

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
			int contador = 0;
			for (Iterator<Cliente> iterator = listaClientes.iterator(); iterator.hasNext();) {
				Cliente cliente = iterator.next();

				if (cliente.getNombre().toUpperCase().contains(query.toUpperCase())
						|| cliente.getDocumento().toUpperCase().contains(query.toUpperCase())) {

					filteredCliente.add(cliente);
				}

			}

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("listaClientes", filteredCliente);

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
			int contador = 0;
			for (Iterator<Cliente> iterator = listaClientes.iterator(); iterator.hasNext();) {
				Cliente cliente = iterator.next();

				if (cliente.getNombre().toUpperCase().contains(query.toUpperCase())
						|| cliente.getDocumento().toUpperCase().contains(query.toUpperCase())) {

					filteredCliente.add(cliente);
				}

			}

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("listaAcompanantes", filteredCliente);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return filteredCliente;
	}

	public List<Servicio> completeServicio(String query) {

		List<Servicio> listaServicios;
		List<Servicio> filteredServicio = null;
		try {
			listaServicios = ServicioService.getInstance().listar(this.getHotelSession().getCodigo());

			filteredServicio = new ArrayList<Servicio>();
			int contador = 0;
			for (Iterator<Servicio> iterator = listaServicios.iterator(); iterator.hasNext();) {
				Servicio servicio = iterator.next();

				if (servicio.getNombre().toUpperCase().contains(query.toUpperCase())) {
                    servicio.setCantidad(1);
					filteredServicio.add(servicio);
				}

			}

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("listaServicios", filteredServicio);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return filteredServicio;
	}

	public void buscarReserva() {

		cliente = clienteBusqueda;
		Ckecking checking = null;
		try {
			checking = CkeckingService.getInstance().getFindXCliente(cliente.getId(), "A",this.getHotelSession().getCodigo());
			if (checking != null) {
				fechaSalida = checking.getFechaSalida();
				fechaEntrada = checking.getFechaEntrada();
				numeroPersonas = checking.getNumeroPersonas();

				List<String> habitacionesDisponibles = new ArrayList<String>();
				habitacionSeleccionada = HabitacionesCkeckingService.getInstance().getFindXChecking(checking.getId());

				try {
					for (Habitacion i : HabitacionService.getInstance().listarDisponibles()) {

						habitacionesDisponibles
								.add(i.getNombre() + "-Capacidad:" + i.getCapacidad() + "-Precio:" + i.getPrecio());

					}

					for (Habitacion h : habitacionSeleccionada) {

						habitacionesDisponibles
								.remove(h.getNombre() + "-Capacidad:" + h.getCapacidad() + "-Precio:" + h.getPrecio());

					}
					List<String> habitacionesSeleccionados = new ArrayList<String>();

					for (Habitacion h : habitacionSeleccionada) {

						habitacionesSeleccionados
								.add(h.getNombre() + "-Capacidad:" + h.getCapacidad() + "-Precio:" + h.getPrecio());
						
						

					}

					habitacionesPickList = new DualListModel<String>(habitacionesDisponibles,
							habitacionesSeleccionados);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
						.getSession(true);
				session.setAttribute("listaHabitaciones", habitacionSeleccionada);
				

				servicios = ServiciosCkeckingService.getInstance().getInstance().getFindXChecking(checking.getId());
				acompanantes = AcompanantesCkeckingService.getInstance().getFindXChecking(checking.getId());
				for(Cliente c: acompanantes) {
					c.setHabitacion(AcompanantesCkeckingService.getInstance().getFindXHabitacion(checking.getId(), c.getId()));
					
				}
				setHayFactura(true);
			} else {
				FacesContext context = FacesContext.getCurrentInstance();

				context.addMessage(null, new FacesMessage("Mensaje",
						"No hay ningun Checking para la persona:" + cliente.getId() + ":" + cliente.getNombre()));
				setHayFactura(false);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@PostConstruct
	public void init() {

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
		tiposDocumento = new HashMap<String, String>();
		tiposDocumento.put("Cedula", "CC");
		tiposDocumento.put("Pasaporte", "PP");
		tiposDocumento.put("Cedula Extranjeria", "CE");
		tiposDocumento.put("Tarjeta Identidad", "TI");
		tiposDocumento.put("Nit", "NI");
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

	public Cliente getClienteBusqueda() {
		return clienteBusqueda;
	}

	public void setClienteBusqueda(Cliente clienteBusqueda) {
		this.clienteBusqueda = clienteBusqueda;
	}

	public List<Habitacion> getHabitacionSeleccionada() {

		habitacionSeleccionada = new ArrayList<Habitacion>();

		for (String idHabitacion : habitacionesPickList.getTarget()) {
			String[] datos = idHabitacion.split("-");

			try {
				Habitacion x = (HabitacionService.getInstance().findXNombre(datos[0])).get(0);
				habitacionSeleccionada.add(x);
				codigosHabitacion.add(idHabitacion);

			} catch (NumberFormatException e) {

				e.printStackTrace();
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
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

			if (cliente.getDocumento().equals(documento)) {
				clienteBorrar = cliente;
			}

		}
		if (clienteBorrar != null) {
			acompanantes.remove(clienteBorrar);
		}
	}

	public void addAcompananteBuscar() {

		acompanantes.add(acompananteBusqueda);

	}

	public void addServicioBuscar() {

		servicios.add(servicioBusqueda);

	}

	public void borrarServicio(int id) {

		Servicio servicioBorrar = null;
		for (Servicio servicio : servicios) {

			if (servicio.getId() == id) {
				servicioBorrar = servicio;
			}

		}
		if (servicioBorrar != null) {
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

	public void generarChecking() {

		guardar();
		
	}

	public void imprimirChecking() {

	JRBeanCollectionDataSource beancollection = new JRBeanCollectionDataSource(null);
		String realpath = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("ruta_reportes")
				+ "checkingTable.jasper";
		String realpathImagenes = FacesContext.getCurrentInstance().getExternalContext()
				.getInitParameter("ruta_imagenes") + this.getHotelSession().getCodigo() + ".png";
		try {
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("tipoDocumentoCliente", cliente.getTipoDocumento());
			parametros.put("documentoCliente", cliente.getDocumento());
			parametros.put("nombreCliente", cliente.getNombre());
			System.out.println("tipodocumento>>>>>>>>"+ cliente.getTipoDocumento());
			System.out.println("documento>>>>>>>>"+cliente.getDocumento());
			System.out.println("nombre>>>>>>>>"+cliente.getNombre());
			System.out.println("correo>>>>>>>>"+cliente.getCorreo());
		
			
			parametros.put("correo", cliente.getCorreo());
			parametros.put("celular", cliente.getCelular());
			parametros.put("fechaEntrada", fechaEntrada);
			parametros.put("fechaSalida", fechaSalida);
			parametros.put("numeroPersonas", numeroPersonas);
			parametros.put("habitacionCliente",habitacionCliente);
			parametros.put("rutaImagen", realpathImagenes);
			parametros.put("rutaReportes",
					FacesContext.getCurrentInstance().getExternalContext().getInitParameter("ruta_reportes"));

			CheckingDTO checkingDTO = new CheckingDTO();
			checkingDTO.setAcompanantes(acompanantes);

			checkingDTO.setHabitaciones(getHabitacionSeleccionada());
			checkingDTO.setServicios(servicios);
			List<CheckingDTO> lista = new ArrayList<CheckingDTO>();
			lista.add(checkingDTO);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(lista);

			JasperPrint jasperprint = JasperFillManager.fillReport(realpath, parametros, beanColDataSource);
			HttpServletResponse httpservlet = (HttpServletResponse) FacesContext.getCurrentInstance()
					.getExternalContext().getResponse();
			httpservlet.addHeader("Content-disposition", "attachment;filename=checking_"
					+ this.getHotelSession().getCodigo() + "_" + cliente.getDocumento() + "_" + new Date() + ".pdf");
			ServletOutputStream servletout = httpservlet.getOutputStream();
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

	public void generarFactura() {

		JRBeanCollectionDataSource beancollection = new JRBeanCollectionDataSource(null);
		String realpath = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("ruta_reportes")
				+ "FacturaTable.jasper";
		String realpathImagenes = FacesContext.getCurrentInstance().getExternalContext()
				.getInitParameter("ruta_imagenes") + this.getHotelSession().getCodigo() + ".png";
		boolean limpias = false;
		int contadorLimpias = 0;
		String habitacionesLimpiar = ",";

		for (Habitacion habitacion : habitacionSeleccionada) {

			if ("RPF".equals(habitacion.getEstado())) {
				contadorLimpias++;
			} else {
				habitacionesLimpiar += habitacion.getNombre() + ",";
			}

		}

		if (contadorLimpias == habitacionSeleccionada.size() && contadorLimpias > 0) {
			try {

				HashMap<String, Object> parametros = new HashMap<String, Object>();
				Factura factura = new Factura();

				parametros.put("tipoDocumentoCliente", cliente.getTipoDocumento());
				factura.setTipodocumentoCliente(cliente.getTipoDocumento());
				parametros.put("documentoCliente", cliente.getDocumento());
				factura.setDocumentoCliente(cliente.getDocumento());
				parametros.put("nombreCliente", cliente.getNombre());
				factura.setCliente(cliente.getNombre());
				parametros.put("correo", cliente.getCorreo());
				factura.setDireccionCliente(cliente.getCorreo());
				parametros.put("celular", cliente.getCelular());
				factura.setTelefonoCliente(cliente.getCelular());
				parametros.put("fechaEntrada", fechaEntrada);
				factura.setFechaEntrada(fechaEntrada);
				parametros.put("fechaSalida", fechaSalida);
				factura.setFechaSalida(fechaSalida);
				int dias = (int) ((fechaSalida.getTime() - fechaEntrada.getTime()) / 86400000);
				dias = dias + 1;
				parametros.put("dias", (dias));
				Parametro parametroResolucion = ParametroService.getInstance().findXNombreXHotel("resolucion", this.getHotelSession().getCodigo());
				Parametro parametroIVA = ParametroService.getInstance().findXNombre("iva");

				String resolucion = parametroResolucion.getValor();
				int consecutivo = Integer.parseInt(resolucion);
				float iva = Float.parseFloat(parametroIVA.getValor());
				parametros.put("consecutivo", consecutivo);
				factura.setResolucion(resolucion);
				consecutivo++;
				Ckecking ci = CkeckingService.getInstance().getFindXCliente(cliente.getId(), "A",this.getHotelSession().getCodigo());
				factura.setChecking(ci.getId());
				factura.setDireccion(this.getHotelSession().getDireccion());
				factura.setTelefono(this.getHotelSession().getTelefono());
				factura.setNit(this.getHotelSession().getNit());

				parametros.put("numeroPersonas", numeroPersonas);
				parametros.put("rutaImagen", realpathImagenes);
				parametros.put("rutaReportes",
						FacesContext.getCurrentInstance().getExternalContext().getInitParameter("ruta_reportes"));

				float subtotal = 0;
				for (Servicio servicio : servicios) {

					subtotal += servicio.getCantidad() * servicio.getValor();

				}
				for (Habitacion habitacion : habitacionSeleccionada) {

					subtotal += habitacion.getPrecio() * dias;

				}

				List<InsumosChecking> listai = InsumosCheckingService.getInstance().findXChecking(ci.getId());
				List<InsumoDTO> listainsumos = new ArrayList<InsumoDTO>();

				for (InsumosChecking insumosChecking : listai) {
					Insumo i = InsumoService.getInstance().find(insumosChecking.getIdInsumo());
					subtotal += (i.getValor() * insumosChecking.getCantidad());
					InsumoDTO u = new InsumoDTO();
					u.setCantidad(insumosChecking.getCantidad());
					u.setNombre(i.getNombre());
					u.setValor(i.getValor());
					listainsumos.add(u);
					

				}
                float total = subtotal;
                float denom = (1 + (iva/100));
				subtotal = total /denom;
              
                parametros.put("subtotal", subtotal);
				float ivaFactura = total-subtotal;
				parametros.put("iva",ivaFactura);
                parametros.put("total", total);
				
				
				factura.setTotal(subtotal);
				factura.setHotel(this.getHotelSession().getCodigo());
				factura.setFecha(fechaEntrada);
				factura.setRazonSocial(this.getHotelSession().getNomgre());
				parametroResolucion.setValor((consecutivo) + "");
				parametroResolucion.setHotel(this.getHotelSession().getCodigo());
				ParametroService.getInstance().actualizar(parametroResolucion);

				FacturaService.getInstance().ingresar(factura);
				ci.setEstado("I");
				ci.setHotel(this.getHotelSession().getCodigo());
				CkeckingService.getInstance().actualizar(ci);
				for (Habitacion habitacion : habitacionSeleccionada) {

					habitacion.setEstado("FAC");
				
					HabitacionService.getInstance().actualizar(habitacion);

				}

				CheckingDTO checkingDTO = new CheckingDTO();
				checkingDTO.setHabitaciones(habitacionSeleccionada);
				checkingDTO.setServicios(servicios);
				checkingDTO.setInsumos(listainsumos);

				List<CheckingDTO> lista = new ArrayList<CheckingDTO>();
				lista.add(checkingDTO);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(lista);

				JasperPrint jasperprint = JasperFillManager.fillReport(realpath, parametros, beanColDataSource);
				HttpServletResponse httpservlet = (HttpServletResponse) FacesContext.getCurrentInstance()
						.getExternalContext().getResponse();
				httpservlet.addHeader("Content-disposition",
						"attachment;filename=recibo_" + cliente.getDocumento() + new Date() + ".pdf");
				ServletOutputStream servletout = httpservlet.getOutputStream();
				JasperExportManager.exportReportToPdfStream(jasperprint, servletout);
				FacesContext.getCurrentInstance().responseComplete();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			habitacionesLimpiar = habitacionesLimpiar.substring(1);
			FacesContext context = FacesContext.getCurrentInstance();
			if (habitacionSeleccionada.isEmpty()) {
				context.addMessage(null, new FacesMessage("Error", " No hay ninguna habitación para facturar"));

			} else {

				context.addMessage(null, new FacesMessage("Error",
						" Falta reportar el inventario de la(s) habitacion(es) :" + habitacionesLimpiar +"en el modulo de limpieza"));

			}
		}
	}

	public DualListModel<String> getHabitacionesPickList() {
		return habitacionesPickList;
	}

	public void setHabitacionesPickList(DualListModel<String> habitacionesPickList) {
		this.habitacionesPickList = habitacionesPickList;
	}

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

	public boolean isHayFactura() {
		return hayFactura;
	}

	public void setHayFactura(boolean hayFactura) {
		this.hayFactura = hayFactura;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	  public void onCellEdit(CellEditEvent event) {
	        Object oldValue = event.getOldValue();
	        Object newValue = event.getNewValue();
	  
		        
	    	   
	        if(newValue != null && !newValue.equals(oldValue)) {
	            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
	            FacesContext.getCurrentInstance().addMessage(null, msg);
	        }
	    }
	  
	  public void onRowEdit(RowEditEvent event) {	
		  
	        
	    }

	public List<String> getCodigosHabitacion() {
	

		if(!codigosHabitacion.isEmpty()){
			codigosHabitacion.clear();
		}
		
			
			
		habitacionSeleccionada = new ArrayList<Habitacion>();

			for (String idHabitacion : habitacionesPickList.getTarget()) {
				String[] datos = idHabitacion.split("-");

				try {
					Habitacion x = (HabitacionService.getInstance().findXNombre(datos[0])).get(0);
					codigosHabitacion.add(idHabitacion);

				} catch (NumberFormatException e) {

					e.printStackTrace();
				} catch (Exception e) {

					e.printStackTrace();
				}

			}
		
			
		return codigosHabitacion;
	}

	public void setCodigosHabitacion(List<String> codigosHabitacion) {
		this.codigosHabitacion = codigosHabitacion;
	}

	public String getHabitacionCliente() {
		return habitacionCliente;
	}

	public void setHabitacionCliente(String habitacionCliente) {
		this.habitacionCliente = habitacionCliente;
	} 
}