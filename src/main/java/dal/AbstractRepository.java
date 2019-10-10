package dal;

import org.hibernate.Session;

import dal.persistence.HibernateUtil;
import dal.persistence.SessionHolder;

public abstract class AbstractRepository<T, ID> implements Repository<T, ID> {
	
	@Override
	public void flush() {
		getSession().flush();
	}

	protected Session getSession() {
		Session session = SessionHolder.get();
		if (session == null) {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			SessionHolder.set(session);
		}
		
		return session;
	}
}
