package dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import entity.BookReview;
import entity.PartnerInfo;
import util.HibernateUtil;

public class BookReviewDAOTest {
	@Test
	public void shouldNotCreateNonExistedBookSuccess() {
		assertTrue(BookReviewDAO.addNewBookReview("B143423", "abc") == null);
	}
	
	@Test
	public void shouldSuccessfullyCreateBookReview() {
		assertTrue(BookReviewDAO.addNewBookReview("B123", "abc") != null);
	}
	
	@Test
	public void shouldReturnBookReviewsForExistedBook() {
		assertTrue(BookReviewDAO.reviewByBook("B123") != null);
	}
	
	@Test
	public void shouldNotReturnReviewsForNonExistedBook() {
		assertTrue(BookReviewDAO.reviewByBook("B1234567") == null);
	}
	
	@Test
	public void shouldReturnCorrectNumberOfReview() {
		List<BookReview> reviews = BookReviewDAO.reviewByBook("B123");
		
		assertTrue(reviews.size() == 3);
	}
	
	@Test
	public void shouldReturnCorrectContentOfReview() {
		List<BookReview> reviews = BookReviewDAO.reviewByBook("B123");
		
		assertTrue(reviews.get(0).getContent().equalsIgnoreCase("This book is very great! yeahhhhhh"));
	}

	@AfterClass
	public static void shutDownSession() {
		HibernateUtil.shutdown();
	}
}
