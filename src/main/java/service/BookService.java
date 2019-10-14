package service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

import representation.BookRepresentation;
import representation.BookRequest;

//@Path("/books")
@WebService
public interface BookService {

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/{bookId}")
	public Response get(@PathParam("bookId") String id);
	
	
	@GET
	@Produces({"application/xml" , "application/json"})
	Response all();
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/query")
	public Response searchByTitle(@QueryParam("title") String title);
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/{bookId}/reviews")
	public Response getReviewsByBook(@PathParam("bookId") String id);
	
	@POST
	@Produces({"application/xml" , "application/json"})
	public Response createNewBook(BookRequest request);
}
