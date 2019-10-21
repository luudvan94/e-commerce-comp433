package service;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import representation.BookDeleteRequest;
import representation.BookRequest;
import representation.OrderRequest;

@WebService
public interface OrderService {
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/{orderID}")
	public Response get(@PathParam("orderID") String id);
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/customer/{id}")
	public Response ordersByCustomer(@PathParam("id") String id);
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/partner/{id}")
	public Response ordersByPartner(@PathParam("id") String title);
	
	
	@POST
	@Produces({"application/xml" , "application/json"})
	public Response createNewOrder(OrderRequest request);
	
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/delete")
	public Response deleteOrder(BookDeleteRequest request);
	
	@GET
	@Produces({"application/xml" , "application/json"})
	public Response getAll();
}
