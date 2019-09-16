package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;
import dao.BookDAO;
import entity.Partner;
import util.HibernateUtil;

public class PartnerDAOTest {
	
	@Test
	public void userNameShouldExisted() {
		String testUsername = "partner1";
		
		assertEquals("Username should be existed", true, PartnerDAO.isUserNameAlreadyExist(testUsername));
	}
	
	@Test
	public void userNameShouldNotExist() {
		String testUsername = "partner2";
		
		assertEquals("Username already existed", false, PartnerDAO.isUserNameAlreadyExist(testUsername));
	}
	
	@Test
	public void shouldRegisterSuccessfully() {
		String id = PartnerDAO.register("partner2", "password");
		
		assertTrue(id != "");
	}
	
	@Test
	public void shouldRegisterUnsuccessfully() {
		String id = PartnerDAO.register("partner1", "password");
		
		assertTrue( id == null);
	}
	
	@Test 
	public void loginSucessfully() {
		shouldRegisterSuccessfully();
		
		Partner partner = PartnerDAO.login("partner2", "password");
		assertTrue(partner != null);
		assertTrue(partner.getId() != null);
		assertEquals("partner2", partner.getUsername());
	}
	
	@Test
	public void loginUnsucessfully() {
		shouldRegisterSuccessfully();
		
		Partner partner = PartnerDAO.login("partner3", "password");
		assertTrue(partner == null);
	}
	
	@Test 
	public void shouldReturnTrueForPartnerExist() {
		assertTrue(PartnerDAO.isPartnerExist("P1234"));
	}
	
	@Test public void shouldReturnFalseForPartnerNotExist() {
		assertFalse(PartnerDAO.isPartnerExist("P12345"));
	}

	@AfterClass
	public static void shutDownSession() {
		HibernateUtil.shutdown();
	}
}
