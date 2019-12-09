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

import representation.CustomerInfoRequest;
import representation.PartnerInfoRequest;
import representation.PartnerRequest;

@WebService
public interface PartnerService {
	
	@POST
	@Consumes({"application/xml" , "application/json"})
	@Produces({"application/xml" , "application/json"})
	public Response createPartner(PartnerRequest  request);
	
	
	@POST
	@Consumes({"application/xml" , "application/json"})
	@Produces({"application/xml" , "application/json"})
	@Path("/{partnerID}")
	public Response createPartnerInfo(PartnerInfoRequest  request);
	
	@POST
	@Consumes({"application/xml" , "application/json"})
	@Produces({"application/xml" , "application/json"})
	@Path("/login")
	public Response login(PartnerRequest request);
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/{partnerID}")
	public Response getPartnerInfo(@PathParam("partnerID") String id);
	
	@PUT
	@Consumes({"application/xml" , "application/json"})
	@Produces({"application/xml" , "application/json"})
	@Path("/{partnerID}")
	public Response updatePartnerInfo(PartnerInfoRequest request);
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/{partnerID}/books")
	public Response booksByPartnerID(@PathParam("partnerID") String id);
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/{partnerID}/orders")
	public Response ordersByPartnerInfo(@PathParam("partnerID") String id);
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/{partnerID}/orders/status/pending")
	public Response newOrder(@PathParam("partnerID") String id);
	
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/{partnerID}")
	public Response deleteCustomer(@PathParam("partnerID") String id);
}

