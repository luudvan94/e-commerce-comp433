package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import entity.Book;
import entity.Partner;
import util.HibernateUtil;

public class BookDAO {
	
	public static boolean isBookExist(String bookID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria crit = session.createCriteria(Book.class);
		crit.add(Restrictions.eq("id",bookID));
		
		List<Book> result = crit.list();
		
		if (result.size() == 0) {
			return false;
		}
		
		return true;
	}

	public static List<Book> getAllBooks() {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria cri = session.createCriteria(Book.class);
		List<Book> result = cri.list();
		
		
		return result;
	}
	
	public static Book getBookById(String id) {
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

	public static List<Book> getBooksByTitle(String title) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Criteria crit = session.createCriteria(Book.class);
		crit.add(Restrictions.like("title",title, MatchMode.START));
		List<Book> result = (List<Book>) crit.list();
		
		if (result.size() == 0) {
			return null;
		}
		
		
		return result;
	}
	
	public static List<Book> getBooksByPartnerID(String partnerId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Criteria crit = session.createCriteria(Book.class);
		crit.add(Restrictions.eq("partnerID",partnerId));
		List<Book> result = (List<Book>) crit.list();
		
		if (result.size() == 0) {
			return null;
		}
		
		
		return result;
	}

	public static void addNewBook(Book book) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
	       
		session.save(book);
	       
      	session.getTransaction().commit();
      
		
	}
	
	

}
