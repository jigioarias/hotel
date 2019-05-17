package co.com.hoteles.turin.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.hoteles.turin.entities.Rol;
import co.com.hoteles.turin.reportes.JPAUtility;

public class RolService {
	

	private static RolService rolService;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private RolService() {
       
    }

    public static RolService getInstance() {
        if (rolService == null){
        	rolService = new RolService();
        }
       
        
        return rolService;
    }
	
	public List<Rol> listar() throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
		Query query = em.createNamedQuery("Rol.findAll");
		List<Rol> results = query.getResultList();
	    return results;

	}
	

	
	   public void actualizar(Rol Rol)throws Exception {
			
			EntityManager em = JPAUtility.getEntityManager();
		    em.getTransaction().begin();
			em.merge(Rol);
		    em.getTransaction().commit();
		    em.close();
		   }
	   
	
	
public Rol find(String id) throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
	    return em.find(Rol.class, id);

	}


}
