package domain_layer.customer;

import java.util.List;

import dal.customer.CustomerInfoRepository;
import dal.customer.CustomerInfoRepositoryImpl;
import dal.customer.CustomerRepository;
import dal.customer.CustomerRepositoryImpl;
import dal.shipping_address.ShippingAddressRepository;
import dal.shipping_address.ShippingAddressRepositoryImpl;
import entity.customer.Customer;
import entity.customer.CustomerInfo;
import entity.shipping_address.ShippingAddress;
import exception.NotExistException;
import util.ID;

public class CustomerDomainImpl implements CustomerDomain {
	
	private CustomerRepository customerRepository = new CustomerRepositoryImpl();
	private CustomerInfoRepository customerInfoRepository = new CustomerInfoRepositoryImpl();
	private ShippingAddressRepository shippingAddressRepository = new ShippingAddressRepositoryImpl();

	@Override
	public CustomerInfo login(String username, String password) throws NotExistException {
		Customer customer = customerRepository.customerByUsernamePassword(username, password);
		
		if (customer == null) {
			throw new NotExistException("Customer with user name and password does not exist");
		}
		
		CustomerInfo customerInfo = customerInfoRepository.customerInfoByCustomerID(customer.getId());
		
		if (customerInfo == null) {
			throw new NotExistException("Customer info with customer id does not exist");
		}
		
		return customerInfo;
	}

	@Override
	public String register(String username, String password) {
		Customer customer = new Customer();
		customer.setId(ID.generateID("C"));
		customer.setUsername(username);
		customer.setPassword(password);
		
		return customerRepository.create(customer);
	}

	@Override
	public void updatePassword(String id, String newPassword) throws NotExistException {
		Customer customer = customerRepository.get(id);
		
		customer.setPassword(newPassword);
		
		customerRepository.update(customer);
		
	}

	@Override
	public void deleteCustomer(String id) throws NotExistException {
		Customer customer = this.getCustomer(id);
		
		customerRepository.delete(customer);
	}

	@Override
	public Customer getCustomer(String id) throws NotExistException {
		Customer customer = customerRepository.get(id);
		
		if (customer == null) {
			throw new NotExistException("Customer with user name and password does not exist");
		}
		
		return customer;
	}

	@Override
	public ShippingAddress getShippingAddressByCustomer(String id) {
		return shippingAddressRepository.shippingAddressByCustomerID(id);
	}

}
