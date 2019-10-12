package org.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.AbstractHibernateTest;
import org.junit.Test;

import dal.book_review.BookReviewRepository;
import dal.book_review.BookReviewRepositoryImpl;
import entity.book_review.BookReview;
import entity.customer.CustomerInfo;
import util.EntityUtil;

public class BookReviewRepositoryImplTest extends AbstractHibernateTest {
	private BookReviewRepository bookReviewRepository;
	
	@Override
	public void setup() {
		super.setup();
		bookReviewRepository = new BookReviewRepositoryImpl();
	}
	
	@Override
	public void tearDown() {
		super.tearDown();
	}
	
	@Test
	public void getBookReview() {
		flushAndClearSession();
		
		BookReview bookReview = bookReviewRepository.get("BR123");
		assertTrue(bookReview.getId().equalsIgnoreCase("BR123"));
	}
	
	@Test
	public void getBookReview_WhenNotExist() {
		flushAndClearSession();
		
		BookReview bookReview = bookReviewRepository.get("BR123567");
		assertTrue(bookReview == null);
	}
	
	@Test
	public void getAll() {
		assertTrue(bookReviewRepository.getAll().size() == 3);
	}
	
	@Test
	public void create() {
		flushAndClearSession();
		
		String newID = bookReviewRepository.create(EntityUtil.bookReviewSample());
		assertTrue(newID != null);
		assertTrue(!newID.equalsIgnoreCase(""));
	}
	
	
	@Test
	public void update() {
		BookReview bookReview = bookReviewRepository.get("BR123");
		
		bookReview.setContent("this is very awesome!!!");
		bookReviewRepository.update(bookReview);
		
		flushAndClearSession();
		
		bookReview = bookReviewRepository.get("BR123");
		assertTrue(bookReview.getContent().equalsIgnoreCase("this is very awesome!!!"));
	}
	
	@Test
	public void delete() {
		bookReviewRepository.delete(bookReviewRepository.get("BR123"));
		
		flushAndClearSession();
		
		assertTrue(bookReviewRepository.getAll().size() == 2);
	}
	
	@Test
	public void deleteAll() {
		bookReviewRepository.deleteAll();
		
		flushAndClearSession();
		
		assertTrue(bookReviewRepository.getAll().size() == 0);
	}
	
	@Test
	public void bookReviewsByBookID() {
		List<BookReview> reviews = bookReviewRepository.bookReviewsByBookID("B123");
		assertTrue(reviews != null);
		assertTrue(reviews.size() == 3);
	}
	
	@Test
	public void bookReviewsByCustomerInfoID() {
		List<BookReview> reviews = bookReviewRepository.bookReviewsByCustomerInfoID("CI123");
		assertTrue(reviews != null);
		assertTrue(reviews.size() == 2);
	}
	
	@Test
	public void customerInfoByReview() {
		flushAndClearSession();
		
		BookReview bookReview = bookReviewRepository.get("BR123");
		assertTrue(bookReview.getCustomerInfo() != null);
		assertTrue(bookReview.getCustomerInfo().getCustomerInfoID().equalsIgnoreCase("CI123"));
	}
}
