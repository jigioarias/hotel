package co.com.hoteles.turin.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.hql.spi.id.inline.IdsClauseBuilder;

import co.com.hoteles.turin.entities.Ckecking;
import co.com.hoteles.turin.entities.Cliente;
import co.com.hoteles.turin.entities.Habitacion;
import co.com.hoteles.turin.entities.Servicio;
import co.com.hoteles.turin.entities.ServiciosCkeking;
import co.com.hoteles.turin.reportes.JPAUtility;

public class ServiciosCkeckingService {
	
	private static ServiciosCkeckingService serviciosCheckingService;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private ServiciosCkeckingService() {
       
    }

    public static ServiciosCkeckingService getInstance() {
        if (serviciosCheckingService == null){
        	serviciosCheckingService = new ServiciosCkeckingService();
        }
       
        
        return serviciosCheckingService;
    }
    
	public void ingresar(ServiciosCkeking serviciosCkecking)throws Exception {
		
		EntityManager em = JPAUtility.getEntityManager();
		List<ServiciosCkeking> lista =getFindXCheckingxServicio(serviciosCkecking.getIdChecking(),serviciosCkecking.getIdServicio());
		if(lista== null || lista.size()==0) {
	     em.getTransaction().begin();
	     em.persist(serviciosCkecking);
	     em.getTransaction().commit();
	     em.close();
	     }
	   }
	
	
   
   
   
   public void actualizar(Ckecking Ckecking)throws Exception {
		
		EntityManager em = JPAUtility.getEntityManager();
	    em.getTransaction().begin();
		em.merge(Ckecking);
	    em.getTransaction().commit();
	    em.close();
	   }
   
   
   
   public List<Servicio> getFindXChecking(int idCkecking) throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
		Query query = em.createNamedQuery("ServiciosChecking.findxCkecking");
		query.setParameter("id", idCkecking);
		List<ServiciosCkeking>  results =  query.getResultList();
		List<Servicio> servicios = new ArrayList<Servicio>();
	    for (ServiciosCkeking serviciosChecking : results) {
	     
	      Servicio servicio = 	ServicioService.getInstance().find(serviciosChecking.getIdServicio());
	      servicio.setCantidad(serviciosChecking.getCantidad());
		  servicios.add(servicio);
		}
	    
	     return servicios;

	}
   

   public List<ServiciosCkeking> getFindXCheckingxServicio(int idCkecking,int idServicio) throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
		Query query = em.createNamedQuery("ServiciosChecking.findxCkeckingxServicio");
		query.setParameter("idChecking", idCkecking);
		query.setParameter("idServicio", idServicio);

		List<ServiciosCkeking>  results =  query.getResultList();
		
	    
	     return results;

	}
   
   
}
