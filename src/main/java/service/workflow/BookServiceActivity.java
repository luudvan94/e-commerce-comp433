package service.workflow;

import representation.BookRepresentation;
import representation.BookRequest;
import representation.BookReviewRepresentation;
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

public class BookServiceActivity {
	
	private static BookDomain bookDomain = new BookDomainImpl();
	private static BookReviewDomain bookReviewDomain = new BookReviewDomainImpl();
	private static PartnerDomain partnerDomain = new PartnerDomainImpl();
	
	public BookRepresentation get(String id) throws NotExistException {
		Book book = bookDomain.getBookById(id);
		
		return RepresentationConverter.toBookRepresentation(book.getBookID(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getPrice(), book.getQuantity(), book.getPartnerInfo());
	}
	
	public List<BookRepresentation> getBooksByTitle(String title) throws NotExistException {
		List<Book> books = bookDomain.getBooksByTitle(title);
		
		return books.stream().map(book -> RepresentationConverter.toBookRepresentation(book.getBookID(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getPrice(), book.getQuantity(), book.getPartnerInfo())).collect(Collectors.toList());
	}
	
	public List<BookRepresentation> getAll() throws NotExistException {
		List<Book> books = bookDomain.getAllBooks();
		
		return books.stream().map(book -> RepresentationConverter.toBookRepresentation(book.getBookID(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getPrice(), book.getQuantity(), book.getPartnerInfo())).collect(Collectors.toList());
	}
	
	public List<BookReviewRepresentation> getReviewsByBookID(String id) throws NotExistException {
		List<BookReview> bookReviews = bookReviewDomain.getReviewsByBookId(id);
		
		return bookReviews.stream().map(bookReview -> RepresentationConverter.toBookReviewRepresentation(bookReview.getId(), bookReview.getBook(), bookReview.getCustomerInfo(), bookReview.getContent())).collect(Collectors.toList());
	}
	
	public BookRepresentation createNewBook(BookRequest request) throws NotExistException {
		PartnerInfo partnerInfo = partnerDomain.getPartnerInfo(request.getPartnerID());
		
		String newID = bookDomain.addBook(request.getTitle(), request.getAuthor(), request.getDescription(), request.getPrice(), request.getQuantity(), partnerInfo);
		
		return RepresentationConverter.toBookRepresentation(newID, request.getTitle(), request.getAuthor(), request.getDescription(), request.getPrice(), request.getQuantity(), partnerInfo);
	}

}
