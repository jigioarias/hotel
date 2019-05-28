package co.com.hoteles.turin.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.hoteles.turin.entities.Habitacion;
import co.com.hoteles.turin.entities.Usuario;
import co.com.hoteles.turin.reportes.JPAUtility;

public class UsuarioService {
	

	private static UsuarioService usuarioService;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private UsuarioService() {
       
    }

    public static UsuarioService getInstance() {
        if (usuarioService == null){
        	usuarioService = new UsuarioService();
        }
       
        
        return usuarioService;
    }
	
	
	
	
public List<Usuario> listar() throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
		Query query = em.createNamedQuery("Usuario.findAll");
		List<Usuario> results = query.getResultList();
	    return results;

	}

	
	   public void actualizar(Usuario usuario)throws Exception {
			
		   List<Usuario> usuarios =findXLogin(usuario.getId());
		    EntityManager em = JPAUtility.getEntityManager();
		    em.getTransaction().begin();
		    
		    if(!usuarios.isEmpty()) {
		     Usuario usuarioConsultada=  usuarios.get(0);
		     usuarioConsultada.setCorreo(usuario.getCorreo());
		     usuarioConsultada.setClave(usuario.getClave());
		   
		     em.merge(usuarioConsultada);
		    
		   }else {

			   em.persist(usuario);
		   }
	       em.getTransaction().commit();
		    em.close();
	   
	   }
	
	
public Usuario find(String id) throws Exception{
		
		EntityManager em = JPAUtility.getEntityManager();
	    return em.find(Usuario.class, id);

	}

public List<Usuario> findXLogin(String login) throws Exception{
	
	EntityManager em = JPAUtility.getEntityManager();
	Query query = em.createNamedQuery("Usuario.findLogin");
	query.setParameter("login", login);
	List<Usuario> results = query.getResultList();
    return results;
    
}


public Usuario findXClave(Usuario usuario) throws Exception{
	
	EntityManager em = JPAUtility.getEntityManager();
	Query query = em.createNamedQuery("Usuario.findClave");
	query.setParameter("login", usuario.getId());
	query.setParameter("clave",usuario.getClave());

	Usuario results = (Usuario) query.getSingleResult();
    return results;
    
}
}