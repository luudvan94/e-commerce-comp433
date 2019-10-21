package service;

import javax.jws.WebService;
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
	@Produces({"application/xml" , "application/json"})
	public Response createCustomer(CustomerRequest  request);
	
	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/customerInfo")
	public Response createCustomerInfo(CustomerInfoRequest  request);
	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/login")
	public Response login(CustomerRequest request);
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/{customerID}")
	public Response getCustomerInfo(@PathParam("customerID") String id);
	
	@PUT
	@Produces({"application/xml" , "application/json"})
	@Path("/customerInfo")
	public Response updateCustomerInfo(CustomerInfoRequest request);
	
	
}
