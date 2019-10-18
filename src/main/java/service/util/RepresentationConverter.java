package service.util;

import entity.book.Book;
import entity.book_review.BookReview;
import entity.customer.CustomerInfo;
import entity.partner.PartnerInfo;
import representation.BookRepresentation;
import representation.BookReviewRepresentation;
import representation.CustomerInfoRepresentation;
import representation.CustomerRepresentation;
import representation.ObjectFactory;
import representation.PartnerInfoRepresentation;
import representation.PartnerRepresentation;

public class RepresentationConverter {
	
	private static ObjectFactory factory = new ObjectFactory();
	
	public static BookRepresentation toBookRepresentation(String bookID, String title, String author, String description, double price, int quantity, PartnerInfo partnerInfo) {
		BookRepresentation representation = factory.createBookRepresentation();
		representation.setBookId(bookID);
		representation.setAuthor(author);
		representation.setTitle(title);
		representation.setDescription(description);
		representation.setPrice(price);
		representation.setQuantity(quantity);
		
		representation.setPartnerInfoRepresentation(RepresentationConverter.toPartnerInfoRepresentation(partnerInfo.getPartnerID(), partnerInfo.getName(), partnerInfo.getAddress()));
		
		return representation;
	}
	
	public static BookReviewRepresentation toBookReviewRepresentation(String id, Book book, CustomerInfo customerInfo, String content) {
		BookReviewRepresentation representation = factory.createBookReviewRepresentation();
		representation.setBookReviewId(id);
		representation.setContent(content);
		representation.setCustomerInfoRepresentation(RepresentationConverter.toCustomerInfoRepresentation(customerInfo.getCustomerID(), customerInfo.getName(), customerInfo.getAddress()));
		representation.setBookRepresentation(RepresentationConverter.toBookRepresentation(book.getBookID(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getPrice(), book.getQuantity(), book.getPartnerInfo()));
		
		return representation;
	}
	
	public static CustomerInfoRepresentation toCustomerInfoRepresentation(String customerID, String name, String address) {
		CustomerInfoRepresentation representation = factory.createCustomerInfoRepresentation();
		representation.setCustomerId(customerID);
		representation.setAddress(address);
		representation.setName(name);
		
		return representation;
	}
	
	public static PartnerInfoRepresentation toPartnerInfoRepresentation(String partnerID, String name, String address) {
		PartnerInfoRepresentation representation = factory.createPartnerInfoRepresentation();
		representation.setPartnerId(partnerID);
		representation.setName(name);
		representation.setAddress(address);
		
		return representation;
	}
	
	public static CustomerRepresentation toCustomerRepresentation(String id, String username) {
		CustomerRepresentation representation = factory.createCustomerRepresentation();
		representation.setCustomerID(id);
		representation.setUsername(username);
		
		return representation;
	}
	
	public static PartnerRepresentation toPartnerRepresentation(String id, String username) {
		PartnerRepresentation representation = factory.createPartnerRepresentation();
		representation.setPartnerID(id);
		representation.setUsername(username);
		
		return representation; 
	}

}
