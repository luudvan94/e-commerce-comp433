package service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import representation.BookRepresentation;

@Path("/bookservice")
public interface BookService {

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("book/{bookId}")
	public BookRepresentation get(@PathParam("bookId") String id);
	
//	List<BookRepresentation> booksByTitle(String title);
}
