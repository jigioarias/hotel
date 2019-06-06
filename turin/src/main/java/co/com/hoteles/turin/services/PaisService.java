package co.com.hoteles.turin.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.hoteles.turin.entities.Pais;
import co.com.hoteles.turin.reportes.JPAUtility;

public class PaisService {
	
	private static PaisService paisService;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private PaisService() {
       
    }

    public static PaisService getInstance() {
        if (paisService == null){
        	paisService = new PaisService();
        }
       
        
        return paisService;
    }
    
	
   public List<Pais> listar()throws Exception {
		
	 EntityManager em = JPAUtility.getEntityManager();
	Query query = em.createNamedQuery("Pais.findAll");
	
	  List<Pais> results = query.getResultList();
 
	  return results;
	  }

		  
}   
  
