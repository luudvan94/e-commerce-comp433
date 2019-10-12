package domain_layer.book_review;

import java.util.List;

import dal.book_review.BookReviewRepository;
import dal.book_review.BookReviewRepositoryImpl;
import entity.book_review.BookReview;
import exception.NotExistException;

public class BookReviewDomainImpl implements BookReviewDomain {
	
	private BookReviewRepository bookReviewRepository = new BookReviewRepositoryImpl();

	@Override
	public List<BookReview> getReviewsByBookId(String id) {
		List<BookReview> reviews = bookReviewRepository.bookReviewsByBookID(id);
		
		return reviews;
	}

	@Override
	public String addNewReview(BookReview review) {
		return bookReviewRepository.create(review);
	}

	@Override
	public void deleteReview(String id) throws NotExistException {
		BookReview review = bookReviewRepository.get(id);
		
		bookReviewRepository.delete(review);
	}

	@Override
	public void updateReview(BookReview review) {
		bookReviewRepository.update(review);		
	}

}
