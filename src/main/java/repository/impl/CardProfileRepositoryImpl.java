package repository.impl;

import java.util.List;

import org.hibernate.Query;

import entity.CardProfile;
import repository.AbstractRepository;
import repository.CardProfileRepository;

public class CardProfileRepositoryImpl extends AbstractRepository<CardProfile, String> implements CardProfileRepository {

	@Override
	public void delete(CardProfile t) {
		CardProfile persistanceCardProfile = get(t.getId());
		getSession().delete(persistanceCardProfile);	
	}

	@Override
	public void update(CardProfile t) {
		getSession().merge(t);
		
	}

	@Override
	public String create(CardProfile t) {
		return (String) getSession().save(t);
	}

	@Override
	public CardProfile get(String id) {
		return (CardProfile) getSession().get(CardProfile.class, id);
	}

	@Override
	public List<CardProfile> getAll() {
		Query query = getSession().createQuery("FROM CardProfile");
		return query.list();
	}

	@Override
	public void deleteAll() {
		Query query = getSession().createQuery("DELETE FROM CardProfile");
		query.executeUpdate();
		
	}

	@Override
	public CardProfile cardProfileByCustomerID(String id) {
		Query query = getSession().createQuery("FROM CardProfile WHERE customerID = :customerID");
		query.setParameter("customerID", id);
		List<CardProfile> result = query.list();
		
		if (result.size() > 0) {
			return result.get(0);
		}
		
		return null;
	}

	
}