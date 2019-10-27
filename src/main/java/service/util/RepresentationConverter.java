package service.util;

import java.util.List;

import entity.book.Book;
import entity.book_review.BookReview;
import entity.customer.CustomerInfo;
import entity.order.Order1;
import entity.order.Order_Book;
import entity.partner.PartnerInfo;
import entity.payment.Payment;
import representation.BookRepresentation;
import representation.BookReviewRepresentation;
import representation.CustomerInfoRepresentation;
import representation.CustomerRepresentation;
import representation.ObjectFactory;
import representation.OrderBookRepresentation;
import representation.OrderRepresentation;
import representation.OrderStatus;
import representation.PartnerInfoRepresentation;
import representation.PartnerRepresentation;
import representation.PaymentRepresentation;

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
	
	public static PaymentRepresentation toPaymentRepresentation(String id, String cardNumber, String expires, double amount, String dateAdded) {
		PaymentRepresentation representation = factory.createPaymentRepresentation();
		representation.setPaymentID(id);
		representation.setCardNumber(cardNumber);
		representation.setExpires(expires);
		representation.setAmount(amount);
		representation.setDateAdded(dateAdded);
		
		return representation;
	}
	
	public static OrderBookRepresentation toOrderBookRepresentaiton(Book book, int quantity, double total, String status) {
		OrderBookRepresentation representation = factory.createOrderBookRepresentation();
		representation.setBookRepresentation(RepresentationConverter.toBookRepresentation(book.getBookID(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getPrice(), book.getQuantity(), book.getPartnerInfo()));
		representation.setQuantity(quantity);
		representation.setTotal(total);
		representation.setStatus(status);
		
		return representation;
	}
	
	public static OrderRepresentation toOrderRepresentation(Order1 order) {
		OrderRepresentation representation = factory.createOrderRepresentation();
		representation.setOrderId(order.getOrderID());		
		
		CustomerInfo customerInfo = order.getCustomerInfo();
		representation.setCustomerInfoRepresentation(RepresentationConverter.toCustomerInfoRepresentation(customerInfo.getCustomerID(), customerInfo.getName(), customerInfo.getAddress()));
		
		representation.setDateUpdated(order.getDate_updated());
		representation.setShippingAddress(order.getShippingAddress());
		representation.setStatus(order.getStatus());
		representation.setTotal(order.getTotal());
		
		Payment payment = order.getPayment();
		representation.setPaymentRepresentation(RepresentationConverter.toPaymentRepresentation(payment.getPaymentID(), payment.getCardNumber(), payment.getExpires(), payment.getAmount(), payment.getDate_added()));
		
		List<Order_Book> orderBooks = order.getProducts();
		for(Order_Book orderBook: orderBooks) {
			representation.getOrderBookRepresentation().add(RepresentationConverter.toOrderBookRepresentaiton(orderBook.getBook(), orderBook.getQty(), orderBook.getTotal(), null));
		}
		
		return representation;
	}
	
	public static OrderStatus toOrderStatus(Order1 order) {
		OrderStatus representation = factory.createOrderStatus();
		representation.setOrderID(order.getOrderID());
		representation.setStatus(order.getStatus());
		
		return representation;
	}

}
