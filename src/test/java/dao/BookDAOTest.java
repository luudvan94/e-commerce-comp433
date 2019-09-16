package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import entity.Book;
import util.HibernateUtil;
import dao.BookDAO;

public class BookDAOTest {
	
	@Test
	public void listBooksShouldReturnCorrectNumber() {
		
		List<Book> result = BookDAO.getAllBooks();
		
		assertEquals("the size of books not correct", 4, result.size());
	}
	
	@Test
	public void shouldReturnCorrectDetailOfBook() {
		
		Book book = BookDAO.getBookById("B123");
		
		assertEquals("Book detail is not correct", "Artificial Intelligence in Practice", book.getTitle());
	}
	
	@Test
	public void shouldReturnNullObject() {
		
		Book book = BookDAO.getBookById("B346");
		
		assertEquals("Book shouldn't be exist", null, book);
	}
	
	@Test
	public void shouldReturnCorrectNumberOfBookBySearching() {
		
		List<Book> result = BookDAO.getBooksByTitle("A");
		
		assertEquals("Number of Book returned not correct", 2, result.size());
	}
	
	@Test
	public void shouldReturnCorrectNumberOfBooksAfterAdd() {
		
		Book book = new Book();
		book.setId(Book.generateID());
		book.setTitle("Data Smart: Using Data Science to Transform Information into Insight");
		book.setAuthor("John W. Foreman");
		book.setDescription("Data Science gets thrown around in the press like it's magic. Major retailers are predicting everything from when their customers are pregnant to when they want a new pair of Chuck Taylors. It's a brave new world where seemingly meaningless data can be transformed into valuable insight to drive smart business decisions.");
		book.setPrice(22.95);
		book.setPartnerID("P1234");
		BookDAO.addNewBook(book);
		
		assertEquals("Number of Book is not correct", 5, BookDAO.getAllBooks().size());
		
	}
	
	@Test
	public void shouldReturnCorrectNumberOfBookByPartnerID() {
		List<Book> result = BookDAO.getBooksByPartnerID("P1234");
		
		assertEquals("Number of Book returned not correct", 4, result.size());
	}
	
	@Test
	public void shouldReturnNullByPartnerID() {
		List<Book> result = BookDAO.getBooksByPartnerID("P12345");
		
		assertTrue(result == null);
	}
	
	@AfterClass
	public static void shutDownSession() {
		HibernateUtil.shutdown();
	}

}
