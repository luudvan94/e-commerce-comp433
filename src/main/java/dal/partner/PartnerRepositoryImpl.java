package dal.partner;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import dal.AbstractRepository;
import entity.book.Book;
import entity.partner.Partner;
import util.Password;

public class PartnerRepositoryImpl extends AbstractRepository<Partner, String> implements PartnerRepository {

	@Override
	public void delete(Partner t) {
		Partner persistancePartner = get(t.getPartnerID());
		getSession().delete(persistancePartner);	
	}

	@Override
	public void update(Partner t) {
		t.setPassword(Password.encrypt(t.getPassword()));
		getSession().merge(t);
		
	}

	@Override
	public String create(Partner t) {
		if (this.partnerWithUsername(t.getUsername()) != null) {
			return null;
		}
		
		return (String) getSession().save(t);
	}

	@Override
	public Partner get(String id) {
		return (Partner) getSession().get(Partner.class, id);
	}
	

	@Override
	public List<Partner> getAll() {
		Query query = getSession().createQuery("FROM Partner");
		return query.list();
	}

	@Override
	public void deleteAll() {
		Query query = getSession().createQuery("DELETE FROM Partner");
		query.executeUpdate();
	}

	@Override
	public Partner partnerWithUsernamePassword(String username, String password) {
		Criteria crit = getSession().createCriteria(Partner.class);
		crit.add(Restrictions.eq("username", username));
		crit.add(Restrictions.eq("password", password));
//		crit.add(Restrictions.eq("password", Password.encrypt(password)));
		List<Partner> result = crit.list();
		
		if (result.size() > 0) {
			return result.get(0);
		}
		
		return null;
	}

	@Override
	public Partner partnerWithUsername(String username) {
		Criteria crit = getSession().createCriteria(Partner.class);
		crit.add(Restrictions.eq("username", username));
		List<Partner> result = crit.list();
		
		if (result.size() > 0) {
			return result.get(0);
		}
		
		return null;
	}
	
	

}
