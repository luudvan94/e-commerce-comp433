package service.impl;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import representation.BookRepresentation;
import service.BookService;
import service.workflow.BookServiceActivity;

@Service("bookService")
public class BookServiceImpl implements BookService {

	@Override
	public BookRepresentation get(String id) {
//		BookServiceActivity activity = new BookServiceActivity();
//		return activity.get(id);
		
		BookRepresentation book = new BookRepresentation();
		book.setBookId("B123");
		book.setAuthor("Luu Dinh Van");
		book.setDescription("This is desciption");
		book.setPrice(20.5);
		book.setTitle("title");
		
		return book;
		
	}
	

//	@GET
//	@Produces({"application/xml" , "application/json"})
//	@Override
//	public List<BookRepresentation> booksByTitle(String title) {
//		BookServiceActivity activity = new BookServiceActivity();
//		return activity.getAll();
//	}
	
}
