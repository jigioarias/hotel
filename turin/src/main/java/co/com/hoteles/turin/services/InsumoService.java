package co.com.hoteles.turin.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.hoteles.turin.entities.Cliente;
import co.com.hoteles.turin.entities.Insumo;
import co.com.hoteles.turin.reportes.JPAUtility;

public class InsumoService {
	

	private static InsumoService insumoService;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private InsumoService() {
       
    }

    public static InsumoService getInstance() {
        if (insumoService == null){
        	insumoService = new InsumoService();
        }
       
        
        return insumoService;
    }
	
	public List<Insumo> listar() throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
		Query query = em.createNamedQuery("Insumo.findAll");
		List<Insumo> results = query.getResultList();
	    return results;

	}
	

	
	   public void actualizar(Insumo Insumo)throws Exception {
			
			EntityManager em = JPAUtility.getEntityManager();
		    em.getTransaction().begin();
			em.merge(Insumo);
		    em.getTransaction().commit();
		    em.close();
		   }
	   
	
	
public Insumo find(int id) throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
	    return em.find(Insumo.class, id);

	}

}
