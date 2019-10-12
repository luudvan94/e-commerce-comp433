package org.repository;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.AbstractHibernateTest;
import org.junit.Test;

import dal.book.BookRepository;
import dal.book.BookRepositoryImpl;
import entity.book.Book;
import util.EntityUtil;

public class BookRepositoryImplTest extends AbstractHibernateTest {
	
	private BookRepository bookRepository;
	
	@Override
	public void setup() {
		super.setup();
		bookRepository = new BookRepositoryImpl();
	}
	
	@Override
	public void tearDown() {
		super.tearDown();
	}
	
	@Test
	public void getBook() {
		flushAndClearSession();
		
		Book bookFromDb = bookRepository.get("B123");
		assertTrue(bookFromDb.getId().equalsIgnoreCase("B123"));
	}
	
	@Test
	public void getBook_WhenNotExist() {
		flushAndClearSession();
		
		Book bookFromDb = bookRepository.get("B123567");
		assertTrue(bookFromDb == null);
	}
	
	@Test
	public void getAll() {
		assertTrue(bookRepository.getAll().size() == 4);
	}
	
	@Test
	public void create() {
		flushAndClearSession();
		
		String newID = bookRepository.create(EntityUtil.bookSample());
		assertTrue(newID != null);
		assertTrue(!newID.equalsIgnoreCase(""));
	}
	
	@Test
	public void update() {
		Book book = bookRepository.get("B123");
		
		book.setTitle("Updated Title");
		bookRepository.update(book);
		
		flushAndClearSession();
		
		book = bookRepository.get("B123");
		assertTrue(book.getTitle().equalsIgnoreCase("Updated Title"));
	}
	
	@Test
	public void delete() {
		bookRepository.delete(bookRepository.get("B123"));
		
		flushAndClearSession();
		
		assertTrue(bookRepository.getAll().size() == 3);
	}
	
	@Test
	public void deleteAll() {
		bookRepository.deleteAll();
		
		flushAndClearSession();
		
		assertTrue(bookRepository.getAll().size() == 0);
	}
	
	@Test
	public void booksByTitle() {
		assertTrue(bookRepository.booksByTitle("A").size() == 2);
	}
	
	@Test
	public void booksByTitle_NotExist() {
		assertTrue(bookRepository.booksByTitle("Z").size() == 0);
	}
	
	@Test
	public void booksByPartnerID() {
		assertTrue(bookRepository.booksByPartnerID("P1234").size() == 4);
	}
	
	@Test
	public void booksByPartnerID_NotExist() {
		assertTrue(bookRepository.booksByPartnerID("P1234567").size() == 0);
	}
	
	@Test
	public void booksByIDList() {
		List<Book> books = bookRepository.booksByIDList(Arrays.asList("B123", "B789"));
		assertTrue(books != null);
		assertTrue(books.size() == 2);
	}
	
//	@Test
//	public void create_WhenIDAlreadyExist() {
////		flushAndClearSession();
//		
//		Book book = EntityUtil.sample();
//		book.setId("B123");
//		String newID = bookRepository.create(book);
//		System.out.println(book.getId());
//		System.out.println(newID);
//		assertTrue(newID.equalsIgnoreCase(""));
//		assertTrue(newID == null);
//		
//	}
	
	
	
	
	
	
	
	
}
