package co.com.hoteles.turin.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.hoteles.turin.entities.Ckecking;
import co.com.hoteles.turin.entities.Cliente;
import co.com.hoteles.turin.entities.Habitacion;
import co.com.hoteles.turin.reportes.JPAUtility;

public class CkeckingService {
	
	private static CkeckingService checkingService;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private CkeckingService() {
       
    }

    public static CkeckingService getInstance() {
        if (checkingService == null){
        	checkingService = new CkeckingService();
        }
       
        
        return checkingService;
    }
    
	public void ingresar(Ckecking ckecking)throws Exception {
		
		EntityManager em = JPAUtility.getEntityManager();
	    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>:::ID cliente:::::"+ckecking.getIdCliente()); 

		Ckecking ckeckingConsultado = getFindXCliente(ckecking.getIdCliente());
	    em.getTransaction().begin();
	    
		if (ckeckingConsultado==null) {
			   System.out.println(">>>>>>>>>>>>>GUARDANDO>>>>>>>>>>>>>>::::::::"); 

	    em.persist(ckecking);
	    }else {
		   System.out.println(">>>>>>>>>>>>>ACTUALIZANDO>>>>>>>>>>>>>>::::::::"+ckeckingConsultado); 
		   ckeckingConsultado.setFechaEntrada(ckecking.getFechaEntrada());
		   ckeckingConsultado.setFechaSalida(ckecking.getFechaSalida());
		   ckeckingConsultado.setUsuario(ckecking.getUsuario());
	       em.merge(ckeckingConsultado);
	    }
	    em.getTransaction().commit();

		em.close();
	   }
	
	
   public List<Ckecking> listar()throws Exception {
		
	 EntityManager em = JPAUtility.getEntityManager();
	 Query query = em.createNamedQuery("Ckecking.findAll");
	 List<Ckecking> results = query.getResultList();
 	 return results;
	}

   public Ckecking get(int id)throws Exception {
		
		 EntityManager em = JPAUtility.getEntityManager();
  		
		 return em.find(Ckecking.class, id);
	 
    }
   
   
   public void actualizar(Ckecking Ckecking)throws Exception {
		
		EntityManager em = JPAUtility.getEntityManager();
	    em.getTransaction().begin();
		em.merge(Ckecking);
	    em.getTransaction().commit();
	    em.close();
	   }
   
   
   
   public Ckecking getFindXCliente(int idCliente) throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
		try {
			Query query = em.createNamedQuery("Ckecking.findIdCliente");
			query.setParameter("id", idCliente);
			query.setParameter("estado", "A");
			System.out.println("clienteeeeee:>>>>>"+idCliente);
			Ckecking  results = (Ckecking) query.getSingleResult();
		    return results;	
		} catch (Exception e) {
				return null;
		}
		

	}
   
  
}
