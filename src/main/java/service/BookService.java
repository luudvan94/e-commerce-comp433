package service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

import representation.BookDeleteRequest;
import representation.BookRepresentation;
import representation.BookRequest;

@WebService
public interface BookService {

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/{bookId}")
	public Response get(@PathParam("bookId") String id);
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/title/{title}")
	public Response searchByTitle(@PathParam("title") String title);
	
	
	@POST
	@Produces({"application/xml" , "application/json"})
	public Response createNewBook(BookRequest request);
	
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/delete")
	public Response deleteBook(BookDeleteRequest request);
	
	@GET
	@Produces({"application/xml" , "application/json"})
	public Response getAll();
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/{bookID}/reviews")
	public Response reviewsByBookID(@PathParam("bookID") String id);
}
