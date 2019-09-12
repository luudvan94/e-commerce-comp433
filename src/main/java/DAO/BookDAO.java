package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import entity.Book;
import util.HibernateUtil;

public class BookDAO {
	
	public static final String INSERT_QUERY = "INSERT INTO BOOK(title, author, description, price) VALUES (:title, :author, :description, :price)";

	public List<Book> getAllBooks() {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria cri = session.createCriteria(Book.class);
		List<Book> result = cri.list();
		
		
		return result;
	}
	
	public Book getBookById(String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Criteria crit = session.createCriteria(Book.class);
		crit.add(Restrictions.eq("id",id));
		List<Book> books = crit.list();
		
		if (books.size() == 0) {
			return null;
		}
		Book result = (Book) books.get(0);
		
		
		return result;
	}

	public List<Book> getBooksByTitle(String title) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Criteria crit = session.createCriteria(Book.class);
		crit.add(Restrictions.like("title",title, MatchMode.START));
		List<Book> result = crit.list();
		
		if (result.size() == 0) {
			return null;
		}
		
		
		return result;
	}

	public void addNewBook(Book book) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
	       
		session.save(book);
	       
      	session.getTransaction().commit();
      
		
	}

}
