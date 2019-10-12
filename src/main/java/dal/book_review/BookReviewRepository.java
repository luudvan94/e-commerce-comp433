package dal.book_review;

import java.util.List;

import dal.Repository;
import entity.book_review.BookReview;

public interface BookReviewRepository extends Repository<BookReview, String> {

	List<BookReview> bookReviewsByBookID(String id);
	List<BookReview> bookReviewsByCustomerInfoID(String id);
}
