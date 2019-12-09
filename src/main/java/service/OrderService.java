package service;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import representation.BookDeleteRequest;
import representation.BookRequest;
import representation.OrderInfoRequest;
import representation.OrderRequest;

@WebService
public interface OrderService {
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/{orderID}")
	public Response get(@PathParam("orderID") String id);
	
	@PUT
	@Consumes({"application/xml" , "application/json"})
	@Produces({"application/xml" , "application/json"})
	@Path("/{orderID}/shipping")
	public Response shippingOrder(@PathParam("orderID") String id);
	
	@PUT
	@Consumes({"application/xml" , "application/json"})
	@Produces({"application/xml" , "application/json"})
	@Path("/{orderID}/cancel")
	public Response cancellingOrder(@PathParam("orderID") String id);
	
	@PUT
	@Consumes({"application/xml" , "application/json"})
	@Produces({"application/xml" , "application/json"})
	@Path("/{orderID}/delivered")
	public Response orderDelivered(@PathParam("orderID") String id);
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/{orderID}/status")
	public Response getOrderStatus(@PathParam("orderID") String id);
	
	@POST
	@Consumes({"application/xml" , "application/json"})
	@Produces({"application/xml" , "application/json"})
	public Response createNewOrder(OrderRequest request);
	
	@PUT
	@Consumes({"application/xml" , "application/json"})
	@Produces({"application/xml" , "application/json"})
	@Path("/{orderID}")
	public Response fullfillOrder(OrderInfoRequest request, @PathParam("orderID") String id);
	
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/delete")
	public Response deleteOrder(BookDeleteRequest request);
	
	@GET
	@Produces({"application/xml" , "application/json"})
	public Response getAll();
}
