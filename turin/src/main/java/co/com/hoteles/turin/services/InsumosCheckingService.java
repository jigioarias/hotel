package co.com.hoteles.turin.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.hoteles.turin.entities.Cliente;
import co.com.hoteles.turin.entities.Habitacion;
import co.com.hoteles.turin.entities.Insumo;
import co.com.hoteles.turin.entities.InsumosChecking;
import co.com.hoteles.turin.reportes.JPAUtility;

public class InsumosCheckingService {
	

	private static InsumosCheckingService insumosCheckingService;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private InsumosCheckingService() {
       
    }

    public static InsumosCheckingService getInstance() {
        if (insumosCheckingService == null){
        	insumosCheckingService = new InsumosCheckingService();
        }
       
        
        return insumosCheckingService;
    }
	
	public List<InsumosChecking> listar() throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
		Query query = em.createNamedQuery("InsumosChecking.findAll");
		List<InsumosChecking> results = query.getResultList();
	    return results;

	}
	

	
	   public void actualizar(InsumosChecking insumosChecking)throws Exception {
			
			EntityManager em = JPAUtility.getEntityManager();
		    em.getTransaction().begin();
			em.merge(insumosChecking);
		    em.getTransaction().commit();
		    em.close();
		   }
	   
	
	
public Insumo find(int id) throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
	    return em.find(Insumo.class, id);

	}

public List<InsumosChecking> findXChecking(int id) throws Exception{
	
	EntityManager em = JPAUtility.getEntityManager();
	Query query = em.createNamedQuery("InsumoChecking.findChecking");
	query.setParameter("idCheking", id);
	List<InsumosChecking> results = query.getResultList();
    return results;
    
}

}
