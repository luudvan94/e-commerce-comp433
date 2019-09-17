package repository;

import java.util.List;

import entity.BookReview;

public interface BookReviewRepository extends Repository<BookReview, String> {

	List<BookReview> bookReviewsByBookID(String id);
	List<BookReview> bookReviewsByCustomerID(String id);
}
