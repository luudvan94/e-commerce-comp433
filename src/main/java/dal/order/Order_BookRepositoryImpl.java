package dal.order;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import dal.AbstractRepository;
import domain.book_review.BookReview;
import domain.order.Order_Book;

public class Order_BookRepositoryImpl extends AbstractRepository<Order_Book, String> implements Order_BookRepository {
	
	@Override
	public void delete(Order_Book t) {
		Order_Book persistanceOrder_Book = get(t.getId());
		getSession().delete(persistanceOrder_Book);
	}

	@Override
	public void update(Order_Book t) {
		getSession().merge(t);
		
	}

	@Override
	public String create(Order_Book t) {
		return (String) getSession().save(t);
	}

	@Override
	public Order_Book get(String id) {
		return (Order_Book) getSession().get(Order_Book.class, id);
	}

	@Override
	public List<Order_Book> getAll() {
		Query query = getSession().createQuery("FROM Order_Book");
		return query.list();
	}

	@Override
	public void deleteAll() {
		Query query = getSession().createQuery("DELETE FROM Order_Book");
		query.executeUpdate();
	}

	@Override
	public List<Order_Book> byOrderID(String id) {
		Criteria crit = getSession().createCriteria(Order_Book.class);
		crit.add(Restrictions.eq("orderID",id));
		List<Order_Book> result = new ArrayList();
		
		result = crit.list();
		
		return result;
	}

	@Override
	public List<Order_Book> byBookID(String id) {
		Criteria crit = getSession().createCriteria(Order_Book.class);
		crit.add(Restrictions.eq("bookID",id));
		List<Order_Book> result = new ArrayList();
		
		result = crit.list();
		
		return result;
	}
}
