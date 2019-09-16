package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;
import dao.BookDAO;
import entity.Partner;
import entity.PartnerInfo;
import util.HibernateUtil;

public class PartnerInfoDAOTest {
	
	@Test
	public void shouldNotCreateNonExistedPartnerSuccess() {
		assertTrue(PartnerInfoDAO.addNewPartnerInfo("P123456", "abc", "abcd") == null);
	}
	
	@Test
	public void shouldSuccessfullyCreatePartnerInfo() {
		assertTrue(PartnerInfoDAO.addNewPartnerInfo("P1234", "Loyola2", "123 Sherindan Rd") != null);
	}
	
	@Test
	public void shouldReturnInfoForExistedPartner() {
		assertTrue(PartnerInfoDAO.partnerInfoByPartner("P1234") != null);
	}
	
	@Test
	public void shouldNotReturnInfoForNonExistedPartner() {
		assertTrue(PartnerInfoDAO.partnerInfoByPartner("P12345") == null);
	}
	
	@Test
	public void shouldReturnCorrectNameOfInfo() {
		PartnerInfo info = PartnerInfoDAO.partnerInfoByPartner("P1234");
		
		assertTrue(info.getName().equalsIgnoreCase("Loyola University"));
	}
	
	

	@AfterClass
	public static void shutDownSession() {
		HibernateUtil.shutdown();
	}
}
