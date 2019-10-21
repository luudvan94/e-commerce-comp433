package service;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import representation.PaymentRequest;


@WebService
public interface PaymentService {
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/{paymentID}")
	public Response get(@PathParam("paymentID") String id);
	
	@POST
	@Produces({"application/xml" , "application/json"})
	public Response createNewBook(PaymentRequest request);

}
