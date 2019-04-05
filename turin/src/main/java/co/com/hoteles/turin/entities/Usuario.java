package co.com.hoteles.turin.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Codigo Realizado por Natali Monsalve Ramirez 
   Derechos reservados de Natali Monsalve Ramirez 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String login;
	private String password;
	private String rol;

	public Usuario() {
	}
	//* Se definen los getters y setters para los objetos de la clase
	
	public String getLogin() {
		return this.login;
	}

	public Usuario(String login) {
		super();
		this.login = login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

}