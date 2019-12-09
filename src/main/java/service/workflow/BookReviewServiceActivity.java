package service.workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dal.book.BookRepository;
import dal.book.BookRepositoryImpl;
import domain_layer.book.BookDomain;
import domain_layer.book.BookDomainImpl;
import domain_layer.book_review.BookReviewDomain;
import domain_layer.book_review.BookReviewDomainImpl;
import domain_layer.customer.CustomerDomain;
import domain_layer.customer.CustomerDomainImpl;
import entity.book.Book;
import entity.book_review.BookReview;
import entity.customer.CustomerInfo;
import exception.NotExistException;
import exception.UnAuthorizedException;
import representation.BookReviewRepresentation;
import representation.BookReviewRequest;
import representation.Link;
import service.util.RepresentationConverter;

public class BookReviewServiceActivity {
	
	private BookReviewDomain bookReviewDomain = new BookReviewDomainImpl();
	
	public BookReviewRepresentation createNewBookReview(BookReviewRequest request) throws NotExistException {

		BookReview newReview = bookReviewDomain.addNewReview(request.getBookID(), request.getContent(), request.getCustomerID());
		
		return RepresentationConverter.toBookReviewRepresentation(newReview.getId(), newReview.getBook(), newReview.getCustomerInfo(), newReview.getContent(), this.linkForReview());
	}
	
	public BookReviewRepresentation updateReview(String customerID, String reviewID, String content) throws NotExistException, UnAuthorizedException {
		BookReview review = bookReviewDomain.updateReview(reviewID, content, customerID);
		
		return RepresentationConverter.toBookReviewRepresentation(review.getId(), review.getBook(), review.getCustomerInfo(), review.getContent(), this.linkForReview());
	}
	
	public List<BookReviewRepresentation> getBookReviews(String customerID) throws NotExistException {
		CustomerInfo customerInfo = new CustomerDomainImpl().getCustomerInfo(customerID);
		
		List<BookReview> reviews = bookReviewDomain.getReviewsByCustomerInfoId(customerInfo.getCustomerInfoID());
		
		return reviews.stream().map(bookReview -> RepresentationConverter.toBookReviewRepresentation(bookReview.getId(), bookReview.getBook(), bookReview.getCustomerInfo(), bookReview.getContent(), this.linkForReview())).collect(Collectors.toList());
		
	}
	
	public void deleteReview(String customerID, String reviewID) throws NotExistException, UnAuthorizedException {
		bookReviewDomain.deleteReview(customerID, reviewID);
	}
	
	public List<BookReviewRepresentation> getReviewsByBookID(String id) throws NotExistException {
		List<BookReview> bookReviews = bookReviewDomain.getReviewsByBookId(id);
		
		return bookReviews.stream().map(bookReview -> RepresentationConverter.toBookReviewRepresentation(bookReview.getId(), bookReview.getBook(), bookReview.getCustomerInfo(), bookReview.getContent(), this.linkForReview())).collect(Collectors.toList());
	}
	
	private List<Link> linkForReview() {
		List<Link> links = new ArrayList<>();
		Link create = new Link();
		create.setRel("create");
		create.setUrl("http://localhost:8080/bookstore/v1/reviews");
		create.setMediaType("application/json");
		
		links.add(create);
		
		return links;
	}
}
