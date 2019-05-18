package co.com.hoteles.turin.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.hoteles.turin.entities.Hotel;
import co.com.hoteles.turin.reportes.JPAUtility;

public class HotelService {
	

	private static HotelService hotelService;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private HotelService() {
       
    }

    public static HotelService getInstance() {
        if (hotelService == null){
        	hotelService = new HotelService();
        }
       
        
        return hotelService;
    }
	
	public List<Hotel> listar() throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
		Query query = em.createNamedQuery("Hotel.findAll");
		List<Hotel> results = query.getResultList();
	    return results;

	}
	

	
	   public void actualizar(Hotel Hotel)throws Exception {
			
			EntityManager em = JPAUtility.getEntityManager();
		    em.getTransaction().begin();
			em.merge(Hotel);
		    em.getTransaction().commit();
		    em.close();
		   }
	   
	
	
public Hotel find(int id) throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
	    return em.find(Hotel.class, id);

	}


}
