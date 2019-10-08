package org.repository;

import static org.junit.Assert.assertTrue;

import org.AbstractHibernateTest;
import org.junit.Test;

import entity.ShippingAddress;
import repository.ShippingAddressRepository;
import repository.impl.ShippingAddressRepositoryImpl;
import util.EntityUtil;

public class ShippingAddressRepositoryImplTest extends AbstractHibernateTest {
	private ShippingAddressRepository shippingAddressRepository;
	
	@Override
	public void setup() {
		super.setup();
		shippingAddressRepository = new ShippingAddressRepositoryImpl();
	}
	
	@Override
	public void tearDown() {
		super.tearDown();
	}
	
	@Test
	public void getShippingAddress() {
		flushAndClearSession();
		
		ShippingAddress shippingAddressFromDb = shippingAddressRepository.get("S123");
		assertTrue(shippingAddressFromDb.getId().equalsIgnoreCase("S123"));
	}
	
	@Test
	public void getShippingAddress_WhenNotExist() {
		flushAndClearSession();
		
		ShippingAddress shippingAddressFromDb = shippingAddressRepository.get("S123567");
		assertTrue(shippingAddressFromDb == null);
	}
	
	@Test
	public void getAll() {
		assertTrue(shippingAddressRepository.getAll().size() == 2);
	}
	
	@Test
	public void create() {
		flushAndClearSession();
		
		String newID = shippingAddressRepository.create(EntityUtil.shippingAddressSample());
		assertTrue(newID != null);
		assertTrue(!newID.equalsIgnoreCase(""));
	}
	
	
	@Test
	public void update() {
		ShippingAddress info = shippingAddressRepository.get("S123");
		
		info.setStreet("123 Lorel");
		shippingAddressRepository.update(info);
		
		flushAndClearSession();
		
		info = shippingAddressRepository.get("S123");
		assertTrue(info.getStreet().equalsIgnoreCase("123 Lorel"));
	}
	
	@Test
	public void delete() {
		shippingAddressRepository.delete(shippingAddressRepository.get("S123"));
		
		flushAndClearSession();
		
		assertTrue(shippingAddressRepository.getAll().size() == 1);
	}
	
	@Test
	public void deleteAll() {
		shippingAddressRepository.deleteAll();
		
		flushAndClearSession();
		
		assertTrue(shippingAddressRepository.getAll().size() == 0);
	}
	
	@Test
	public void shippingAddressByCustomerID() {
		assertTrue(shippingAddressRepository.shippingAddressByCustomerID("C123") != null);
	}

}