package co.com.hoteles.turin.reportes;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import co.com.hoteles.turin.entities.Reserva;
import co.com.hoteles.turin.utilidades.ValidacionesUtil;
public class JPAUtilityRest {
 	
	
	  private EntityManager em;

	protected EntityManager getEntityManager() throws NamingException {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("HotelPU");
	    return emf.createEntityManager();
	  }
	
	
	
	public void ingresar(Reserva reserva)throws Exception {

		em = getEntityManager();
		em.getTransaction().begin();
		reserva.setActiva("S");
		em.persist(reserva);
		em.getTransaction().commit();
		em.close();
	}

	
} 