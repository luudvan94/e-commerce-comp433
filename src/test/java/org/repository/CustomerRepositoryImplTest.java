package org.repository;

import static org.junit.Assert.assertTrue;

import org.AbstractHibernateTest;
import org.junit.Test;

import dal.customer.CustomerRepository;
import dal.customer.CustomerRepositoryImpl;
import entity.customer.Customer;
import util.EntityUtil;
import util.Password;

public class CustomerRepositoryImplTest extends AbstractHibernateTest {
	private CustomerRepository customerRepository;
	
	@Override
	public void setup() {
		super.setup();
		customerRepository = new CustomerRepositoryImpl();
	}
	
	@Override
	public void tearDown() {
		super.tearDown();
	}
	
	@Test
	public void getCustomer() {
		flushAndClearSession();
		
		Customer customerFromDb = customerRepository.get("C123");
		assertTrue(customerFromDb.getId().equalsIgnoreCase("C123"));
	}
	
	@Test
	public void getCustomer_WhenNotExist() {
		flushAndClearSession();
		
		Customer customerFromDb = customerRepository.get("C123567");
		assertTrue(customerFromDb == null);
	}
	
	@Test
	public void getAll() {
		assertTrue(customerRepository.getAll().size() == 2);
	}
	
	@Test
	public void create() {
		flushAndClearSession();
		
		String newID = customerRepository.create(EntityUtil.customerSample());
		assertTrue(newID != null);
		assertTrue(!newID.equalsIgnoreCase(""));
	}
	
	@Test
	public void create_ExistedUsername() {
		flushAndClearSession();
		Customer customer = EntityUtil.customerSample();
		customer.setUsername("customer1");
		String newID = customerRepository.create(customer);
		assertTrue(newID == null);
	}
	
	@Test
	public void update() {
		Customer customer = customerRepository.get("C123");
		
		customer.setPassword("newPassword");
		customerRepository.update(customer);
		
		flushAndClearSession();
		
		customer = customerRepository.get("C123");
		assertTrue(customer.getPassword().equalsIgnoreCase(Password.encrypt("newPassword")));
	}
	
	@Test
	public void delete() {
		customerRepository.delete(customerRepository.get("C123"));
		
		flushAndClearSession();
		
		assertTrue(customerRepository.getAll().size() == 1);
	}
	
	@Test
	public void deleteAll() {
		customerRepository.deleteAll();
		
		flushAndClearSession();
		
		assertTrue(customerRepository.getAll().size() == 0);
	}
	
	@Test
	public void login() {
		create();
		
		flushAndClearSession();
		
		Customer customer = customerRepository.customerByUsernamePassword("username", "password");
		
		assertTrue(customer != null);
		assertTrue(customer.getUsername().equalsIgnoreCase("username"));
	}
	
	@Test
	public void login_WrongUsername() {
		create();
		
		flushAndClearSession();
		
		Customer partner = customerRepository.customerByUsernamePassword("wrongusername", "password");
		
		assertTrue(partner == null);
	}
	
	@Test
	public void login_WrongPassword() {
		create();
		
		flushAndClearSession();
		
		Customer partner = customerRepository.customerByUsernamePassword("username", "wrongpassword");
		
		assertTrue(partner == null);
	}
	
	@Test
	public void login_WrongUsername_WrongPassword() {
		create();
		
		flushAndClearSession();
		
		Customer partner = customerRepository.customerByUsernamePassword("wrongusername", "wrongpassword");
		
		assertTrue(partner == null);
	}
}
