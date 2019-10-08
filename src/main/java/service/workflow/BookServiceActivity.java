package service.workflow;

import repository.BookRepository;
import repository.impl.BookRepositoryImpl;
import representation.BookRepresentation;
import representation.ObjectFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entity.Book;

public class BookServiceActivity {
	
	private static BookRepository bo = new BookRepositoryImpl();
	private static ObjectFactory factory = new ObjectFactory();
	
	public BookRepresentation get(String id) {
		Book book = bo.get(id);
		
		BookRepresentation bRepresent = factory.createBookRepresentation();
		bRepresent.setBookId(book.getId());
		bRepresent.setAuthor(book.getAuthor());
		bRepresent.setTitle(book.getTitle());
		bRepresent.setDescription(book.getDescription());
		bRepresent.setPrice(book.getPrice());
		
		return bRepresent;
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
