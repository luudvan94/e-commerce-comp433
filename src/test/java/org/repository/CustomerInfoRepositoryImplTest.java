package org.repository;

import static org.junit.Assert.assertTrue;

import org.AbstractHibernateTest;
import org.junit.Test;

import dal.customer.CustomerInfoRepository;
import dal.customer.CustomerInfoRepositoryImpl;
import domain.customer.CustomerInfo;
import util.EntityUtil;

public class CustomerInfoRepositoryImplTest extends AbstractHibernateTest {
	private CustomerInfoRepository customerInfoRepository;
	
	@Override
	public void setup() {
		super.setup();
		customerInfoRepository = new CustomerInfoRepositoryImpl();
	}
	
	@Override
	public void tearDown() {
		super.tearDown();
	}
	
	@Test
	public void getCustomerInfo() {
		flushAndClearSession();
		
		CustomerInfo customerInfoFromDb = customerInfoRepository.get("CI123");
		assertTrue(customerInfoFromDb.getCustomerInfoID().equalsIgnoreCase("CI123"));
	}
	
	@Test
	public void getCustomerInfo_WhenNotExist() {
		flushAndClearSession();
		
		CustomerInfo customerInfoFromDb = customerInfoRepository.get("CI123567");
		assertTrue(customerInfoFromDb == null);
	}
	
	@Test
	public void getAll() {
		assertTrue(customerInfoRepository.getAll().size() == 2);
	}
	
	@Test
	public void create() {
		flushAndClearSession();
		
		String newID = customerInfoRepository.create(EntityUtil.customerInfoSample());
		assertTrue(newID != null);
		assertTrue(!newID.equalsIgnoreCase(""));
	}
	
	
	@Test
	public void update() {
		CustomerInfo info = customerInfoRepository.get("CI123");
		
		info.setName("Loyola Uni Info");
		customerInfoRepository.update(info);
		
		flushAndClearSession();
		
		info = customerInfoRepository.get("CI123");
		assertTrue(info.getName().equalsIgnoreCase("Loyola Uni Info"));
	}
	
	@Test
	public void delete() {
		customerInfoRepository.delete(customerInfoRepository.get("CI123"));
		
		flushAndClearSession();
		
		assertTrue(customerInfoRepository.getAll().size() == 1);
	}
	
//	@Test
//	public void deleteAll() {
//		customerInfoRepository.deleteAll();
//		
//		flushAndClearSession();
//		
//		assertTrue(customerInfoRepository.getAll().size() == 0);
//	}
	
	@Test
	public void customerInfoByCustomerID() {
		assertTrue(customerInfoRepository.customerInfoByCustomerID("C123") != null);
	}

}