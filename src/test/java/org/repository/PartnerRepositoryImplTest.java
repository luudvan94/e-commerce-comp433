package org.repository;

import static org.junit.Assert.assertTrue;

import org.AbstractHibernateTest;
import org.junit.Test;

import dal.partner.PartnerRepository;
import dal.partner.PartnerRepositoryImpl;
import entity.partner.Partner;
import util.EntityUtil;
import util.Password;

public class PartnerRepositoryImplTest extends AbstractHibernateTest {

	private PartnerRepository partnerRepository;
	
	@Override
	public void setup() {
		super.setup();
		partnerRepository = new PartnerRepositoryImpl();
	}
	
	@Override
	public void tearDown() {
		super.tearDown();
	}
	
	@Test
	public void getpartner() {
		flushAndClearSession();
		
		Partner partnerFromDb = partnerRepository.get("P1234");
		assertTrue(partnerFromDb.getId().equalsIgnoreCase("P1234"));
	}
	
	@Test
	public void getpartner_WhenNotExist() {
		flushAndClearSession();
		
		Partner partnerFromDb = partnerRepository.get("P123567");
		assertTrue(partnerFromDb == null);
	}
	
	@Test
	public void getAll() {
		assertTrue(partnerRepository.getAll().size() == 2);
	}
	
	@Test
	public void create() {
		flushAndClearSession();
		
		String newID = partnerRepository.create(EntityUtil.partnerSample());
		assertTrue(newID != null);
		assertTrue(!newID.equalsIgnoreCase(""));
	}
	
	@Test
	public void create_ExistedUsername() {
		flushAndClearSession();
		Partner partner = EntityUtil.partnerSample();
		partner.setUsername("partner1");
		String newID = partnerRepository.create(partner);
		assertTrue(newID == null);
	}
	
	@Test
	public void update() {
		Partner partner = partnerRepository.get("P1234");
		
		partner.setPassword("newPassword");
		partnerRepository.update(partner);
		
		flushAndClearSession();
		
		partner = partnerRepository.get("P1234");
		assertTrue(partner.getPassword().equalsIgnoreCase(Password.encrypt("newPassword")));
	}
	
	@Test
	public void delete() {
		partnerRepository.delete(partnerRepository.get("P1234"));
		
		flushAndClearSession();
		
		assertTrue(partnerRepository.getAll().size() == 1);
	}
	
	@Test
	public void deleteAll() {
		partnerRepository.deleteAll();
		
		flushAndClearSession();
		
		assertTrue(partnerRepository.getAll().size() == 0);
	}
	
	@Test
	public void login() {
		create();
		
		flushAndClearSession();
		
		Partner partner = partnerRepository.partnerWithUsernamePassword("username", "password");
		
		assertTrue(partner != null);
		assertTrue(partner.getUsername().equalsIgnoreCase("username"));
	}
	
	@Test
	public void login_WrongUsername() {
		create();
		
		flushAndClearSession();
		
		Partner partner = partnerRepository.partnerWithUsernamePassword("wrongusername", "password");
		
		assertTrue(partner == null);
	}
	
	@Test
	public void login_WrongPassword() {
		create();
		
		flushAndClearSession();
		
		Partner partner = partnerRepository.partnerWithUsernamePassword("username", "wrongpassword");
		
		assertTrue(partner == null);
	}
	
	@Test
	public void login_WrongUsername_WrongPassword() {
		create();
		
		flushAndClearSession();
		
		Partner partner = partnerRepository.partnerWithUsernamePassword("wrongusername", "wrongpassword");
		
		assertTrue(partner == null);
	}
}
