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
import representation.PartnerInfoRequest;
import representation.PartnerRequest;

@WebService
public interface PartnerService {
	
	@POST
	@Produces({"application/xml" , "application/json"})
	public Response createPartner(PartnerRequest  request);
	
	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/partnerInfo")
	public Response createPartnerInfo(PartnerInfoRequest  request);
	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/login")
	public Response login(PartnerRequest request);
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/{partnerID}")
	public Response getPartnerInfo(@PathParam("partnerID") String id);
	
	@PUT
	@Produces({"application/xml" , "application/json"})
	@Path("/partnerInfo")
	public Response updatePartnerInfo(PartnerInfoRequest request);
}
