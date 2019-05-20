package co.com.hoteles.turin.views;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import co.com.hoteles.turin.dtos.CheckingDTO;
import co.com.hoteles.turin.dtos.VentaDTO;
import co.com.hoteles.turin.entities.Cliente;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;



@ManagedBean(name="reportesView")
@ViewScoped
public class ReportesView extends GenericBB implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date FechaInicial;
	private Date fechaFinal;



	public Date getFechaInicial() {
		return FechaInicial;
	}








	public void setFechaInicial(Date fechaInicial) {
		FechaInicial = fechaInicial;
	}








	public Date getFechaFinal() {
		return fechaFinal;
	}








	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}








	public List<Cliente> getExtranjeros() {
		return extranjeros;
	}








	public void setExtranjeros(List<Cliente> extranjeros) {
		this.extranjeros = extranjeros;
	}








	public List<VentaDTO> getVentas() {
		return ventas;
	}








	public void setVentas(List<VentaDTO> ventas) {
		this.ventas = ventas;
	}






	private List<Cliente> extranjeros; 
	private List<VentaDTO> ventas; 






	public ReportesView(){



	}








	@PostConstruct
	public void init(){


       ventas = new ArrayList<VentaDTO>();
       extranjeros = new ArrayList<Cliente>();

	}






	public  void generarExtranjeros() {

		String realpath=FacesContext.getCurrentInstance().getExternalContext().getInitParameter("ruta_reportes")+"extranjeroTable.jasper";
		String realpathImagenes=FacesContext.getCurrentInstance().getExternalContext().getInitParameter("ruta_imagenes")+this.getHotelSession().getCodigo()+".png";


		try {

			HashMap<String,Object> parametros =new HashMap<String,Object>();

			parametros.put("rutaImagen",realpathImagenes );
			parametros.put("rutaReportes",FacesContext.getCurrentInstance().getExternalContext().getInitParameter("ruta_reportes") );
			parametros.put("telefono", this.getHotelSession().getTelefono());
			parametros.put("direccion", this.getHotelSession().getDireccion());
			parametros.put("sitio", this.getHotelSession().getRedessociales());
			parametros.put("correo", this.getHotelSession().getRedessociales());
			parametros.put("titulo", "Reporte de Extranjeros");
			parametros.put("nombreHotel", "Reporte de Extranjeros");


			CheckingDTO checkingDTO = new CheckingDTO();
			checkingDTO.setVentas(ventas);


			List<CheckingDTO> lista = new ArrayList<CheckingDTO>();
			lista.add(checkingDTO);

			JRBeanCollectionDataSource beanColDataSource =  new JRBeanCollectionDataSource(lista);

			JasperPrint jasperprint=JasperFillManager.fillReport(realpath,parametros,beanColDataSource);
			HttpServletResponse httpservlet=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
			httpservlet.addHeader("Content-disposition", "attachment;filename=extranjeros_"+new Date()+".pdf");
			ServletOutputStream servletout=httpservlet.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperprint, servletout);
			FacesContext.getCurrentInstance().responseComplete();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public  void generarVentas() {

		String realpath=FacesContext.getCurrentInstance().getExternalContext().getInitParameter("ruta_reportes")+"facturasTable.jasper";
		String realpathImagenes=FacesContext.getCurrentInstance().getExternalContext().getInitParameter("ruta_imagenes")+this.getHotelSession().getCodigo()+".png";


		try {

			HashMap<String,Object> parametros =new HashMap<String,Object>();

			parametros.put("rutaImagen",realpathImagenes );
			parametros.put("rutaReportes",FacesContext.getCurrentInstance().getExternalContext().getInitParameter("ruta_reportes") );
			parametros.put("telefono", this.getHotelSession().getTelefono());
			parametros.put("direccion", this.getHotelSession().getDireccion());
			parametros.put("sitio", this.getHotelSession().getRedessociales());
			parametros.put("correo", this.getHotelSession().getRedessociales());
			parametros.put("titulo", "Reporte de Extranjeros");
			parametros.put("nombreHotel", "Reporte de Extranjeros");


			CheckingDTO checkingDTO = new CheckingDTO();
			checkingDTO.setVentas(ventas);



			List<CheckingDTO> lista = new ArrayList<CheckingDTO>();
			lista.add(checkingDTO);

			JRBeanCollectionDataSource beanColDataSource =  new JRBeanCollectionDataSource(lista);

			JasperPrint jasperprint=JasperFillManager.fillReport(realpath,parametros,beanColDataSource);
			HttpServletResponse httpservlet=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
			httpservlet.addHeader("Content-disposition", "attachment;filename=ventas_"+new Date()+".pdf");
			ServletOutputStream servletout=httpservlet.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperprint, servletout);
			FacesContext.getCurrentInstance().responseComplete();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}



}