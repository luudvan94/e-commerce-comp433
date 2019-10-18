package domain_layer.customer;

import java.util.List;

import entity.customer.Customer;
import entity.customer.CustomerInfo;
import entity.partner.PartnerInfo;
import entity.shipping_address.ShippingAddress;
import exception.AlreadyExistedException;
import exception.NotExistException;
import exception.UnAuthorizedException;

public interface CustomerDomain {
	
	Customer getCustomer(String id) throws NotExistException;
	
	Customer login(String username, String password) throws NotExistException;
	
	String register(String username, String password) throws AlreadyExistedException;
	
	void updatePassword(String id, String newPassword) throws NotExistException;
	
	void deleteCustomer(String id) throws NotExistException;
	
	ShippingAddress getShippingAddressByCustomer(String id);
	
	String addCustomerInfo(String customerID, String name, String address) throws NotExistException, AlreadyExistedException;
	
	CustomerInfo getCustomerInfo(String customerID) throws NotExistException;

	void deleteReview(String customerID, String bookReviewID) throws NotExistException, UnAuthorizedException;
	
	CustomerInfo updateCustomerInfo(String customerID, String name, String address) throws NotExistException, UnAuthorizedException;
}
