package co.com.hoteles.turin.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.hoteles.turin.dtos.CheckinDTO;
import co.com.hoteles.turin.entities.Cliente;
import co.com.hoteles.turin.entities.Habitacion;
import co.com.hoteles.turin.entities.Insumo;
import co.com.hoteles.turin.entities.Insumoshabitacion;
import co.com.hoteles.turin.entities.InsumoshabitacionPK;
import co.com.hoteles.turin.reportes.JPAUtility;

public class HabitacionService {
	

	private static HabitacionService habitacionService;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private HabitacionService() {
       
    }

    public static HabitacionService getInstance() {
        if (habitacionService == null){
        	habitacionService = new HabitacionService();
        }
       
        
        return habitacionService;
    }
	
	public List<Habitacion> listarDisponibles(int hotel) throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
		Query query = em.createNamedQuery("Habitacion.findEstado");
		query.setParameter("estado", "DIS");
		query.setParameter("hotel", hotel);
		
		List<Habitacion> results = query.getResultList();
	    return results;

	}
	
	
public List<Habitacion> listar(int hotel) throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
		Query query = em.createNamedQuery("Habitacion.findAll");
		query.setParameter("hotel", hotel);
		List<Habitacion> results = query.getResultList();
	    return results;

	}

public List<Habitacion> listar(String estado, int hotel) throws Exception{
	
	EntityManager em = JPAUtility.getEntityManager();
	Query query = em.createNamedQuery("Habitacion.findEstado");
	query.setParameter("estado", estado);
	query.setParameter("hotel", hotel);

	List<Habitacion> results = query.getResultList();
    return results;

}
public List<Habitacion> listarOcupadas(int hotel) throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
		Query query = em.createNamedQuery("Habitacion.findEstado");
		query.setParameter("hotel", hotel);
		query.setParameter("estado", "OCU");
		List<Habitacion> results = query.getResultList();
	    return results;

	}
	
	   public void actualizar(Habitacion habitacion,int hotel)throws Exception {
			
		   List<Habitacion> habitaciones =findXNombre(habitacion.getNombre(),hotel);
		    EntityManager em = JPAUtility.getEntityManager();
		    em.getTransaction().begin();
		    
		    if(!habitaciones.isEmpty()) {
		     Habitacion habitacionConsultada=  habitaciones.get(0);
			 habitacionConsultada.setCapacidad(habitacion.getCapacidad());
		     habitacionConsultada.setPrecio(habitacion.getPrecio());
		     habitacionConsultada.setNombre(habitacion.getNombre());
		     habitacionConsultada.setDescripcion(habitacion.getDescripcion());
		     habitacionConsultada.setEstado(habitacion.getEstado());
		     em.merge(habitacionConsultada);
		    
		   }else {

			   em.persist(habitacion);
		   }
	       em.getTransaction().commit();
		    em.close();
	   
	   }
	
	   
	   public void actualizar(Habitacion habitacion,List<String> listaInsumos,int hotel)throws Exception {
			
		   List<Habitacion> habitaciones =findXNombre(habitacion.getNombre(),hotel);
		    EntityManager em = JPAUtility.getEntityManager();
		    em.getTransaction().begin();
		    InsumoHabitacionService insumoHabitacionService= InsumoHabitacionService.getInstance();
		    
		    if(!habitaciones.isEmpty()) {
		     Habitacion habitacionConsultada=  habitaciones.get(0);
			 habitacionConsultada.setCapacidad(habitacion.getCapacidad());
		     habitacionConsultada.setPrecio(habitacion.getPrecio());
		     habitacionConsultada.setNombre(habitacion.getNombre());
		     habitacionConsultada.setDescripcion(habitacion.getDescripcion());
		     habitacionConsultada.setEstado(habitacion.getEstado());
		     em.merge(habitacionConsultada);
		     em.getTransaction().commit();
			 em.close();
             
		     
			 for (String insumo : listaInsumos) {
				   Insumoshabitacion insumoshabitacion = new Insumoshabitacion();
				   insumoshabitacion.setCantidad(1);
				   InsumoshabitacionPK insumoshabitacionPK = new InsumoshabitacionPK();
				   insumoshabitacionPK.setHabitacion(habitacionConsultada.getId());
			       Insumo insumoConsultado=(InsumoService.getInstance().findXNombre(insumo)).get(0);
				   insumoshabitacionPK.setInsumo(insumoConsultado.getCodigo());
				   insumoshabitacion.setId(insumoshabitacionPK);
				   insumoHabitacionService.ingresar(insumoshabitacion);
			  }
		    
		   }else {

			   em.persist(habitacion);
			   em.getTransaction().commit();
			    em.close();
			   List<Habitacion> habitacionesInsertadas =findXNombre(habitacion.getNombre(),hotel);
			   
			   Habitacion habitacionInsertada=  habitacionesInsertadas.get(0);
			   
			   for (String insumo : listaInsumos) {
				   Insumoshabitacion insumoshabitacion = new Insumoshabitacion();
				   insumoshabitacion.setCantidad(1);
				   InsumoshabitacionPK insumoshabitacionPK = new InsumoshabitacionPK();
				   insumoshabitacionPK.setHabitacion(habitacionInsertada.getId());
				   Insumo insumoConsultado=(InsumoService.getInstance().findXNombre(insumo)).get(0);
				   insumoshabitacionPK.setInsumo(insumoConsultado.getCodigo());
				   insumoshabitacion.setId(insumoshabitacionPK);
				   insumoHabitacionService.ingresar(insumoshabitacion);
			   }
			  
			   
			   
		   }
	     
	   
	   }
public Habitacion find(int id) throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
	    return em.find(Habitacion.class, id);

	}

public List<Habitacion> findXNombre(String nombre,int hotel) throws Exception{
	
	EntityManager em = JPAUtility.getEntityManager();
	Query query = em.createNamedQuery("Habitacion.findNombre");
	query.setParameter("nombre", nombre);
	query.setParameter("hotel",hotel);
	List<Habitacion> results = query.getResultList();
    return results;
    
}


public String listarHabitacionesXCheckin(int checkin, int hotel) throws Exception{
	try {
	    String lista = "";
		EntityManager em = JPAUtility.getEntityManager();
		Query query = em.createNativeQuery("select h.nombre,precio from habitaciones_checking hc, habitacion h, ckecking  "+ 
				"c where   "+ 
				"c.id = hc.id_ckecking  "+ 
				"and hc.id_habitacion = h.id  "+ 
				"and c.id = ? " +
				" and c.hotel = ?");
		query.setParameter(1, checkin);
		query.setParameter(2, hotel);

		List<Object[]> habitaciones =  query.getResultList();
		if (habitaciones==null || habitaciones.isEmpty()) {
		    return ""; // handle no-results case
		} else {
				
			for (Object[] r : habitaciones) {
				
				lista = lista+","+ r[0].toString();
				
		
			}
				
				
				
		}
		
		return lista.substring(1);
		 

	} catch (Exception e) {

		e.printStackTrace();
		return "";
	}


}

}
