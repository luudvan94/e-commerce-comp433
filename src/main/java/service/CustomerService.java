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
import javax.ws.rs.core.Response;

import representation.CustomerInfoRequest;
import representation.CustomerRequest;

@WebService
public interface CustomerService {
	
	@POST
	@Consumes({"application/xml" , "application/json"})
	@Produces({"application/xml" , "application/json"})
	public Response createCustomer(CustomerRequest  request);
	
	
	@POST
	@Consumes({"application/xml" , "application/json"})
	@Produces({"application/xml" , "application/json"})
	@Path("/{customerID}")
	public Response createCustomerInfo(CustomerInfoRequest  request);
	
	@POST
	@Consumes({"application/xml" , "application/json"})
	@Produces({"application/xml" , "application/json"})
	@Path("/login")
	public Response login(CustomerRequest request);
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/{customerID}")
	public Response getCustomerInfo(@PathParam("customerID") String id);
	
	@PUT
	@Consumes({"application/xml" , "application/json"})
	@Produces({"application/xml" , "application/json"})
	@Path("/{customerID}")
	public Response updateCustomerInfo(CustomerInfoRequest request);
	
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/{customerID}")
	public Response deleteCustomer(@PathParam("customerID") String id);
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/{customerID}/reviews")
	public Response reviewsByCustomerID(@PathParam("customerID") String id);
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/{customerID}/orders")
	public Response ordersByCustomer(@PathParam("customerID") String id);
	
}
