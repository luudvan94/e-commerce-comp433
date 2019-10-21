package domain_layer.book;

import java.util.List;

import dal.book.BookRepository;
import dal.book.BookRepositoryImpl;
import domain_layer.partner.PartnerDomain;
import domain_layer.partner.PartnerDomainImpl;
import entity.book.Book;
import entity.partner.PartnerInfo;
import exception.NotExistException;
import exception.UnAuthorizedException;
import exception.UnAvailableException;
import util.ID;

public class BookDomainImpl implements BookDomain {
	
//	private PartnerDomain partnerDomain = new PartnerDomainImpl();
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
	public boolean isBookStillAvailable(String id) throws NotExistException, UnAvailableException {
		Book book = getBookById(id);
		
		if (book.getQuantity() == 0) {
			throw new UnAvailableException("The quantity of this book is un-available for purchase");
		}
		
		return true;
	}

	@Override
	public void updateBook(Book book) {
		bookRepository.update(book);
	}

	@Override
	public List<Book> getBooksByParterInfoID(String partnerInfoID) {
		return bookRepository.booksByPartnerInfoID(partnerInfoID);
	}

	@Override
	public String addBook(String title, String author, String description, double price, int quantity,
			PartnerInfo partnerInfo) throws NotExistException {
		
		Book book = new Book();
		book.setBookID(ID.generateID("B"));
		book.setTitle(title);
		book.setAuthor(author);
		book.setDescription(description);
		book.setPrice(price);
		book.setQuantity(quantity);
		book.setPartnerInfo(partnerInfo);
		
		return bookRepository.create(book);
	}
	
	@Override
	public void deleteBook(String partnerID, String bookID) throws NotExistException, UnAuthorizedException {
		Book book = this.getBookById(bookID);
		
		if (!book.getPartnerInfo().getPartnerID().equalsIgnoreCase(partnerID)) {
			throw new UnAuthorizedException("This book can not be deleted by partner with provided ID");
		}
		
		bookRepository.delete(book);
		
	}
	
}
