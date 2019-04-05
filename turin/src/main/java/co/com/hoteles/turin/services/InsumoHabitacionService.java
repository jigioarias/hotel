package co.com.hoteles.turin.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.hoteles.turin.entities.Cliente;
import co.com.hoteles.turin.entities.Insumo;
import co.com.hoteles.turin.entities.Insumoshabitacion;
import co.com.hoteles.turin.reportes.JPAUtility;

public class InsumoHabitacionService {
	

	private static InsumoHabitacionService insumoService;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private InsumoHabitacionService() {
       
    }

    public static InsumoHabitacionService getInstance() {
        if (insumoService == null){
        	insumoService = new InsumoHabitacionService();
        }
       
        
        return insumoService;
    }
	
	public List<Insumo> listar(int habitacion) throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
		Query query = em.createNamedQuery("Insumoshabitacion.findHabitacion");
		query.setParameter("habitacion",habitacion);
		List<Insumoshabitacion> results = query.getResultList();
		List<Insumo> insumos = new ArrayList<Insumo>();
		
		for (Insumoshabitacion insumoshabitacion : results) {
			Insumo  insumo= InsumoService.getInstance().find(insumoshabitacion.getId().getInsumo());
			insumo.setCantidad(insumoshabitacion.getCantidad());
			insumos.add(insumo);
		}
		
	    return insumos;

	}
	

	
	   public void actualizar(Insumoshabitacion Insumo)throws Exception {
			
			EntityManager em = JPAUtility.getEntityManager();
		    em.getTransaction().begin();
			em.merge(Insumo);
		    em.getTransaction().commit();
		    em.close();
		   }
	   
	
	   public void ingresar(Insumoshabitacion Insumo)throws Exception {
			
				EntityManager em = JPAUtility.getEntityManager();
			    em.getTransaction().begin();
				em.persist(Insumo);
			    em.getTransaction().commit();
			    em.close();
			   }
		   
}