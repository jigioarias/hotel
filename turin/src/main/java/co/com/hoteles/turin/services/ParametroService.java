package co.com.hoteles.turin.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.hoteles.turin.entities.Cliente;
import co.com.hoteles.turin.entities.Habitacion;
import co.com.hoteles.turin.entities.Parametro;
import co.com.hoteles.turin.reportes.JPAUtility;

public class ParametroService {
	

	private static ParametroService parametroService;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private ParametroService() {
       
    }

    public static ParametroService getInstance() {
        if (parametroService == null){
        	parametroService = new ParametroService();
        }
       
        
        return parametroService;
    }
	
	public List<Parametro> listar(int hotel) throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
		Query query = em.createNamedQuery("Parametro.findAll");
		query.setParameter("hotel", hotel);
		List<Parametro> results = query.getResultList();
	    return results;

	}
	

	
	   public void actualizar(Parametro Parametro)throws Exception {
			
			EntityManager em = JPAUtility.getEntityManager();
		    em.getTransaction().begin();
			em.merge(Parametro);
		    em.getTransaction().commit();
		    em.close();
		   }
	   
	
	
public Parametro find(int id) throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
	    return em.find(Parametro.class, id);

	}

public Parametro findXHotel(int id) throws Exception{
	
	EntityManager em = JPAUtility.getEntityManager();
    return em.find(Parametro.class, id);

}


public Parametro findXNombre(String nombre) throws Exception{
	
	EntityManager em = JPAUtility.getEntityManager();
	Query query = em.createNamedQuery("Parametro.findNombre");
	query.setParameter("nombre", nombre);
	Parametro results = (Parametro) query.getSingleResult();
    return results;
    
}

public Parametro findXNombreXHotel(String nombre, int hotel) throws Exception{
	
	EntityManager em = JPAUtility.getEntityManager();
	Query query = em.createNativeQuery("SELECT * FROM hturin.parametros where nombreParametro=? and hotel=?",Parametro.class);
	query.setParameter(1, nombre);
	query.setParameter(2, hotel);
	
	Parametro results = (Parametro) query.getSingleResult();
    return results;
    
}

}
