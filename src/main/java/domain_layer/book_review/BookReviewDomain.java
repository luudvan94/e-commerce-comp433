package domain_layer.book_review;

import java.util.List;

import entity.book_review.BookReview;
import exception.NotExistException;

public interface BookReviewDomain {

	List<BookReview> getReviewsByBookId(String id) throws NotExistException;
	
//	List<BookReview> getReviewsByCustomerId(String id) throws NotExistException;
	
	String addNewReview(BookReview review);
	
	void deleteReview(String id) throws NotExistException;
	
	void updateReview(BookReview review);
}
