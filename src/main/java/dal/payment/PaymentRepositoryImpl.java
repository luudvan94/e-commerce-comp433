package dal.payment;

import java.util.List;

import org.hibernate.Query;

import dal.AbstractRepository;
import domain.payment.Payment;

public class PaymentRepositoryImpl extends AbstractRepository<Payment, String> implements PaymentRepository {

	@Override
	public void delete(Payment t) {
		Payment persistanceCardProfile = get(t.getId());
		getSession().delete(persistanceCardProfile);	
	}

	@Override
	public void update(Payment t) {
		getSession().merge(t);
		
	}

	@Override
	public String create(Payment t) {
		return (String) getSession().save(t);
	}

	@Override
	public Payment get(String id) {
		return (Payment) getSession().get(Payment.class, id);
	}

	@Override
	public List<Payment> getAll() {
		Query query = getSession().createQuery("FROM Payment");
		return query.list();
	}

	@Override
	public void deleteAll() {
		Query query = getSession().createQuery("DELETE FROM Payment");
		query.executeUpdate();
		
	}

	@Override
	public Payment cardProfileByOrderID(String id) {
		Query query = getSession().createQuery("FROM Payment WHERE orderID = :orderID");
		query.setParameter("orderID", id);
		List<Payment> result = query.list();
		
		if (result.size() > 0) {
			return result.get(0);
		}
		
		return null;
	}

	
}