package domain_layer.book;

import java.util.List;

import entity.book.Book;
import entity.partner.PartnerInfo;
import exception.NotExistException;
import exception.OperationFailedException;
import exception.UnAvailableException;

public interface BookDomain {
	
	Book getBookById(String id) throws NotExistException;
	
	List<Book> getAllBooks() throws NotExistException;
	
	List<Book> getBooksByTitle(String title) throws NotExistException;
	
	String addBook(String title, String author, String description, double price, int quantity, PartnerInfo partnerInfo) throws NotExistException;
	
	boolean isBookStillAvailable(String id) throws NotExistException, UnAvailableException;
	
	void deleteBook(String id) throws NotExistException;
	
	void updateBook(Book book);

	List<Book> getBooksByParterInfoID(String partnerInfoID);
}
