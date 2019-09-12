package dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import entity.Book;
import util.HibernateUtil;
import dao.BookDAO;

public class BookDAOTest {
	
	@Test
	public void listBooksShouldReturnCorrectNumber() {
		BookDAO dao = new BookDAO();
		
		List<Book> result = dao.getAllBooks();
		
		assertEquals("the size of books not correct", 4, result.size());
	}
	
	@Test
	public void shouldReturnCorrectDetailOfBook() {
		BookDAO dao = new BookDAO();
		
		Book book = dao.getBookById("B123");
		
		assertEquals("Book detail is not correct", "Artificial Intelligence in Practice", book.getTitle());
	}
	
	@Test
	public void shouldReturnNullObject() {
		BookDAO dao = new BookDAO();
		
		Book book = dao.getBookById("B346");
		
		assertEquals("Book shouldn't be exist", null, book);
	}
	
	@Test
	public void shouldReturnCorrectNumberOfBookBySearching() {
		BookDAO dao = new BookDAO();
		
		List<Book> result = dao.getBooksByTitle("A");
		
		assertEquals("Number of Book returned not correct", 2, result.size());
	}
	
	@Test
	public void shouldReturnCorrectNumberOfBooksAfterAdd() {
		BookDAO dao = new BookDAO();
		
		Book book = new Book();
		book.setId(Book.generateID());
		book.setTitle("Data Smart: Using Data Science to Transform Information into Insight");
		book.setAuthor("John W. Foreman");
		book.setDescription("Data Science gets thrown around in the press like it's magic. Major retailers are predicting everything from when their customers are pregnant to when they want a new pair of Chuck Taylors. It's a brave new world where seemingly meaningless data can be transformed into valuable insight to drive smart business decisions.");
		book.setPrice(22.95);
		dao.addNewBook(book);
		
		assertEquals("Number of Book is not correct", 5, dao.getAllBooks().size());
		
	}
	
	@AfterClass
	public static void shutDownSession() {
		HibernateUtil.shutdown();
	}

}
