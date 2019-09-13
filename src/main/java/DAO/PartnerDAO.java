package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entity.Book;
import entity.Partner;
import util.HibernateUtil;

public class PartnerDAO {
	
	public static boolean isUserNameAlreadyExist(String username) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria crit = session.createCriteria(Partner.class);
		crit.add(Restrictions.eq("username", username));
		List<Partner> result = crit.list();
		
		if (result.size() == 0) {
			return false;
		}
		
		return true;
		
	}
	
	public static String register(String username, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (PartnerDAO.isUserNameAlreadyExist(username)) {
			return null;
		}
		
		session.beginTransaction();
		Partner partner = new Partner();
		partner.setId(Partner.generateID());
		partner.setUsername(username);
		partner.setPassword(Partner.encrypt(password));
		
		String id = (String) session.save(partner);
		session.getTransaction().commit();
		
		return id;
	}
	
	public static Partner login(String username, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (!PartnerDAO.isUserNameAlreadyExist(username)) {
			return null;
		}
		
		Criteria crit = session.createCriteria(Partner.class);
		crit.add(Restrictions.eq("username", username));
		crit.add(Restrictions.eq("password", Partner.encrypt(password)));
		List<Partner> result = crit.list();
		
		if (result.size() > 0) {
			return result.get(0);
		}
		
		return null;
	}

}
