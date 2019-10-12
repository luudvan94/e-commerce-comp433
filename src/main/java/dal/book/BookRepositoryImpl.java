package dal.book;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import dal.AbstractRepository;
import entity.book.Book;

public class BookRepositoryImpl extends AbstractRepository<Book, String> implements BookRepository {

	@Override
	public void delete(Book t) {
		Book persistanceBook = get(t.getId());
		getSession().delete(persistanceBook);		
	}

	@Override
	public void update(Book t) {
		getSession().merge(t);
		
	}

	@Override
	public String create(Book t) {
		return (String) getSession().save(t);
	}

	@Override
	public Book get(String id) {
		return (Book) getSession().get(Book.class, id);
	}

	@Override
	public List<Book> getAll() {
		Query query = getSession().createQuery("from Book");
		return query.list();
	}

	@Override
	public void deleteAll() {
		Query query = getSession().createQuery("DELETE FROM Book");
		query.executeUpdate();
		
	}

	@Override
	public List<Book> booksByTitle(String title) {
		Criteria crit = getSession().createCriteria(Book.class);
		crit.add(Restrictions.like("title",title, MatchMode.START));
		List<Book> result = new ArrayList();
		
		result = crit.list();
		
		return result;
	}

	@Override
	public List<Book> booksByPartnerID(String id) {
		Criteria crit = getSession().createCriteria(Book.class);
		crit.add(Restrictions.eq("partnerID",id));
		List<Book> result = new ArrayList();
		
		result = crit.list();
		
		return result;
	}

	@Override
	public List<Book> booksByIDList(List<String> idList) {
		Criteria crit = getSession().createCriteria(Book.class);
		crit.add(Restrictions.in("id", idList));
		List<Book> result = new ArrayList();
		
		result = crit.list();
		
		return result;
	}

}
