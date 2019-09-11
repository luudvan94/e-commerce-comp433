
import org.hibernate.Session;

import java.util.List;

import org.hibernate.Criteria;
import util.HibernateUtil;

import entity.Book;

public class TestHibernate {

	public static void main(String[] args)
	   {
	      Session session = HibernateUtil.getSessionFactory().openSession();
	      Criteria cri = session.createCriteria(Book.class);
	      List<Book> books = cri.list();
	      
	      System.out.println(books.size());
	      HibernateUtil.shutdown();
	      
	      
	      
	   }
}
