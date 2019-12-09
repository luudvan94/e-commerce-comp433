package dal.customer;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import dal.AbstractRepository;
import dal.partner.PartnerRepository;
import entity.customer.Customer;
import util.Password;

public class CustomerRepositoryImpl extends AbstractRepository<Customer, String> implements CustomerRepository {
	
	@Override
	public void delete(Customer t) {
		Customer persistanceCustomer = get(t.getCustomerID());
		getSession().delete(persistanceCustomer);	
	}

	@Override
	public void update(Customer t) {
		t.setPassword(Password.encrypt(t.getPassword()));
		getSession().merge(t);
		
	}

	@Override
	public String create(Customer t) {
		if (isUserAlreadyExist(t.getUsername())) {
			return null;
		}
		
		return (String) getSession().save(t);
	}

	@Override
	public Customer get(String id) {
		return (Customer) getSession().get(Customer.class, id);
	}
	

	@Override
	public List<Customer> getAll() {
		Query query = getSession().createQuery("FROM Customer");
		return query.list();
	}

	@Override
	public void deleteAll() {
		Query query = getSession().createQuery("DELETE FROM Customer");
		query.executeUpdate();
	}

	@Override
	public Customer customerByUsernamePassword(String username, String password) {
		Criteria crit = getSession().createCriteria(Customer.class);
		crit.add(Restrictions.eq("username", username));
		crit.add(Restrictions.eq("password", password));
//		crit.add(Restrictions.eq("password", Password.encrypt(password)));
		List<Customer> result = crit.list();
		
		if (result.size() > 0) {
			return result.get(0);
		}
		
		return null;
	}

	@Override
	public boolean isUserAlreadyExist(String username) {
		Criteria crit = getSession().createCriteria(Customer.class);
		crit.add(Restrictions.eq("username", username));
		List<Customer> result = crit.list();
		
		if (result.size() == 0) {
			return false;
		}
		
		return true;
	}
}
