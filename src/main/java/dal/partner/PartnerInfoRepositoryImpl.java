package dal.partner;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import dal.AbstractRepository;
import entity.book.Book;
import entity.partner.Partner;
import entity.partner.PartnerInfo;

public class PartnerInfoRepositoryImpl extends AbstractRepository<PartnerInfo, String> implements PartnerInfoRepository {

	@Override
	public void delete(PartnerInfo t) {
		PartnerInfo persistancePartnerinfo = get(t.getPartnerInfoID());
		getSession().delete(persistancePartnerinfo);	
	}

	@Override
	public void update(PartnerInfo t) {
		getSession().merge(t);
		
	}

	@Override
	public String create(PartnerInfo t) {
		return (String) getSession().save(t);
	}

	@Override
	public PartnerInfo get(String id) {
		return (PartnerInfo) getSession().get(PartnerInfo.class, id);
	}

	@Override
	public List<PartnerInfo> getAll() {
		Query query = getSession().createQuery("FROM PartnerInfo");
		return query.list();
	}

	@Override
	public void deleteAll() {
		Query query = getSession().createQuery("DELETE FROM PartnerInfo");
		query.executeUpdate();
		
	}

	@Override
	public PartnerInfo partnerInfobyPartnerID(String id) {
		Query query = getSession().createQuery("FROM PartnerInfo WHERE partnerID = :partnerID");
		query.setParameter("partnerID", id);
		List<PartnerInfo> result = query.list();
		
		if (result.size() > 0) {
			return result.get(0);
		}
		
		return null;
	}

	
}
