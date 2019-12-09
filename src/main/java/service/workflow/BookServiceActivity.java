package service.workflow;

import representation.BookRepresentation;
import representation.BookRequest;
import representation.BookReviewRepresentation;
import representation.Link;
import representation.ObjectFactory;
import service.util.RepresentationConverter;
import util.ID;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import dal.book.BookRepository;
import dal.book.BookRepositoryImpl;
import dal.book_review.BookReviewRepository;
import dal.book_review.BookReviewRepositoryImpl;
import domain_layer.book.BookDomain;
import domain_layer.book.BookDomainImpl;
import domain_layer.book_review.BookReviewDomain;
import domain_layer.book_review.BookReviewDomainImpl;
import domain_layer.partner.PartnerDomain;
import domain_layer.partner.PartnerDomainImpl;
import entity.book.Book;
import entity.book_review.BookReview;
import entity.partner.PartnerInfo;
import exception.NoContentException;
import exception.NotExistException;
import exception.UnAuthorizedException;

public class BookServiceActivity {
	
	private static BookDomain bookDomain = new BookDomainImpl();
	private static BookReviewDomain bookReviewDomain = new BookReviewDomainImpl();
	private static PartnerDomain partnerDomain = new PartnerDomainImpl();
	
	public BookRepresentation get(String id) throws NotExistException {
		Book book = bookDomain.getBookById(id);
		
		return RepresentationConverter.toBookRepresentation(book.getBookID(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getPrice(), book.getQuantity(), book.getPartnerInfo(), this.linkForBook(book.getBookID()));
	}
	
	public List<BookRepresentation> getBooksByTitle(String title) throws NotExistException {
		List<Book> books = bookDomain.getBooksByTitle(title);
		
		return books.stream().map(book -> RepresentationConverter.toBookRepresentation(book.getBookID(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getPrice(), book.getQuantity(), book.getPartnerInfo(), this.linkForBook(book.getBookID()))).collect(Collectors.toList());
	}
	
	public List<BookRepresentation> getAll() throws NotExistException {
		List<Book> books = bookDomain.getAllBooks();
		
		return books.stream().map(book -> RepresentationConverter.toBookRepresentation(book.getBookID(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getPrice(), book.getQuantity(), book.getPartnerInfo(), this.linkForBook(book.getBookID()))).collect(Collectors.toList());
	}
	
	public BookRepresentation createNewBook(BookRequest request) throws NotExistException {
		PartnerInfo partnerInfo = partnerDomain.getPartnerInfo(request.getPartnerID());
		
		String newID = bookDomain.addBook(request.getTitle(), request.getAuthor(), request.getDescription(), request.getPrice(), request.getQuantity(), partnerInfo);
		
		return RepresentationConverter.toBookRepresentation(newID, request.getTitle(), request.getAuthor(), request.getDescription(), request.getPrice(), request.getQuantity(), partnerInfo, this.linkForBook(newID));
	}
	
	public void deleteBook(String partnerID, String bookID) throws NotExistException, UnAuthorizedException {
		bookDomain.deleteBook(partnerID, bookID);
	}

	public List<BookRepresentation> getPartnerBooks(String partnerID) throws NotExistException {
//		System.out.println(partnerID);
		PartnerInfo info = new PartnerDomainImpl().getPartnerInfo(partnerID);
		List<Book> books = info.getBooks();
		
		return books.stream().map(book -> RepresentationConverter.toBookRepresentation(book.getBookID(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getPrice(), book.getQuantity(), book.getPartnerInfo(), this.linkForBook(book.getBookID()))).collect(Collectors.toList());
		
	}
	
	private List<Link> linkForBook(String bookID) {
		List<Link> links = new ArrayList<>();
		Link buy = new Link();
		buy.setRel("order");
		buy.setUrl("http://localhost:8080/bookstore/v1/orders");
		buy.setMediaType("application/json");
		
		Link review = new Link();
		review.setRel("reviews");
		review.setUrl("http://localhost:8080/bookstore/v1/books/" + bookID + "/reviews");
		review.setMediaType("application/json");
		
		Link self = new Link();
		self.setRel("self");
		self.setUrl("http://localhost:8080/bookstore/v1/books/" + bookID);
		self.setMediaType("application/json");
		
		Link pay = new Link();
		pay.setRel("pay");
		pay.setUrl("http://localhost:8080/bookstore/v1/payments");
		pay.setMediaType("application/json");
		
		Link login = new Link();
		login.setRel("login");
		login.setUrl("http://localhost:8080/bookstore/v1/customers/login");
		login.setMediaType("application/json");
		
		links.add(buy);
		links.add(pay);
		links.add(review);
		links.add(self);
		links.add(login);
		
		return links;
	}
}
