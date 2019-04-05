package co.com.hoteles.turin;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import co.com.hoteles.turin.entities.Employee;
import co.com.hoteles.turin.entities.Reserva;
 
public class JpaTest {
    private static EntityManager em;
 
    public static void main(String[] args) {
 
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("HotelPU");
        em = emf.createEntityManager();
 
      /*(1, "Ravi");
        createEmployee(2, "Amit");
        createEmployee(3, "Nitish");
        */
        createReserva("Jinni",
			     "3233",
				"jigio",
				2,
				4,
				new Date(),
				new Date(),
				2);
 
    }
 
    private static void createEmployee(int id, String firstName) {
        em.getTransaction().begin();
        Employee emp = new Employee(id, firstName);
        em.persist(emp);
        em.getTransaction().commit();
    }
    
    private static void createReserva( String nombre,
    									String celular,
    									String correo,
    									int numeroNinos,
    									int numeroAdultos,
    									Date fechaEntrada,
    									Date fechaSalida,
    									int numeroHabitaciones) {
        em.getTransaction().begin();
        Reserva reserva = new Reserva();
        reserva.setCelular(celular);
        reserva.setCorreo(correo);
        reserva.setFechaEntrada(fechaEntrada);
        reserva.setFechaSalida(fechaSalida);
        reserva.setNombre(nombre);
        reserva.setNumeroAdultos(numeroAdultos);
        reserva.setNumeroHabitaciones(numeroHabitaciones);
        reserva.setNumeroNinos(numeroNinos);
        em.persist(reserva);
        em.getTransaction().commit();
    }
}