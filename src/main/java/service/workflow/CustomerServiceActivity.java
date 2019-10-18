package service.workflow;

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
import service.util.RepresentationConverter;

public class CustomerServiceActivity {
	
	private static CustomerDomain customerDomain = new CustomerDomainImpl();
	private static BookReviewDomain bookReviewDomain = new BookReviewDomainImpl();
	
	public CustomerRepresentation register(String username, String password) throws AlreadyExistedException {
		
		String customerID = customerDomain.register(username, password);
		
		return RepresentationConverter.toCustomerRepresentation(customerID, username);
	}
	
	public CustomerInfoRepresentation addCustomerInfo(String customerID, String name, String address) throws NotExistException, AlreadyExistedException {
		String customerInfoID = customerDomain.addCustomerInfo(customerID, name, address);
		
		return RepresentationConverter.toCustomerInfoRepresentation(customerID, name, address);
	}
	
	public CustomerRepresentation login(String username, String password) throws NotExistException {
		Customer customer = customerDomain.login(username, password);
		
		return RepresentationConverter.toCustomerRepresentation(customer.getCustomerID(), username);
	}
	
	public CustomerInfoRepresentation getCustomerInfo(String customerID) throws NotExistException {
		CustomerInfo customerInfo = customerDomain.getCustomerInfo(customerID);
		
		return RepresentationConverter.toCustomerInfoRepresentation(customerInfo.getCustomerID(), customerInfo.getName(), customerInfo.getAddress());
	}
	
	public List<BookReviewRepresentation> getBookReviews(String customerID) throws NotExistException {
		CustomerInfo customerInfo = customerDomain.getCustomerInfo(customerID);
		
		List<BookReview> reviews = bookReviewDomain.getReviewsByCustomerInfoId(customerInfo.getCustomerInfoID());
		
		return reviews.stream().map(bookReview -> RepresentationConverter.toBookReviewRepresentation(bookReview.getId(), bookReview.getBook(), bookReview.getCustomerInfo(), bookReview.getContent())).collect(Collectors.toList());
		
	}
	
	public void deleteReview(String customerID, String reviewID) throws NotExistException, UnAuthorizedException {
		customerDomain.deleteReview(customerID, reviewID);
	}
	
	public CustomerInfoRepresentation updateCustomerInfo(CustomerInfoRequest request) throws NotExistException, UnAuthorizedException {
		CustomerInfo info = customerDomain.updateCustomerInfo(request.getCustomerID(), request.getName(), request.getAddress());
		
		return RepresentationConverter.toCustomerInfoRepresentation(info.getCustomerID(), info.getName(), info.getAddress());
	}
}
