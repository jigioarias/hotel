package co.com.hoteles.turin.services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.hoteles.turin.entities.Cliente;
import co.com.hoteles.turin.entities.Habitacion;
import co.com.hoteles.turin.reportes.JPAUtility;

public class ClienteService {
	
	private static ClienteService clienteService;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private ClienteService() {
       
    }

    public static ClienteService getInstance() {
        if (clienteService == null){
        	clienteService = new ClienteService();
        }
       
        
        return clienteService;
    }
    
	public void ingresar(Cliente cliente)throws Exception {
		
		EntityManager em = JPAUtility.getEntityManager();
		Cliente ClienteConsultado = getFindXDocumento(cliente.getDocumento());
	    
		em.getTransaction().begin();
	    if(ClienteConsultado==null) {
			em.persist(cliente);
	    	
	    }else {
	    	em.merge(cliente);
	    }
	    em.getTransaction().commit();
	    em.close();
	   }
	
	
   public List<Cliente> listar()throws Exception {
		
	 EntityManager em = JPAUtility.getEntityManager();
	Query query = em.createNamedQuery("Cliente.findAll");
	
	  List<Cliente> results = query.getResultList();
 
	  return results;
	  }

   public Cliente get(int id)throws Exception {
		
		 EntityManager em = JPAUtility.getEntityManager();
  		
		 return em.find(Cliente.class, id);
	 
    }
   
   
   public void actualizar(Cliente cliente)throws Exception {
		
		EntityManager em = JPAUtility.getEntityManager();
	    em.getTransaction().begin();
		em.merge(cliente);
	    em.getTransaction().commit();
	    em.close();
	   }
   
   public Cliente getFindXDocumento(String documento) throws Exception{
		
	  try {
		  EntityManager em = JPAUtility.getEntityManager();
			Query query = em.createNamedQuery("Cliente.findDocumento");
			query.setParameter("documento", documento);
			
			Cliente  results = (Cliente) query.getSingleResult();
		    
			return results;
	} catch (Exception e) {
	     return null;
	}
	  

	}
   
   public Cliente find(Cliente cliente) throws Exception{
		try {
			EntityManager em = JPAUtility.getEntityManager();
			Cliente results =em.find(Cliente.class,cliente.getId());
		    return results;		
		} catch (Exception e) {
			
			e.printStackTrace();
           return null;
		}
		

	}
   
   public List<Cliente> listarExtranjeros(String extranjero,Date fechaInicio, Date fechaFin, int hotel) throws Exception {
		
	 EntityManager em = JPAUtility.getEntityManager();
	Query query = em.createNamedQuery("Cliente.findExtranjeros");
	query.setParameter("extranjero", extranjero);
	query.setParameter("fechaInicio", fechaInicio);
	query.setParameter("fechaFin", fechaFin);
	query.setParameter("hotel", hotel);
	List<Cliente> results = query.getResultList();
 
	  return results;
	  }
}
