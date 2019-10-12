package dal.order;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import dal.AbstractRepository;
import entity.book.Book;
import entity.order.Order1;

public class OrderRepositoryImpl extends AbstractRepository<Order1, String> implements OrderRepository {
	
	@Override
	public void delete(Order1 t) {
		Order1 persistanceOrder = get(t.getId());
		getSession().delete(persistanceOrder);
	}

	@Override
	public void update(Order1 t) {
		getSession().merge(t);
		
	}

	@Override
	public String create(Order1 t) {
		return (String) getSession().save(t);
	}

	@Override
	public Order1 get(String id) {
		return (Order1) getSession().get(Order1.class, id);
	}

	@Override
	public List<Order1> getAll() {
		Query query = getSession().createQuery("FROM Order1");
		return query.list();
	}

	@Override
	public void deleteAll() {
		Query query = getSession().createQuery("DELETE FROM Order1");
		query.executeUpdate();
	}

	@Override
	public List<Order1> ordersByCustomerID(String id) {
		Criteria crit = getSession().createCriteria(Order1.class);
		crit.add(Restrictions.eq("customerID",id));
		List<Order1> result = new ArrayList();
		
		result = crit.list();
		
		return result;
	}

	@Override
	public List<Order1> ordersByPartnerID(String id) {
		Criteria crit = getSession().createCriteria(Order1.class);
		crit.add(Restrictions.eq("partnerID",id));
		List<Order1> result = new ArrayList();
		
		result = crit.list();
		
		return result;
	}
	
	@Override
	public List<Order1> ordersByIDList(List<String> idList) {
		Criteria crit = getSession().createCriteria(Order1.class);
		crit.add(Restrictions.in("id", idList));
		List<Order1> result = new ArrayList();
		
		result = crit.list();
		
		return result;
	}

}
