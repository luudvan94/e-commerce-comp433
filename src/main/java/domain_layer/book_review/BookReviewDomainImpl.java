package domain_layer.book_review;

import java.util.Date;
import java.util.List;

import dal.book.BookRepository;
import dal.book.BookRepositoryImpl;
import dal.book_review.BookReviewRepository;
import dal.book_review.BookReviewRepositoryImpl;
import domain_layer.book.BookDomain;
import domain_layer.book.BookDomainImpl;
import domain_layer.customer.CustomerDomain;
import domain_layer.customer.CustomerDomainImpl;
import entity.book.Book;
import entity.book_review.BookReview;
import entity.customer.CustomerInfo;
import exception.NotExistException;
import exception.UnAuthorizedException;
import util.ID;

public class BookReviewDomainImpl implements BookReviewDomain {
	
	private BookReviewRepository bookReviewRepository = new BookReviewRepositoryImpl();
	private BookDomain bookDomain = new BookDomainImpl();

	@Override
	public List<BookReview> getReviewsByBookId(String id) throws NotExistException {
		List<BookReview> reviews = bookReviewRepository.bookReviewsByBookID(id);
		
		if (reviews.size() == 0) {
			throw new NotExistException("No reviews exist for provided book id");
		}
		
		return reviews;
	}

	@Override
	public BookReview addNewReview(String bookID, String content, String customerInfoID) throws NotExistException {
		
		Book book = bookDomain.getBookById(bookID);
		
		CustomerInfo customerInfo = new CustomerDomainImpl().getCustomerInfo(customerInfoID);
		
		BookReview review = new BookReview();
		review.setId(ID.generateID("BR"));
		review.setBook(book);
		review.setCustomerInfo(customerInfo);
		review.setContent(content);
		review.setDateCreated(Long.toString(new Date().getTime()));
		
		bookReviewRepository.create(review);
		
		return review;
	}

	@Override
	public void deleteReview(String id) throws NotExistException {
		BookReview review = bookReviewRepository.get(id);
		
		bookReviewRepository.delete(review);
	}

	@Override
	public List<BookReview> getReviewsByCustomerInfoId(String id) throws NotExistException {
		List<BookReview> reviews = bookReviewRepository.bookReviewsByCustomerInfoID(id);
		
		if (reviews.size() == 0) {
			throw new NotExistException("No reviews existed for provided customer id");
		}
		
		return reviews;
	}

	@Override
	public BookReview getBookReview(String id) throws NotExistException {
		BookReview review = bookReviewRepository.get(id);
		
		if (review == null) {
			throw new NotExistException("Book Review with provided id does not exist");
		}
		
		return review;
	}

	@Override
	public BookReview updateReview(String bookReviewID, String content, String customerID) throws NotExistException, UnAuthorizedException {
		
		BookReview review = this.getBookReview(bookReviewID);
		
		if (!review.getCustomerInfo().getCustomerID().equalsIgnoreCase(customerID)) {
			throw new UnAuthorizedException("Book Review can not be edited with provided customer ID");
		}
		
		review.setContent(content);
		bookReviewRepository.update(review);
		
		return review;
		
		
	}

}
