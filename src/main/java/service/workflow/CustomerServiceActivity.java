package service.workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain_layer.book_review.BookReviewDomain;
import domain_layer.book_review.BookReviewDomainImpl;
import domain_layer.customer.CustomerDomain;
import domain_layer.customer.CustomerDomainImpl;
import entity.book_review.BookReview;
import entity.customer.Customer;
import entity.customer.CustomerInfo;
import exception.AlreadyExistedException;
import exception.NotExistException;
import exception.UnAuthorizedException;
import representation.BookReviewRepresentation;
import representation.CustomerInfoRepresentation;
import representation.CustomerInfoRequest;
import representation.CustomerRepresentation;
import representation.Link;
import service.util.RepresentationConverter;

public class CustomerServiceActivity {
	
	private static CustomerDomain customerDomain = new CustomerDomainImpl();
	private static BookReviewDomain bookReviewDomain = new BookReviewDomainImpl();
	
	public CustomerRepresentation register(String username, String password) throws AlreadyExistedException {
		
		String customerID = customerDomain.register(username, password);
		
		return RepresentationConverter.toCustomerRepresentation(customerID, username, this.linkForCustomer(customerID));
	}
	
	public CustomerInfoRepresentation addCustomerInfo(String customerID, String name, String address) throws NotExistException, AlreadyExistedException {
		String customerInfoID = customerDomain.addCustomerInfo(customerID, name, address);
		
		return RepresentationConverter.toCustomerInfoRepresentation(customerID, name, address);
	}
	
	public CustomerRepresentation login(String username, String password) throws NotExistException {
		Customer customer = customerDomain.login(username, password);
		
		return RepresentationConverter.toCustomerRepresentation(customer.getCustomerID(), username, this.linkForCustomer(customer.getCustomerID()));
	}
	
	public CustomerInfoRepresentation getCustomerInfo(String customerID) throws NotExistException {
		CustomerInfo customerInfo = customerDomain.getCustomerInfo(customerID);
		
		return RepresentationConverter.toCustomerInfoRepresentation(customerInfo.getCustomerID(), customerInfo.getName(), customerInfo.getAddress());
	}
	
	public CustomerInfoRepresentation updateCustomerInfo(CustomerInfoRequest request) throws NotExistException, UnAuthorizedException {
		CustomerInfo info = customerDomain.updateCustomerInfo(request.getCustomerID(), request.getName(), request.getAddress());
		
		return RepresentationConverter.toCustomerInfoRepresentation(info.getCustomerID(), info.getName(), info.getAddress());
	}
	
	public void deleteCustomer(String id) throws NotExistException {
		customerDomain.deleteCustomer(id);
	}
	
	private List<Link> linkForCustomer(String customerID) {
		List<Link> links = new ArrayList<>();
		
		Link review = new Link();
		review.setRel("reviews");
		review.setUrl("http://localhost:8080/bookstore/v1/books/" + customerID + "/reviews");
		review.setMediaType("application/json");
		
		Link order = new Link();
		order.setRel("orders");
		order.setUrl("http://localhost:8080/bookstore/v1/customers/" + customerID + "/orders");
		order.setMediaType("application/json");
		
		Link addReview = new Link();
		addReview.setRel("add_review");
		addReview.setUrl("http://localhost:8080/bookstore/v1/reviews/");
		addReview.setMediaType("application/json");
		
		Link delete = new Link();
		delete.setRel("delete");
		delete.setUrl("http://localhost:8080/bookstore/v1/customers/" + customerID);
		delete.setMediaType("application/json");
		
		Link addCustomerInfo = new Link();
		addCustomerInfo.setRel("add_customer_info");
		addCustomerInfo.setUrl("http://localhost:8080/bookstore/v1/customers/" + customerID);
		addCustomerInfo.setMediaType("application/json");
		
		Link updateCustomerInfo = new Link();
		updateCustomerInfo.setRel("update_customer_info");
		updateCustomerInfo.setUrl("http://localhost:8080/bookstore/v1/customers/" + customerID);
		updateCustomerInfo.setMediaType("application/json");
		
		Link self = new Link();
		self.setRel("self");
		self.setUrl("http://localhost:8080/bookstore/v1/customers/" + customerID);
		self.setMediaType("application/json");
		
		links.add(review);
		links.add(order);
		links.add(addReview);
		links.add(delete);
		links.add(updateCustomerInfo);
		links.add(addCustomerInfo);
		links.add(self);
		
		return links;
	}
}
