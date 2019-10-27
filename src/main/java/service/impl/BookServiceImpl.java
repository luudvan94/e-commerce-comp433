package service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

//
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.stereotype.Service;

import exception.NoContentException;
import exception.NotExistException;
import exception.UnAuthorizedException;
import representation.BookDeleteRequest;
import representation.BookRepresentation;
import representation.BookRequest;
import service.BookService;
import service.workflow.BookReviewServiceActivity;
import service.workflow.BookServiceActivity;
import service.workflow.PartnerServiceActivity;

@Path("/v1/books")
public class BookServiceImpl implements BookService {

	@Override
	public Response get(String id) {
		try {
			BookRepresentation bo = new BookServiceActivity().get(id);
			return Response.status(Response.Status.OK).entity(bo).build();
			
		} catch (NotExistException ex) {
			return Response.status(Response.Status.BAD_REQUEST).build();
			
		}
	}

	
	@Override
	public Response searchByTitle(String title) {
		try {
			return Response.status(Response.Status.OK).entity(new BookServiceActivity().getBooksByTitle(title)).build();
			
		} catch (NotExistException ex) {
			return Response.status(Response.Status.BAD_REQUEST).build();
			
		}
	}

	@Override
	public Response createNewBook(BookRequest request) {
		try {
			BookRepresentation bo = new BookServiceActivity().createNewBook(request);
			return Response.status(Response.Status.OK).entity(bo).build();
			
		} catch (NotExistException ex) {
			return Response.status(Response.Status.BAD_REQUEST).build();
			
		}
	}
	
	@Override
	public Response deleteBook(BookDeleteRequest request) {
		try {
			new BookServiceActivity().deleteBook(request.getPartnerID(), request.getBookID());
			return Response.status(Response.Status.OK).build();
			
		} catch(UnAuthorizedException ex) {
			return Response.status(Response.Status.CONFLICT).entity(ex.getMessage()).build(); 
		} catch (NotExistException ex) {
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
		}
	}
	
	@Override
	public Response getAll() {
		try {
			return Response.status(Response.Status.OK).entity(new BookServiceActivity().getAll()).build();
		} catch (NotExistException e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}


	@Override
	public Response reviewsByBookID(String id) {
		try {
			return Response.status(Response.Status.OK).entity(new BookReviewServiceActivity().getReviewsByBookID(id)).build();
		} catch (NotExistException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

}
