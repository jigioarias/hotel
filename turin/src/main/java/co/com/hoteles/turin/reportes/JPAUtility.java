package co.com.hoteles.turin.reportes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class JPAUtility {
 	private static final EntityManagerFactory emFactory;
	static {
		   emFactory = Persistence.createEntityManagerFactory("HotelPU");
	}
	public static EntityManager getEntityManager(){
	
	//	if (emFactory.isOpen()) {
			return emFactory.createEntityManager();

		//}
	//	return  Persistence.createEntityManagerFactory("HotelPU").createEntityManager();
	}
	
	
	public static void close(){
		//emFactory.close();
	}
} 