package domain_layer.book_review;

import java.util.List;

import entity.book.Book;
import entity.book_review.BookReview;
import entity.customer.CustomerInfo;
import exception.NotExistException;
import exception.UnAuthorizedException;

public interface BookReviewDomain {
	
	BookReview getBookReview(String id) throws NotExistException;

	List<BookReview> getReviewsByBookId(String id) throws NotExistException;
	
	List<BookReview> getReviewsByCustomerInfoId(String id) throws NotExistException;
	
	BookReview addNewReview(String bookID, String content, String customerInfoID) throws NotExistException;
	
	BookReview updateReview(String bookReviewID, String content, String customerID) throws NotExistException, UnAuthorizedException;
	
	void deleteReview(String id) throws NotExistException;
	
}
