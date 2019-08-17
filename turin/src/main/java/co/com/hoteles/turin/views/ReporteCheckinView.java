package co.com.hoteles.turin.views;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import co.com.hoteles.turin.dtos.CheckinDTO;
import co.com.hoteles.turin.dtos.CheckingDTO;
import co.com.hoteles.turin.dtos.VentaDTO;
import co.com.hoteles.turin.entities.Cliente;
import co.com.hoteles.turin.services.CkeckingService;
import co.com.hoteles.turin.services.ClienteService;
import co.com.hoteles.turin.services.FacturaService;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean
@ViewScoped
public class ReporteCheckinView extends  GenericBB {

	private Date fechaInicio;
	private Date fechaFin;

	private String mensaje;
	private List<Cliente> listaClientes;
	private List<CheckinDTO> listaCheckin;

	public List<CheckinDTO> getListaCheckin() {
		return listaCheckin;
	}

	public void setListaCheckin(List<CheckinDTO> listaCheckin) {
		this.listaCheckin = listaCheckin;
	}

	private Date todayDate = new Date();


	
	public ReporteCheckinView(){
		
	}
	  
	public void buscar() {

	    ClienteService clienteService = ClienteService.getInstance();
		try {
			
           setListaClientes(clienteService.listarExtranjeros("S", fechaInicio, fechaFin,this.getHotelSession().getCodigo()));

		} catch (Exception e) {

			e.printStackTrace();
		}
	}


	public void buscarVentas() {

	    CkeckingService facturaService = CkeckingService.getInstance();
		try {
			
           listaCheckin = facturaService.listarVentas(fechaInicio, fechaFin, this.getHotelSession().getCodigo());

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


	public List<Cliente> getListaClientes() {
		return listaClientes;
	}


	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getTodayDate() {
		return todayDate;
	}

	public void setTodayDate(Date todayDate) {
		this.todayDate = todayDate;
	}     

	public  void generarVentas() {

		String realpath=FacesContext.getCurrentInstance().getExternalContext().getInitParameter("ruta_reportes")+"checkinsTable.jasper";
		String realpathImagenes=FacesContext.getCurrentInstance().getExternalContext().getInitParameter("ruta_imagenes")+this.getHotelSession().getCodigo()+".png";


		try {

			HashMap<String,Object> parametros =new HashMap<String,Object>();

			parametros.put("rutaImagen",realpathImagenes );
			parametros.put("rutaReportes",FacesContext.getCurrentInstance().getExternalContext().getInitParameter("ruta_reportes") );
			parametros.put("telefono", this.getHotelSession().getTelefono());
			parametros.put("direccion", this.getHotelSession().getDireccion());
			parametros.put("sitio", this.getHotelSession().getRedessociales());
			parametros.put("correo", this.getHotelSession().getRedessociales());
			parametros.put("fechaInicio", fechaInicio);
			parametros.put("fechaFin", fechaFin);

			parametros.put("titulo", "Reporte de ventas por fecha");
			parametros.put("nombreHotel", this.getHotelSession().getNomgre());


			CheckingDTO checkingDTO = new CheckingDTO();
			listaCheckin= CkeckingService.getInstance().listarVentas(fechaInicio, fechaFin,this.getHotelSession().getCodigo());
					
			checkingDTO.setCheckins(listaCheckin);
			
			float totalVenta = 0;
			
			for (CheckinDTO venta : listaCheckin) {
				
				totalVenta = totalVenta + venta.getValor();
				
			}
			parametros.put("total", totalVenta);


			List<CheckingDTO> lista = new ArrayList<CheckingDTO>();
			lista.add(checkingDTO);

			JRBeanCollectionDataSource beanColDataSource =  new JRBeanCollectionDataSource(lista);

			JasperPrint jasperprint=JasperFillManager.fillReport(realpath,parametros,beanColDataSource);
			HttpServletResponse httpservlet=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
			httpservlet.addHeader("Content-disposition", "attachment;filename=ingresos_checkins_"+new Date()+".pdf");
			ServletOutputStream servletout=httpservlet.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperprint, servletout);
			FacesContext.getCurrentInstance().responseComplete();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	
}