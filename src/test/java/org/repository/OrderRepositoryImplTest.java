package org.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.AbstractHibernateTest;
import org.junit.Test;

import dal.order.OrderRepository;
import dal.order.OrderRepositoryImpl;
import entity.order.Order1;
import util.EntityUtil;

public class OrderRepositoryImplTest extends AbstractHibernateTest {
	private OrderRepository orderRepository;
	
	@Override
	public void setup() {
		super.setup();
		orderRepository = new OrderRepositoryImpl();
	}
	
	@Override
	public void tearDown() {
		super.tearDown();
	}
	
	@Test
	public void getOrder() {
		flushAndClearSession();
		
		Order1 orderFromDb = orderRepository.get("O123");
		assertTrue(orderFromDb.getId().equalsIgnoreCase("O123"));
	}
	
	@Test
	public void getOrder_WhenNotExist() {
		flushAndClearSession();
		
		Order1 orderFromDb = orderRepository.get("O123567");
		assertTrue(orderFromDb == null);
	}
	
	@Test
	public void getAll() {
		assertTrue(orderRepository.getAll().size() == 2);
	}
	
	@Test
	public void create() {
		flushAndClearSession();
		
		String newID = orderRepository.create(EntityUtil.orderSample());
		assertTrue(newID != null);
		assertTrue(!newID.equalsIgnoreCase(""));
	}
	
	
	@Test
	public void update() {
		Order1 info = orderRepository.get("O123");
		
		info.setShippingAddressID("S456");
		orderRepository.update(info);
		
		flushAndClearSession();
		
		info = orderRepository.get("O123");
		assertTrue(info.getShippingAddressID().equalsIgnoreCase("S456"));
	}
	
	@Test
	public void delete() {
		orderRepository.delete(orderRepository.get("O123"));
		
		flushAndClearSession();
		
		assertTrue(orderRepository.getAll().size() == 1);
	}
	
	@Test
	public void deleteAll() {
		orderRepository.deleteAll();
		
		flushAndClearSession();
		
		assertTrue(orderRepository.getAll().size() == 0);
	}
	
	@Test
	public void orderByCustomerID() {
		List<Order1> orders = orderRepository.ordersByCustomerID("C123");
		
		assertTrue(orders != null);
		assertTrue(orders.size() == 1);
	}
	
//	@Test 
//	public void orderByPartnerID() {
//		List<Order1> orders = orderRepository.ordersByPartnerID("P1234");
//		
//		assertTrue(orders != null);
//		assertTrue(orders.size() == 2);
//	}

}