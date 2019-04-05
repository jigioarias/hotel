package co.com.hoteles.turin.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.hoteles.turin.entities.Cliente;
import co.com.hoteles.turin.entities.Habitacion;
import co.com.hoteles.turin.entities.HabitacionesChecking;
import co.com.hoteles.turin.entities.Servicio;
import co.com.hoteles.turin.entities.ServiciosCkeking;
import co.com.hoteles.turin.reportes.JPAUtility;

public class ServicioService {
	
	private static ServicioService servicioService;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private ServicioService() {
       
    }

    public static ServicioService getInstance() {
        if (servicioService == null){
        	servicioService = new ServicioService();
        }
       
        
        return servicioService;
    }
    
	public void ingresar(Servicio servicio)throws Exception {
		
		EntityManager em = JPAUtility.getEntityManager();
	    em.getTransaction().begin();
		em.persist(servicio);
	    em.getTransaction().commit();
	    em.close();
	   }
	
	
   public List<Servicio> listar()throws Exception {
		
	 EntityManager em = JPAUtility.getEntityManager();
	Query query = em.createNamedQuery("Servicio.findAll");
	
	  List<Servicio> results = query.getResultList();
 
	  return results;
	  }

   public Servicio get(int id)throws Exception {
		
		 EntityManager em = JPAUtility.getEntityManager();
  		
		 return em.find(Servicio.class, id);
	 
    }
   
   
   public void actualizar(Servicio servicio)throws Exception {
		
		EntityManager em = JPAUtility.getEntityManager();
	    em.getTransaction().begin();
		em.merge(servicio);
	    em.getTransaction().commit();
	    em.close();
	   }
   
   
   public List<Servicio> getFindXChecking(int idCkecking) throws Exception{
		
 		EntityManager em = JPAUtility.getEntityManager();
 		Query query = em.createNamedQuery("ServiciosChecking.findxCkecking");
 		query.setParameter("id", idCkecking);
 		List<ServiciosCkeking>  results =  query.getResultList();
 		List<Servicio> servicios = new ArrayList<Servicio>();
 	    for (ServiciosCkeking serviciosCkeking : results) {
 		 servicios.add(ServicioService.getInstance().find(serviciosCkeking.getId()));
 		}
 	    
 	     return servicios;

 	}
  
   
   public Servicio find(int id) throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
	    return em.find(Servicio.class, id);

	}

   
   public Servicio getFindXNombre(String nombre) throws Exception{
		
	   
		  try {
			  EntityManager em = JPAUtility.getEntityManager();
				Query query = em.createNamedQuery("Servicio.findXNombre");
				query.setParameter("nombre", nombre.toUpperCase());
				
				Servicio  results = (Servicio) query.getSingleResult();
			    
				return results;
		} catch (Exception e) {
			
			 e.printStackTrace();
		     return null;
		}
		  
}
}   
  
