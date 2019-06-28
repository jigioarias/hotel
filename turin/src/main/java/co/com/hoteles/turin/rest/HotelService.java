package co.com.hoteles.turin.rest;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.com.hoteles.turin.entities.Reserva;
import co.com.hoteles.turin.reportes.JPAUtilityRest;



@Path("/hotel/reserva")
public class HotelService {

	

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Reserva reserva) {

	    try {
	    	
	    	JPAUtilityRest jpaRest = new JPAUtilityRest();
	    	jpaRest.ingresar(reserva);
			return Response.status(201).entity("OK").build();

	    } catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();

		}
		
	}
	
}