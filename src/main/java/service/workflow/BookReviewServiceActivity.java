package service.workflow;

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
import service.util.RepresentationConverter;

public class BookReviewServiceActivity {
	
	private BookReviewDomain bookReviewDomain = new BookReviewDomainImpl();
	private BookDomain bookDomain = new BookDomainImpl();
	private CustomerDomain customerDomain = new CustomerDomainImpl();
	
	public BookReviewRepresentation createNewBookReview(BookReviewRequest request) throws NotExistException {

		BookReview newReview = bookReviewDomain.addNewReview(request.getBookID(), request.getContent(), request.getCustomerID());
		
		return RepresentationConverter.toBookReviewRepresentation(newReview.getId(), newReview.getBook(), newReview.getCustomerInfo(), newReview.getContent());
	}
	
	public BookReviewRepresentation updateReview(String customerID, String reviewID, String content) throws NotExistException, UnAuthorizedException {
		BookReview review = bookReviewDomain.updateReview(reviewID, content, customerID);
		
		return RepresentationConverter.toBookReviewRepresentation(review.getId(), review.getBook(), review.getCustomerInfo(), review.getContent());
	}
}
