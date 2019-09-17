package repository;

import org.hibernate.Session;

import persistance.SessionHolder;

public abstract class AbstractRepository<T, ID> implements Repository<T, ID> {
	
	@Override
	public void flush() {
		getSession().flush();
	}

	protected Session getSession() {
		return SessionHolder.get();
	}
}
