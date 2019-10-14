package domain_layer.book;

import java.util.List;

import entity.book.Book;
import exception.NotExistException;
import exception.OperationFailedException;

public interface BookDomain {
	
	Book getBookById(String id) throws NotExistException;
	
	List<Book> getAllBooks() throws NotExistException;
	
	List<Book> getBooksByTitle(String title) throws NotExistException;
	
	String addBook(Book book);
	
	boolean isBookStillAvailable(String id) throws NotExistException;
	
	void deleteBook(String id) throws NotExistException;
	
	void updateBook(Book book);

	List<Book> getBooksByParterInfoID(String partnerInfoID);
}
