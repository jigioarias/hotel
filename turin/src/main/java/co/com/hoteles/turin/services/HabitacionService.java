package co.com.hoteles.turin.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.hoteles.turin.entities.Cliente;
import co.com.hoteles.turin.entities.Habitacion;
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
	
public List<Habitacion> listarOcupadas() throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
		Query query = em.createNamedQuery("Habitacion.findEstado");
		query.setParameter("estado", "OCU");
		List<Habitacion> results = query.getResultList();
	    return results;

	}
	
	   public void actualizar(Habitacion habitacion)throws Exception {
			
			EntityManager em = JPAUtility.getEntityManager();
		    em.getTransaction().begin();
			em.merge(habitacion);
		    em.getTransaction().commit();
		    em.close();
		   }
	   
	
	
public Habitacion find(int id) throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
	    return em.find(Habitacion.class, id);

	}

}
