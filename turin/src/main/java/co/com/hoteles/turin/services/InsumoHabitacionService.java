package co.com.hoteles.turin.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.hoteles.turin.entities.Cliente;
import co.com.hoteles.turin.entities.Insumo;
import co.com.hoteles.turin.entities.Insumoshabitacion;
import co.com.hoteles.turin.entities.InsumoshabitacionPK;
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


	public void ingresar(Insumoshabitacion insumoshabitacion)throws Exception {


		EntityManager em = JPAUtility.getEntityManager();
		Insumoshabitacion insumoshabitacion2=find(insumoshabitacion.getId());
		System.out.println("insumohh2::"+insumoshabitacion2);
		if(insumoshabitacion2== null || insumoshabitacion2.getId()==null) {
			em.getTransaction().begin();
			em.persist(insumoshabitacion);
			em.getTransaction().commit();
			em.close();    	
		}

	}


	public Insumoshabitacion find(InsumoshabitacionPK id) throws Exception{

		
		try {
			
		
		EntityManager em = JPAUtility.getEntityManager();
		Query query = em.createNamedQuery("Insumoshabitacion.findId");
	    
		query.setParameter("habitacion",id.getHabitacion());
		
		query.setParameter("insumo",id.getInsumo());
		Insumoshabitacion results = (Insumoshabitacion) query.getSingleResult();
		return results;
		} catch (Exception e) {
			return null;
		}
	}   
}