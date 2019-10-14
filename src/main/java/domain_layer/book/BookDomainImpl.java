package domain_layer.book;

import java.util.List;

import dal.book.BookRepository;
import dal.book.BookRepositoryImpl;
import entity.book.Book;
import exception.NotExistException;

public class BookDomainImpl implements BookDomain {
	
	private BookRepository bookRepository = new BookRepositoryImpl();

	@Override
	public Book getBookById(String id) throws NotExistException {
		Book book = bookRepository.get(id);
		
		if (book == null) {
			throw new NotExistException("Book with id does not exist");
		}
		
		return book; 
	}

	@Override
	public List<Book> getAllBooks() throws NotExistException {
		List<Book> books =  bookRepository.getAll();
		
		if (books.size() == 0) {
			throw new NotExistException("No book exists");
		}
		
		return books;
	}

	@Override
	public List<Book> getBooksByTitle(String title) throws NotExistException {
		List<Book> books = bookRepository.booksByTitle(title);
		if (books.size() == 0) {
			throw new NotExistException("Books with title do not exist");
		}
		
		return books;
	}

	@Override
	public String addBook(Book book) {
		return bookRepository.create(book);
	}

	@Override
	public boolean isBookStillAvailable(String id) throws NotExistException {
		Book book = getBookById(id);
		
		if (book.getQuantity() == 0) {
			return false;
		}
		
		return true;
	}

	@Override
	public void deleteBook(String id) throws NotExistException {
		Book book = this.getBookById(id);
		
		bookRepository.delete(book);
	}

	@Override
	public void updateBook(Book book) {
		bookRepository.update(book);
	}

	@Override
	public List<Book> getBooksByParterInfoID(String partnerInfoID) {
		return bookRepository.booksByPartnerInfoID(partnerInfoID);
	}
	
	
	
}
