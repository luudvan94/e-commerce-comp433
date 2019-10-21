package service;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import representation.BookReviewDeleteRequest;
import representation.BookReviewRequest;
import representation.BookReviewUpdateRequest;


@WebService
public interface BookReviewService {

	@POST
	@Produces({"application/xml" , "application/json"})
	public Response createNewBookReview(BookReviewRequest request);
	
	@PUT
	@Produces({"application/xml" , "application/json"})
	public Response updateBookReview(BookReviewUpdateRequest request);
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/customer/{id}")
	public Response reviewsByCustomerID(@PathParam("id") String id);
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/book/{id}")
	public Response reviewsByBookID(@PathParam("id") String id);
	
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/delete")
	public Response deleteReview(BookReviewDeleteRequest request);
	
}
