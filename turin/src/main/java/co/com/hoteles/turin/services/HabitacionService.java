package co.com.hoteles.turin.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	
	public List<Habitacion> listarDisponibles() throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
		Query query = em.createNamedQuery("Habitacion.findEstado");
		query.setParameter("estado", "DIS");
		List<Habitacion> results = query.getResultList();
	    return results;

	}
	
	
public List<Habitacion> listar() throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
		Query query = em.createNamedQuery("Habitacion.findAll");
		List<Habitacion> results = query.getResultList();
	    return results;

	}

public List<Habitacion> listar(String estado) throws Exception{
	
	EntityManager em = JPAUtility.getEntityManager();
	Query query = em.createNamedQuery("Habitacion.findEstado");
	query.setParameter("estado", estado);
	List<Habitacion> results = query.getResultList();
    return results;

}
public List<Habitacion> listarOcupadas() throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
		Query query = em.createNamedQuery("Habitacion.findEstado");
		query.setParameter("estado", "OCU");
		List<Habitacion> results = query.getResultList();
	    return results;

	}
	
	   public void actualizar(Habitacion habitacion)throws Exception {
			
		   List<Habitacion> habitaciones =findXNombre(habitacion.getNombre());
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
	
	   
	   public void actualizar(Habitacion habitacion,List<String> listaInsumos)throws Exception {
			
		   List<Habitacion> habitaciones =findXNombre(habitacion.getNombre());
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
			   List<Habitacion> habitacionesInsertadas =findXNombre(habitacion.getNombre());
			   
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

public List<Habitacion> findXNombre(String nombre) throws Exception{
	
	EntityManager em = JPAUtility.getEntityManager();
	Query query = em.createNamedQuery("Habitacion.findNombre");
	query.setParameter("nombre", nombre);
	List<Habitacion> results = query.getResultList();
    return results;
    
}

}
