package service.workflow;

import repository.BookRepository;
import repository.impl.BookRepositoryImpl;
import representation.BookRepresentation;
import representation.ObjectFactory;
import service.exception.NotExistException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import entity.Book;

public class BookServiceActivity {
	
	private static BookRepository bo = new BookRepositoryImpl();
	private static ObjectFactory factory = new ObjectFactory();
	
	private BookRepresentation toRepresentation(Book book) {
		BookRepresentation bRepresent = factory.createBookRepresentation();
		bRepresent.setBookId(book.getId());
		bRepresent.setAuthor(book.getAuthor());
		bRepresent.setTitle(book.getTitle());
		bRepresent.setDescription(book.getDescription());
		bRepresent.setPrice(book.getPrice());
		
		return bRepresent;
	}
	
	public BookRepresentation get(String id) throws NotExistException {
		Book book = bo.get(id);
		
		if (book == null) {
			throw new NotExistException("Book is not existed with provided ID");
		}
		
		BookRepresentation bRepresent = factory.createBookRepresentation();
		bRepresent.setBookId(book.getId());
		bRepresent.setAuthor(book.getAuthor());
		bRepresent.setTitle(book.getTitle());
		bRepresent.setDescription(book.getDescription());
		bRepresent.setPrice(book.getPrice());
		
		return bRepresent;
	}
	
	public List<BookRepresentation> getBooksByTitle(String title) throws NotExistException {
		List<Book> books = bo.booksByTitle(title);
		
		if (books.size() == 0) {
			throw new NotExistException("Can't find any book with provided title");
		}
		
		return books.stream().map(book -> this.toRepresentation(book)).collect(Collectors.toList());
	}
	
	public List<BookRepresentation> getAll() {
		List<Book> books = bo.getAll();
		
		List<BookRepresentation> bRepresenList = new ArrayList();
		
		Iterator<Book> it = books.iterator();
		while(it.hasNext()) {
			Book book = (Book)it.next();
			BookRepresentation bRepresent = new BookRepresentation();
			bRepresent.setBookId(book.getId());
			bRepresent.setAuthor(book.getAuthor());
			bRepresent.setTitle(book.getTitle());
			bRepresent.setDescription(book.getDescription());
			bRepresent.setPrice(book.getPrice());
          
          //now add this representation in the list
			bRepresenList.add(bRepresent);
        }
		return bRepresenList;
	}

}
