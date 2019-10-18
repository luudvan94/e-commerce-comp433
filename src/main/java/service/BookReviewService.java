package service;

import javax.jws.WebService;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

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
	
}
