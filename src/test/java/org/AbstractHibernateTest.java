package org;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import persistance.HibernateUtil;
import persistance.SessionHolder;

public abstract class AbstractHibernateTest {
	
	protected static SessionFactory sessionFactory;
	protected static Session session;
	
	@BeforeClass
	public static void classSetup() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	@Before
	public void setup() {
		beginSessionTransactionAndSaveToHolder();
	}
	
	@After
	public void tearDown() {
		sessionCommitAndClose();
	}
	
	protected void flushAndClearSession() {
		session.flush();
		session.clear();
	}
	
	private void beginSessionTransactionAndSaveToHolder() {
		session = sessionFactory.openSession();
		session.beginTransaction();
		SessionHolder.set(session);
	}
	
	private void sessionCommitAndClose() {
		session.getTransaction().rollback();
		session.close();
		SessionHolder.set(null);
	}
	
	protected void printStructureTable(String tableName) {
        Session session = SessionHolder.get();
        Query query1 = session.createQuery("show columns from " + tableName);
        List<Object[]> results = query1.list();
        System.out.println("\n======================================");
        System.out.println("Structure table \"" + tableName + "\":");
        System.out.println("--------------------------------------");
        for (Object[] obj : results) {
            System.out.println("field: " + obj[0]);
            System.out.println("\ttype: " + obj[1]);
            System.out.println("\tnullable: " + obj[2]);
            System.out.println("\tkey: " + obj[3]);
            System.out.println("\tdefault: " + obj[4]);
        }
        System.out.println("======================================\n");
    }

    protected void showContentTable(String tableName) {
        Session session = SessionHolder.get();
        Query query1 = session.createQuery("select * from " + tableName);
        List<Object[]> results = query1.list();
        System.out.println("\n======================================");
        System.out.println("Content table \"" + tableName + "\":");
        System.out.println("--------------------------------------");
        int cnt = 0;
        for (Object[] obj : results) {
            cnt++;
            System.out.println("Row: " + cnt);
            for (Object objItem: obj) {
                System.out.println("\t" + objItem);
            }
        }
        System.out.println("======================================\n");
    }

}
