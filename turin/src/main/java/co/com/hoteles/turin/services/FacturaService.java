package co.com.hoteles.turin.services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.hoteles.turin.entities.Factura;
import co.com.hoteles.turin.entities.Habitacion;
import co.com.hoteles.turin.entities.Parametro;
import co.com.hoteles.turin.reportes.JPAUtility;

public class FacturaService {
	
	private static FacturaService facturaService;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private FacturaService() {
       
    }

    public static FacturaService getInstance() {
        if (facturaService == null){
        	facturaService = new FacturaService();
        }
       
        
        return facturaService;
    }
    
	public void ingresar(Factura Factura)throws Exception {
		
		Parametro parametro=ParametroService.getInstance().findXNombre("resolucion");
		int consecutivo = Integer.parseInt(parametro.getValor())+1;
		Factura.setNumero(consecutivo);
		EntityManager em = JPAUtility.getEntityManager();
		Factura FacturaConsultado = getFindXDocumento(Factura.getNumero());
	    
		em.getTransaction().begin();
	    if(FacturaConsultado==null) {
			em.persist(Factura);
	    	
	    }else {
	    	em.merge(Factura);
	    }
	    em.getTransaction().commit();
	    em.close();
	   }
	
	
   public List<Factura> listar()throws Exception {
		
	 EntityManager em = JPAUtility.getEntityManager();
	Query query = em.createNamedQuery("Factura.findAll");
	
	  List<Factura> results = query.getResultList();
 
	  return results;
	  }

   public Factura get(int id)throws Exception {
		
		 EntityManager em = JPAUtility.getEntityManager();
  		
		 return em.find(Factura.class, id);
	 
    }
   
   
   public void actualizar(Factura Factura)throws Exception {
		
		EntityManager em = JPAUtility.getEntityManager();
	    em.getTransaction().begin();
		em.merge(Factura);
	    em.getTransaction().commit();
	    em.close();
	   }
   
   public Factura getFindXDocumento(int i) throws Exception{
		
	  try {
		  EntityManager em = JPAUtility.getEntityManager();
			Query query = em.createNamedQuery("Factura.findNumero");
			query.setParameter("numero", i);
			
			Factura  results = (Factura) query.getSingleResult();
		    
			return results;
	} catch (Exception e) {
	     return null;
	}
	  

	}
   
   public Factura find(Factura Factura) throws Exception{
		try {
			EntityManager em = JPAUtility.getEntityManager();
			Factura results =em.find(Factura.class,Factura.getNumero());
		    return results;		
		} catch (Exception e) {
			
			e.printStackTrace();
           return null;
		}
		

	}
   
  
}
