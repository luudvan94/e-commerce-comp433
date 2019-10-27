package service.impl;

import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import exception.NotExistException;
import exception.UnAuthorizedException;
import representation.BookRepresentation;
import representation.BookReviewDeleteRequest;
import representation.BookReviewRepresentation;
import representation.BookReviewRequest;
import representation.BookReviewUpdateRequest;
import service.BookReviewService;
import service.workflow.BookReviewServiceActivity;
import service.workflow.BookServiceActivity;
import service.workflow.CustomerServiceActivity;

@Path("/v1/reviews")
public class BookReviewServiceImpl implements BookReviewService {

	@Override
	public Response createNewBookReview(BookReviewRequest request) {
		try {
			return Response.status(Response.Status.OK).entity(new BookReviewServiceActivity().createNewBookReview(request)).build();
			
		} catch (NotExistException ex) {
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
			
		}
	}

	@Override
	public Response updateBookReview(BookReviewUpdateRequest request) {
		try {
			return Response.status(Response.Status.OK).entity(new BookReviewServiceActivity().updateReview(request.getCustomerID(), request.getBookReviewID(), request.getContent())).build();
			
		} catch(UnAuthorizedException ex) {
			return Response.status(Response.Status.CONFLICT).entity(ex.getMessage()).build(); 
		} catch (NotExistException ex) {
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
		}
	}

	
	@Override
	public Response deleteReview(BookReviewDeleteRequest request) {
		try {
			new BookReviewServiceActivity().deleteReview(request.getCustomerID(), request.getBookReviewID());
			return Response.status(Response.Status.OK).build();
			
		} catch(UnAuthorizedException ex) {
			return Response.status(Response.Status.CONFLICT).entity(ex.getMessage()).build(); 
		} catch (NotExistException ex) {
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
		}
	}

}
