package dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entity.Book;
import entity.BookReview;
import entity.Partner;
import entity.PartnerInfo;
import util.HibernateUtil;

public class BookReviewDAO {
	
	public static String addNewBookReview(String bookID, String content) {
		
		if (!BookDAO.isBookExist(bookID)) {
			return null;
		}
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		BookReview review = new BookReview();
		review.setId(BookReview.generateID());
		review.setContent(content);
		review.setDateCreated("" + new Date().getTime());
		review.setBook_id(bookID);
		
		String id = (String) session.save(review);
		session.getTransaction().commit();
		
		return id;
	}
	
	public static List<BookReview> reviewByBook(String bookID) {
		
		if (!BookDAO.isBookExist(bookID)) {
			return null;
		}
		
		Session session= HibernateUtil.getSessionFactory().openSession();
		Criteria crit = session.createCriteria(BookReview.class);
		crit.add(Restrictions.eq("bookID", bookID));
		List<BookReview> result = crit.list();
		
		if (result.size() > 0) {
			return result;
		}
		
		return null;
	}
}
