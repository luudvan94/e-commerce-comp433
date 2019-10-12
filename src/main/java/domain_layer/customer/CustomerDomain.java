package domain_layer.customer;

import java.util.List;

import entity.customer.Customer;
import entity.customer.CustomerInfo;
import entity.shipping_address.ShippingAddress;
import exception.NotExistException;

public interface CustomerDomain {
	
	Customer getCustomer(String id) throws NotExistException;
	
	CustomerInfo login(String username, String password) throws NotExistException;
	
	String register(String username, String password);
	
	void updatePassword(String id, String newPassword) throws NotExistException;
	
	void deleteCustomer(String id) throws NotExistException;
	
	ShippingAddress getShippingAddressByCustomer(String id);
	

}
