package co.com.hoteles.turin.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.hoteles.turin.entities.Ckecking;
import co.com.hoteles.turin.entities.Cliente;
import co.com.hoteles.turin.entities.Habitacion;
import co.com.hoteles.turin.entities.HabitacionesChecking;
import co.com.hoteles.turin.reportes.JPAUtility;

public class HabitacionesCkeckingService {
	
	private static HabitacionesCkeckingService habitacionesCheckingService;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private HabitacionesCkeckingService() {
       
    }

    public static HabitacionesCkeckingService getInstance() {
        if (habitacionesCheckingService == null){
        	habitacionesCheckingService = new HabitacionesCkeckingService();
        }
       
        
        return habitacionesCheckingService;
    }
    
	public void ingresar(HabitacionesChecking habitacionesCkecking)throws Exception {
		
		EntityManager em = JPAUtility.getEntityManager();
		List<HabitacionesChecking> lista = getFindXCheckingxHabitacion(habitacionesCkecking.getIdCkecking(), habitacionesCkecking.getIdHabitacion());
	   if (lista==null || lista.size()==0) {
		em.getTransaction().begin();
	    em.persist(habitacionesCkecking);
	    em.getTransaction().commit();
	    em.close();
	    }
	   }
	
	
   
   
   
   public void actualizar(Ckecking Ckecking)throws Exception {
		
		EntityManager em = JPAUtility.getEntityManager();
	    em.getTransaction().begin();
		em.merge(Ckecking);
	    em.getTransaction().commit();
	    em.close();
	   }
   
   
   
   public List<Habitacion> getFindXChecking(int idCkecking) throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
		Query query = em.createNamedQuery("HabitacionesChecking.findxCkecking");
		query.setParameter("id", idCkecking);
		List<HabitacionesChecking>  results =  query.getResultList();
		List<Habitacion> habitaciones = new ArrayList<Habitacion>();
	    for (HabitacionesChecking habitacionesChecking : results) {
		 habitaciones.add(HabitacionService.getInstance().find(habitacionesChecking.getIdHabitacion()));
		}
	    
	     return habitaciones;

	}
   
   public List<HabitacionesChecking> getFindXCheckingxHabitacion(int idCkecking,int idHabitacion) throws Exception{
		
 		EntityManager em = JPAUtility.getEntityManager();
 		Query query = em.createNamedQuery("HabitacionesChecking.findxCkeckingxHabitacion");
 		query.setParameter("idCkecking", idCkecking);
 		query.setParameter("idHabitacion", idHabitacion);
 		List<HabitacionesChecking>  results =  query.getResultList();
 	 
 	     return results;

 	}
   
   
   public int getFindXHabitacion(int idHabitacion,int hotel) throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
		Query query = em.createNamedQuery("HabitacionesChecking.findxHabitacion");
		query.setParameter("idHabitacion", idHabitacion);
		int  results =  (Integer) query.getSingleResult();
	 
	     return results;

	}
    
}
