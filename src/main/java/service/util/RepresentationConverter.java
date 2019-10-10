package service.util;

import domain.book.Book;
import domain.book_review.BookReview;
import domain.customer.CustomerInfo;
import representation.BookRepresentation;
import representation.BookReviewRepresentation;
import representation.CustomerInfoRepresentation;
import representation.ObjectFactory;

public class RepresentationConverter {
	
	private static ObjectFactory factory = new ObjectFactory();
	
	public static BookRepresentation toBookRepresentation(Book book) {
		BookRepresentation representation = factory.createBookRepresentation();
		representation.setBookId(book.getId());
		representation.setAuthor(book.getAuthor());
		representation.setTitle(book.getTitle());
		representation.setDescription(book.getDescription());
		representation.setPrice(book.getPrice());
		
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

}
