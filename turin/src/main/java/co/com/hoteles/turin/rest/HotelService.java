package co.com.hoteles.turin.rest;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.com.hoteles.turin.entities.Reserva;
import co.com.hoteles.turin.services.ReservaService;



@Path("/hotel/reserva")
public class HotelService {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Reserva getReserva() {

		Reserva reserva = new Reserva();
		reserva.setIdreserva(33);
		reserva.setCelular("234234");

		return reserva;

	}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Reserva reserva) {

	    try {
	    	/*reserva.setCelular("121212");
	    	reserva.setIdreserva(0);
	    	reserva.setCorreo("gamero");
	    	reserva.setFechaEntrada(new Date());
	    	reserva.setFechaSalida(new Date());
	    	reserva.setNombre("ALBERTO");
	    	reserva.setNumeroAdultos(1);
	    	reserva.setNumeroHabitaciones(1);
	    	reserva.setNumeroNinos(0);*/
			ReservaService.getInstance().ingresar(reserva);
			return Response.status(201).entity("OK").build();

	    } catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();

		}
		
	}
	
}