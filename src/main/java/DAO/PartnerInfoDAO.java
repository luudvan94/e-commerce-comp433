package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entity.PartnerInfo;
import util.HibernateUtil;
import java.util.Date;

public class PartnerInfoDAO {
	
	public static String addNewPartnerInfo(String partnerID, String name, String address) {
		
		if (!PartnerDAO.isPartnerExist(partnerID)) {
			return null;
		}
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		PartnerInfo info = new PartnerInfo();
		info.setId(PartnerInfo.generateID());
		info.setName(name);
		info.setAddress(address);
		info.setDate_added("" + new Date().getTime());
		info.setPartnerID(partnerID);
		
		String id = (String) session.save(info);
		session.getTransaction().commit();
		
		return id;
	}
	
	public static PartnerInfo partnerInfoByPartner(String partnerID) {
		
		if (!PartnerDAO.isPartnerExist(partnerID)) {
			return null;
		}
		
		Session session= HibernateUtil.getSessionFactory().openSession();
		Criteria crit = session.createCriteria(PartnerInfo.class);
		crit.add(Restrictions.eq("partnerID", partnerID));
		List<PartnerInfo> result = crit.list();
		
		if (result.size() > 0) {
			return result.get(0);
		}
		
		return null;
	}

}
