package org.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.AbstractHibernateTest;
import org.junit.Test;

import dal.order.Order_BookRepository;
import dal.order.Order_BookRepositoryImpl;
import domain.order.Order_Book;
import util.EntityUtil;

public class Order_BookRepositoryImplTest extends AbstractHibernateTest {
	private Order_BookRepository orderRepository;
	
	@Override
	public void setup() {
		super.setup();
		orderRepository = new Order_BookRepositoryImpl();
	}
	
	@Override
	public void tearDown() {
		super.tearDown();
	}
	
	@Test
	public void getOrder_Book() {
		flushAndClearSession();
		
		Order_Book orderFromDb = orderRepository.get("OB123");
		assertTrue(orderFromDb.getId().equalsIgnoreCase("OB123"));
	}
	
	@Test
	public void getOrder_Book_WhenNotExist() {
		flushAndClearSession();
		
		Order_Book orderFromDb = orderRepository.get("OB123567");
		assertTrue(orderFromDb == null);
	}
	
	@Test
	public void getAll() {
		assertTrue(orderRepository.getAll().size() == 2);
	}
	
	@Test
	public void create() {
		flushAndClearSession();
		
		String newID = orderRepository.create(EntityUtil.order_bookSample());
		assertTrue(newID != null);
		assertTrue(!newID.equalsIgnoreCase(""));
	}
	
	
	@Test
	public void update() {
		Order_Book info = orderRepository.get("OB123");
		
		info.setQty(5);
		info.setTotal(75.0);
		orderRepository.update(info);
		
		flushAndClearSession();
		
		info = orderRepository.get("OB123");
		assertTrue(info.getTotal() == 75.0);
	}
	
	@Test
	public void delete() {
		orderRepository.delete(orderRepository.get("OB123"));
		
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
		List<Order_Book> orders = orderRepository.byOrderID("O123");
		
		assertTrue(orders != null);
		assertTrue(orders.size() == 2);
	}
	
	@Test 
	public void orderByPartnerID() {
		List<Order_Book> orders = orderRepository.byBookID("B123");
		
		assertTrue(orders != null);
		assertTrue(orders.size() == 1);
	}

}
