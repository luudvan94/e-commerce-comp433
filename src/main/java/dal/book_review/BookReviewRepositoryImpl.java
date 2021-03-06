package dal.book_review;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import dal.AbstractRepository;
import entity.book.Book;
import entity.book_review.BookReview;

public class BookReviewRepositoryImpl extends AbstractRepository<BookReview, String> implements BookReviewRepository {

	@Override
	public void delete(BookReview t) {
		BookReview persistancebookReview = get(t.getId());
		getSession().delete(persistancebookReview);
	}

	@Override
	public void update(BookReview t) {
		getSession().merge(t);
		
	}

	@Override
	public String create(BookReview t) {
		return (String) getSession().save(t);
	}

	@Override
	public BookReview get(String id) {
		return (BookReview) getSession().get(BookReview.class, id);
	}

	@Override
	public List<BookReview> getAll() {
		Query query = getSession().createQuery("FROM BookReview");
		return query.list();
	}

	@Override
	public void deleteAll() {
		Query query = getSession().createQuery("DELETE FROM BookReview");
		query.executeUpdate();
		
	}

	@Override
	public List<BookReview> bookReviewsByBookID(String id) {
		Criteria crit = getSession().createCriteria(BookReview.class);
		crit.add(Restrictions.eq("book.bookID",id));
		List<BookReview> result = new ArrayList();
		
		result = crit.list();
		
		return result;
	}

	@Override
	public List<BookReview> bookReviewsByCustomerInfoID(String id) {
		Criteria crit = getSession().createCriteria(BookReview.class);
		crit.add(Restrictions.eq("customerInfo.customerInfoID",id));
		List<BookReview> result = new ArrayList();
		
		result = crit.list();
		
		return result;
	}
	
}
