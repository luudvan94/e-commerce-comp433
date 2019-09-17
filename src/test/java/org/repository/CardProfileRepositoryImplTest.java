package org.repository;

import static org.junit.Assert.assertTrue;

import org.AbstractHibernateTest;
import org.junit.Test;

import entity.CardProfile;
import repository.CardProfileRepository;
import repository.impl.CardProfileRepositoryImpl;
import util.EntityUtil;

public class CardProfileRepositoryImplTest extends AbstractHibernateTest {
	private CardProfileRepository cardProfileRepository;
	
	@Override
	public void setup() {
		super.setup();
		cardProfileRepository = new CardProfileRepositoryImpl();
	}
	
	@Override
	public void tearDown() {
		super.tearDown();
	}
	
	@Test
	public void getCardProfile() {
		flushAndClearSession();
		
		CardProfile cardProfileFromDb = cardProfileRepository.get("CP123");
		assertTrue(cardProfileFromDb.getId().equalsIgnoreCase("CP123"));
	}
	
	@Test
	public void getCardProfile_WhenNotExist() {
		flushAndClearSession();
		
		CardProfile cardProfileFromDb = cardProfileRepository.get("CP123567");
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
		CardProfile info = cardProfileRepository.get("CP123");
		
		info.setAmount(50.0);
		cardProfileRepository.update(info);
		
		flushAndClearSession();
		
		info = cardProfileRepository.get("CP123");
		assertTrue(info.getAmount() == 50.0);
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
		assertTrue(cardProfileRepository.cardProfileByCustomerID("C123") != null);
	}

}
