package domain_layer.customer;

import java.util.Date;
import java.util.List;

import dal.customer.CustomerInfoRepository;
import dal.customer.CustomerInfoRepositoryImpl;
import dal.customer.CustomerRepository;
import dal.customer.CustomerRepositoryImpl;
import dal.shipping_address.ShippingAddressRepository;
import dal.shipping_address.ShippingAddressRepositoryImpl;
import domain_layer.book_review.BookReviewDomain;
import domain_layer.book_review.BookReviewDomainImpl;
import entity.book.Book;
import entity.book_review.BookReview;
import entity.customer.Customer;
import entity.customer.CustomerInfo;
import entity.partner.Partner;
import entity.partner.PartnerInfo;
import entity.shipping_address.ShippingAddress;
import exception.AlreadyExistedException;
import exception.NotExistException;
import exception.UnAuthorizedException;
import util.ID;
import util.Password;

public class CustomerDomainImpl implements CustomerDomain {
	
	private CustomerRepository customerRepository = new CustomerRepositoryImpl();
	private CustomerInfoRepository customerInfoRepository = new CustomerInfoRepositoryImpl();
	private ShippingAddressRepository shippingAddressRepository = new ShippingAddressRepositoryImpl();

	@Override
	public Customer login(String username, String password) throws NotExistException {
		Customer customer = customerRepository.customerByUsernamePassword(username, password);
		
		if (customer == null) {
			throw new NotExistException("Customer with user name and password does not exist");
		}
		
		return customer;
	}

	@Override
	public String register(String username, String password) throws AlreadyExistedException {
		Customer customer = new Customer();
		customer.setCustomerID(ID.generateID("C"));
		customer.setUsername(username);
		customer.setPassword(Password.encrypt(password));
		
		String newID = customerRepository.create(customer);
		
		if (newID == null) {
			throw new AlreadyExistedException("Customer with provided user name already existed");
		}
		
		return newID;
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
			throw new NotExistException("Customer with provided id does not exist");
		}
		
		return customer;
	}

	@Override
	public ShippingAddress getShippingAddressByCustomer(String id) {
		return shippingAddressRepository.shippingAddressByCustomerID(id);
	}

	@Override
	public String addCustomerInfo(String customerID, String name, String address)
			throws NotExistException, AlreadyExistedException {
		System.out.println(customerID);
		Customer customer = this.getCustomer(customerID);
		
		CustomerInfo customerInfo = customerInfoRepository.customerInfoByCustomerID(customerID);
		
		if (customerInfo != null) {
			throw new AlreadyExistedException("Customer with provided id already had customer information");
		}
		
		CustomerInfo newInfo = new CustomerInfo();
		newInfo.setCustomerInfoID(ID.generateID("CI"));
		newInfo.setCustomerID(customer.getCustomerID());
		newInfo.setName(name);
		newInfo.setAddress(address);
		newInfo.setDate_added(Long.toString(new Date().getTime()));
		
		String newID = customerInfoRepository.create(newInfo);
		
		return newID;
	}

	@Override
	public CustomerInfo getCustomerInfo(String customerID) throws NotExistException {
		CustomerInfo customerInfo = customerInfoRepository.customerInfoByCustomerID(customerID);
		
		if (customerInfo == null) {
			throw new NotExistException("Customer info with customer id does not exist");
		}
		
		return customerInfo;
	}

	@Override
	public void deleteReview(String customerID, String bookReviewID) throws NotExistException, UnAuthorizedException {
		BookReviewDomain bookReviewDomain = new BookReviewDomainImpl();
		
		BookReview review = bookReviewDomain.getBookReview(bookReviewID);
		
		if (!review.getCustomerInfo().getCustomerID().equalsIgnoreCase(customerID)) {
			throw new UnAuthorizedException("This book review can not be deleted by customer with provided ID");
		}
		
		bookReviewDomain.deleteReview(review.getId());	
		
	}

	@Override
	public CustomerInfo updateCustomerInfo(String customerID, String name, String address) throws NotExistException, UnAuthorizedException {
		CustomerInfo info = this.getCustomerInfo(customerID);
		
		info.setName(name);
		info.setAddress(address);
		
		customerInfoRepository.update(info);
		
		return info;
	}

}
