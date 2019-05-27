package co.com.hoteles.turin.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.hoteles.turin.entities.Ckecking;
import co.com.hoteles.turin.entities.Cliente;
import co.com.hoteles.turin.entities.Habitacion;
import co.com.hoteles.turin.entities.AcompanantesChecking;
import co.com.hoteles.turin.reportes.JPAUtility;

public class AcompanantesCkeckingService {
	
	private static AcompanantesCkeckingService acompanantesCkeckingService;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private AcompanantesCkeckingService() {
       
    }

    public static AcompanantesCkeckingService getInstance() {
        if (acompanantesCkeckingService == null){
        	acompanantesCkeckingService = new AcompanantesCkeckingService();
        }
       
        
        return acompanantesCkeckingService;
    }
    
	public void ingresar(AcompanantesChecking acompanantesCkecking)throws Exception {
		
		EntityManager em = JPAUtility.getEntityManager();
		List<Cliente> clientes=getFindXCheckingxACompanante(acompanantesCkecking.getIdChecking(),
				acompanantesCkecking.getIdCliente());
	    if(clientes == null || clientes.size()== 0) {
		   em.getTransaction().begin();
	       em.persist(acompanantesCkecking);
	       em.getTransaction().commit();
	       em.close();
	     }
	   }
	
	
   
   
   
  
   
   
   public List<Cliente> getFindXChecking(int idCkecking) throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
		Query query = em.createNamedQuery("AcompanantesChecking.findxCkecking");
		query.setParameter("id", idCkecking);
		List<AcompanantesChecking>  results =  query.getResultList();
		List<Cliente> clientes = new ArrayList<Cliente>();
	    for (AcompanantesChecking AcompanantesChecking : results) {
		 clientes.add(ClienteService.getInstance().get(AcompanantesChecking.getIdCliente()));
		}
	    
	     return clientes;

	}
   
   public List<Cliente> getFindXCheckingxACompanante(int idCkecking,int idAcompanante) throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
		Query query = em.createNamedQuery("AcompanantesChecking.findxCkeckingxCliente");
		query.setParameter("idChecking", idCkecking);
		query.setParameter("idCliente", idAcompanante);
		List<AcompanantesChecking>  results =  query.getResultList();
		List<Cliente> clientes = new ArrayList<Cliente>();
	    for (AcompanantesChecking AcompanantesChecking : results) {
		 clientes.add(ClienteService.getInstance().get(AcompanantesChecking.getIdCliente()));
		}
	    
	     return clientes;

	}
   
}
