package org.repository;

import static org.junit.Assert.assertTrue;

import org.AbstractHibernateTest;
import org.junit.Test;

import dal.partner.PartnerInfoRepository;
import dal.partner.PartnerInfoRepositoryImpl;
import entity.partner.PartnerInfo;
import util.EntityUtil;

public class PartnerInfoRepositoryImplTest extends AbstractHibernateTest {
	private PartnerInfoRepository partnerInfoRepository;
	
	@Override
	public void setup() {
		super.setup();
		partnerInfoRepository = new PartnerInfoRepositoryImpl();
	}
	
	@Override
	public void tearDown() {
		super.tearDown();
	}
	
	@Test
	public void getPartnerInfo() {
		flushAndClearSession();
		
		PartnerInfo partnerFromDb = partnerInfoRepository.get("PI1234");
		assertTrue(partnerFromDb.getPartnerInfoID().equalsIgnoreCase("PI1234"));
	}
	
	@Test
	public void getPartnerInfo_WhenNotExist() {
		flushAndClearSession();
		
		PartnerInfo partnerFromDb = partnerInfoRepository.get("PI123567");
		assertTrue(partnerFromDb == null);
	}
	
	@Test
	public void getAll() {
		assertTrue(partnerInfoRepository.getAll().size() == 2);
	}
	
	@Test
	public void create() {
		flushAndClearSession();
		
		String newID = partnerInfoRepository.create(EntityUtil.partnerInfoSample());
		assertTrue(newID != null);
		assertTrue(!newID.equalsIgnoreCase(""));
	}
	
	
	@Test
	public void update() {
		PartnerInfo info = partnerInfoRepository.get("PI1234");
		
		info.setName("Loyola Uni");
		partnerInfoRepository.update(info);
		
		flushAndClearSession();
		
		info = partnerInfoRepository.get("PI1234");
		assertTrue(info.getName().equalsIgnoreCase("Loyola Uni"));
	}
	
	@Test
	public void delete() {
		partnerInfoRepository.delete(partnerInfoRepository.get("PI1234"));
		
		flushAndClearSession();
		
		assertTrue(partnerInfoRepository.getAll().size() == 1);
	}
	
//	@Test
//	public void deleteAll() {
//		partnerInfoRepository.deleteAll();
//		
//		flushAndClearSession();
//		
//		assertTrue(partnerInfoRepository.getAll().size() == 0);
//	}
	
	@Test
	public void partnerInfoByPartnerID() {
		assertTrue(partnerInfoRepository.partnerInfobyPartnerID("P1234") != null);
	}

}
