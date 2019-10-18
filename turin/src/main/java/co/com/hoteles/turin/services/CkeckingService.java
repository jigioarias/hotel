package co.com.hoteles.turin.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.hoteles.turin.dtos.CheckinDTO;
import co.com.hoteles.turin.dtos.CheckingDTO;
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

		Ckecking ckeckingConsultado = getFindXCliente(ckecking.getIdCliente(),"A", ckecking.getHotel());
	    em.getTransaction().begin();
	    
		if (ckeckingConsultado==null) {

	    em.persist(ckecking);
	    }else {
		   ckeckingConsultado.setFechaEntrada(ckecking.getFechaEntrada());
		   ckeckingConsultado.setFechaSalida(ckecking.getFechaSalida());
		   ckeckingConsultado.setUsuario(ckecking.getUsuario());
		   ckeckingConsultado.setDescuento(ckecking.getDescuento());
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
   
   
   
  
   
   public Ckecking getFindXCliente(int idCliente,String estado,int hotel) throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
		try {
			Query query = em.createNamedQuery("Ckecking.findIdCliente");
			query.setParameter("id", idCliente);
			query.setParameter("estado", estado);
			query.setParameter("hotel",hotel);
			Ckecking  results = (Ckecking) query.getSingleResult();
		    return results;	
		} catch (Exception e) {
				return null;
		}
		

	}
   
   
   

	public List<CheckinDTO> listarVentas(Date fechaInicio, Date fechaFin, int hotel) throws Exception{
		try {
			List<CheckinDTO> lista = new ArrayList<CheckinDTO>();
			EntityManager em = JPAUtility.getEntityManager();
				Query query = em.createNativeQuery(" select " + 
						"                         ck.id,     " + 
						"						 ck.fecha_registro,     " + 
						"						 ck.habitacion,     " + 
						"						 (select tipoDocumento from clientes c where c.id=ck.id_cliente )tipo_documento,     " + 
						"						 (select documento from clientes c where c.id=ck.id_cliente )documento,     " + 
						"						 (select nombre from clientes c where c.id=ck.id_cliente )nombre,     " + 
						"						 (select fechaNacimiento from clientes c where c.id=ck.id_cliente )fechaNacimiento,     " + 
						"						 (select celular from clientes c where c.id=ck.id_cliente )celular,     " + 
						"						 ck.numero_personas acompanantes,     " + 
						"						 IF(DATEDIFF(ck.fecha_salida, ck.fecha_entrada)=0,1,DATEDIFF(ck.fecha_salida, ck.fecha_entrada))noches,     " + 
						"						 ck.estado,     " + 
						"						 (select fecha from facturas f where f.checking= ck.id)fecha_salida,     " + 
						"						ck.descuento,     " + 
						"						 (select sum(h.precio)      " + 
						"						 from habitaciones_checking hc,      " + 
						"						 habitacion h, ckecking c     " + 
						"						 where  c.id = hc.id_ckecking and h.id = hc.id_habitacion     " + 
						"						 and hc.hotel = c.hotel     " + 
						"						 and c.hotel = h.hotel     " + 
						"						 and c.hotel = ck.hotel     " + 
						"						 and c.id =ck.id)* IF(DATEDIFF(ck.fecha_salida, ck.fecha_entrada)=0,1,DATEDIFF(ck.fecha_salida, ck.fecha_entrada)) -ck.descuento total, ck.usuario     " + 
						"						 from ckecking ck     " + 
						"						 where ck.fecha_entrada >= ?     " + 
						"						 and ck.fecha_entrada <= ?     " + 
						"						 and ck.hotel = ?    " + 
						"						 order by ck.id   " + 
						"                         ");
			query.setParameter(1, fechaInicio);
			query.setParameter(2, fechaFin);
			query.setParameter(3, hotel);

			List<Object[]> ventas =  query.getResultList();
			if (ventas==null || ventas.isEmpty()) {
			    return null; // handle no-results case
			} else {
					String habitaciones="";
				for (Object[] r : ventas) {
					CheckinDTO d = new CheckinDTO();
					d.setCheckin(Integer.parseInt(r[0]+""));
					habitaciones = HabitacionService.getInstance().listarHabitacionesXCheckin(Integer.parseInt(r[0]+""), hotel);
					d.setFecha(r[1].toString());
					d.setHabitacion(habitaciones);
					d.setTipoDocumento(r[3].toString());
					d.setDocumento(r[4].toString());
					d.setNombre(r[5].toString());
					d.setFechaNacimiento((r[6]==null)?"": r[6].toString());
                    d.setCelular(r[7].toString());
					d.setAcompanantes(Integer.parseInt((r[8]==null)?"0":r[8].toString()));
					d.setNoches(Integer.parseInt((r[9]==null)?"0":r[9].toString()));
                    d.setEstado(r[10].toString());
					d.setFechaFin(((r[11]==null)?"": r[11].toString()));

                    d.setDescuento(Integer.parseInt((r[12]==null)?"0":r[12].toString()));
					d.setValor(Integer.parseInt((r[13]==null)?"0":r[13].toString()));
					d.setUsuario(r[14].toString());
					
					lista.add(d);


				}
					
					
					
			}
			
			return lista;
			 
	
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}


	}

  public static void main(String[] args) {
	
}
}
