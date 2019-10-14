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
import representation.BookRepresentation;
import representation.BookRequest;
import service.BookService;
import service.workflow.BookServiceActivity;

@Path("/books")
public class BookServiceImpl implements BookService {

	@Override
	public Response get(String id) {
		try {
			BookRepresentation bo = new BookServiceActivity().get(id);
			return Response.status(Response.Status.OK).entity(bo).build();
			
		} catch (NotExistException ex) {
			return Response.status(Response.Status.NOT_FOUND).build();
			
		}
	}
	@Override
	public Response all() {
		BookServiceActivity activity = new BookServiceActivity();
		try {
			return Response.status(Response.Status.OK).entity(activity.getAll()).build();
		} catch (NotExistException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
	}
	
	@Override
	public Response searchByTitle(String title) {
		try {
			return Response.status(Response.Status.OK).entity(new BookServiceActivity().getBooksByTitle(title)).build();
			
		} catch (NotExistException ex) {
			return Response.status(Response.Status.NOT_FOUND).build();
			
		}
	}
	@Override
	public Response getReviewsByBook(String id) {
		BookServiceActivity activity = new BookServiceActivity();
		try {
			return Response.status(Response.Status.OK).entity(activity.getReviewsByBookID(id)).build();
		} catch (NotExistException e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
	}
	@Override
	public Response createNewBook(BookRequest request) {
		try {
			BookRepresentation bo = new BookServiceActivity().createNewBook(request.getTitle(), request.getDescription(), request.getPrice(), request.getAuthor(), request.getQuantity(), request.getPartnerID());
			return Response.status(Response.Status.OK).entity(bo).build();
			
		} catch (NotExistException ex) {
			return Response.status(Response.Status.NOT_FOUND).build();
			
		}
	}

}
