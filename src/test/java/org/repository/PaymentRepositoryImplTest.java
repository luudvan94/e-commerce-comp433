package org.repository;

import static org.junit.Assert.assertTrue;

import org.AbstractHibernateTest;
import org.junit.Test;

import entity.Payment;
import repository.PaymentRepository;
import repository.impl.PaymentRepositoryImpl;
import util.EntityUtil;

public class PaymentRepositoryImplTest extends AbstractHibernateTest {
	private PaymentRepository cardProfileRepository;
	
	@Override
	public void setup() {
		super.setup();
		cardProfileRepository = new PaymentRepositoryImpl();
	}
	
	@Override
	public void tearDown() {
		super.tearDown();
	}
	
	@Test
	public void getCardProfile() {
		flushAndClearSession();
		
		Payment cardProfileFromDb = cardProfileRepository.get("CP123");
		assertTrue(cardProfileFromDb.getId().equalsIgnoreCase("CP123"));
	}
	
	@Test
	public void getCardProfile_WhenNotExist() {
		flushAndClearSession();
		
		Payment cardProfileFromDb = cardProfileRepository.get("CP123567");
		assertTrue(cardProfileFromDb == null);
	}
	
	@Test
	public void getAll() {
		assertTrue(cardProfileRepository.getAll().size() == 2);
	}
	
	@Test
	public void create() {
		flushAndClearSession();
		
		String newID = cardProfileRepository.create(EntityUtil.cardProfileSample());
		assertTrue(newID != null);
		assertTrue(!newID.equalsIgnoreCase(""));
	}
	
	
	@Test
	public void update() {
		Payment info = cardProfileRepository.get("CP123");
		
		info.setCardNumber("4567");
		cardProfileRepository.update(info);
		
		flushAndClearSession();
		
		info = cardProfileRepository.get("CP123");
		assertTrue(info.getCardNumber().equalsIgnoreCase("4567"));
	}
	
	@Test
	public void delete() {
		cardProfileRepository.delete(cardProfileRepository.get("CP123"));
		
		flushAndClearSession();
		
		assertTrue(cardProfileRepository.getAll().size() == 1);
	}
	
	@Test
	public void deleteAll() {
		cardProfileRepository.deleteAll();
		
		flushAndClearSession();
		
		assertTrue(cardProfileRepository.getAll().size() == 0);
	}
	
	@Test
	public void cardProfileByCustomerID() {
		assertTrue(cardProfileRepository.cardProfileByOrderID("O123") != null);
	}

}
