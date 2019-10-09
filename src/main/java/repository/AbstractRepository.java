package repository;

import org.hibernate.Session;

import persistance.HibernateUtil;
import persistance.SessionHolder;

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
