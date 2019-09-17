package repository.impl;

import java.util.List;

import org.hibernate.Query;

import entity.ShippingAddress;
import repository.AbstractRepository;
import repository.ShippingAddressRepository;

public class ShippingAddressRepositoryImpl extends AbstractRepository<ShippingAddress, String> implements ShippingAddressRepository {

	@Override
	public void delete(ShippingAddress t) {
		ShippingAddress persistanceShippingAddress = get(t.getId());
		getSession().delete(persistanceShippingAddress);	
	}

	@Override
	public void update(ShippingAddress t) {
		getSession().merge(t);
		
	}

	@Override
	public String create(ShippingAddress t) {
		return (String) getSession().save(t);
	}

	@Override
	public ShippingAddress get(String id) {
		return (ShippingAddress) getSession().get(ShippingAddress.class, id);
	}

	@Override
	public List<ShippingAddress> getAll() {
		Query query = getSession().createQuery("FROM ShippingAddress");
		return query.list();
	}

	@Override
	public void deleteAll() {
		Query query = getSession().createQuery("DELETE FROM ShippingAddress");
		query.executeUpdate();
		
	}

	@Override
	public ShippingAddress shippingAddressByCustomerID(String id) {
		Query query = getSession().createQuery("FROM ShippingAddress WHERE customerID = :customerID");
		query.setParameter("customerID", id);
		List<ShippingAddress> result = query.list();
		
		if (result.size() > 0) {
			return result.get(0);
		}
		
		return null;
	}

	
}