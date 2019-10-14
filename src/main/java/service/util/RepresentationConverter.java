package service.util;

import entity.book.Book;
import entity.book_review.BookReview;
import entity.customer.CustomerInfo;
import entity.partner.PartnerInfo;
import representation.BookRepresentation;
import representation.BookReviewRepresentation;
import representation.CustomerInfoRepresentation;
import representation.ObjectFactory;
import representation.PartnerInfoRepresentation;
import representation.PartnerRepresentation;

public class RepresentationConverter {
	
	private static ObjectFactory factory = new ObjectFactory();
	
	public static BookRepresentation toBookRepresentation(Book book) {
		BookRepresentation representation = factory.createBookRepresentation();
		representation.setBookId(book.getId());
		representation.setAuthor(book.getAuthor());
		representation.setTitle(book.getTitle());
		representation.setDescription(book.getDescription());
		representation.setPrice(book.getPrice());
		representation.setQuantity(book.getQuantity());
		
		PartnerInfo info = book.getPartnerInfo();
		representation.setPartnerInfoRepresentation(RepresentationConverter.toPartnerInfoRepresentation(info.getPartnerID(), info.getName(), info.getAddress()));
		
		return representation;
	}
	
	public static BookReviewRepresentation toBookReviewRepresentation(BookReview bookReview) {
		BookReviewRepresentation representation = factory.createBookReviewRepresentation();
		representation.setBookReviewId(bookReview.getId());
		representation.setContent(bookReview.getContent());
		representation.setCustomerInfoRepresentation(RepresentationConverter.toCustomerInfoRepresentation(bookReview.getCustomerInfo()));
		representation.setDateCreated(bookReview.getDateCreated());
		
		return representation;
	}
	
	private static CustomerInfoRepresentation toCustomerInfoRepresentation(CustomerInfo customerInfo) {
		CustomerInfoRepresentation representation = factory.createCustomerInfoRepresentation();
		representation.setCustomerId(customerInfo.getCustomerID());
		representation.setAddress(customerInfo.getAddress());
		representation.setName(customerInfo.getName());
		
		return representation;
	}
	
	public static PartnerInfoRepresentation toPartnerInfoRepresentation(String partnerID, String name, String address) {
		PartnerInfoRepresentation representation = factory.createPartnerInfoRepresentation();
		representation.setPartnerId(partnerID);
		representation.setName(name);
		representation.setAddress(address);
		
		return representation;
	}
	
	public static PartnerRepresentation toPartnerRepresentation(String id, String username) {
		PartnerRepresentation representation = factory.createPartnerRepresentation();
		representation.setPartnerID(id);
		representation.setUsername(username);
		
		return representation; 
	}

}
