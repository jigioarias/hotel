package co.com.hoteles.turin.services;

import javax.persistence.EntityManager;

import co.com.hoteles.turin.entities.Reserva;
import co.com.hoteles.turin.reportes.JPAUtility;
import co.com.hoteles.turin.utilidades.ValidacionesUtil;

public class ReservaService {
	
	private static ReservaService reservaService;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private ReservaService() {
       
    }

    public static ReservaService getInstance() {
        if (reservaService == null){
        	reservaService = new ReservaService();
        }
       
        
        return reservaService;
    }
    
	public void ingresar(Reserva reserva)throws Exception {
		
		int retorno = 0;
	 
	    if (reserva.getCorreo() == null && !ValidacionesUtil.getInstance().validarCorreo(reserva.getCorreo())) {
	    	
	    	throw new Exception ("El correo es no es valido");
	    	
	    }
	    
		EntityManager em = JPAUtility.getEntityManager();
		
	    em.getTransaction().begin();
		em.persist(reserva);
	    em.getTransaction().commit();
	     JPAUtility.close();
	   }

}
