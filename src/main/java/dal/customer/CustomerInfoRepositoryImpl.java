package dal.customer;

import java.util.List;

import org.hibernate.Query;

import dal.AbstractRepository;
import domain.customer.CustomerInfo;

public class CustomerInfoRepositoryImpl extends AbstractRepository<CustomerInfo, String> implements CustomerInfoRepository {

	@Override
	public void delete(CustomerInfo t) {
		CustomerInfo persistanceCustomerInfo = get(t.getCustomerInfoID());
		getSession().delete(persistanceCustomerInfo);	
	}

	@Override
	public void update(CustomerInfo t) {
		getSession().merge(t);
		
	}

	@Override
	public String create(CustomerInfo t) {
		return (String) getSession().save(t);
	}

	@Override
	public CustomerInfo get(String id) {
		return (CustomerInfo) getSession().get(CustomerInfo.class, id);
	}

	@Override
	public List<CustomerInfo> getAll() {
		Query query = getSession().createQuery("FROM CustomerInfo");
		return query.list();
	}

	@Override
	public void deleteAll() {
		Query query = getSession().createQuery("DELETE * FROM CustomerInfo");
		query.executeUpdate();
		
	}

	@Override
	public CustomerInfo customerInfoByCustomerID(String id) {
		Query query = getSession().createQuery("FROM CustomerInfo WHERE customerID = :customerID");
		query.setParameter("customerID", id);
		List<CustomerInfo> result = query.list();
		
		if (result.size() > 0) {
			return result.get(0);
		}
		
		return null;
	}

	
}
