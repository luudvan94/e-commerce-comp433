package util;

import java.util.Date;

import domain.book.Book;
import domain.book_review.BookReview;
import domain.customer.Customer;
import domain.customer.CustomerInfo;
import domain.order.Order1;
import domain.order.Order_Book;
import domain.partner.Partner;
import domain.partner.PartnerInfo;
import domain.payment.Payment;
import domain.shipping_address.ShippingAddress;

public class EntityUtil {
	
	public static Book bookSample() {
		Book book = new Book();
		book.setId(ID.generateID("B"));
		book.setTitle("Data Smart: Using Data Science to Transform Information into Insight");
		book.setAuthor("John W. Foreman");
		book.setDescription("Data Science gets thrown around in the press like it's magic. Major retailers are predicting everything from when their customers are pregnant to when they want a new pair of Chuck Taylors. It's a brave new world where seemingly meaningless data can be transformed into valuable insight to drive smart business decisions.");
		book.setPrice(22.95);
		book.setPartnerID("P1234");
		
		return book;
	}
	
	public static Partner partnerSample() {
		Partner partner = new Partner();
		partner.setId(ID.generateID("B"));
		partner.setUsername("username");
		partner.setPassword(Password.encrypt("password"));
		
		return partner;
	}
	
	public static PartnerInfo partnerInfoSample() {
		PartnerInfo info = new PartnerInfo();
		info.setId(ID.generateID("PI"));
		info.setName("partner info");
		info.setAddress("123 Sherindan Av");
		info.setDate_added("" + new Date().getTime());
		info.setPartnerID("P1234");
		
		return info;
	}
	
	public static BookReview bookReviewSample() {
		BookReview review = new BookReview();
		review.setId(ID.generateID("BR"));
		review.setContent("this is awesome!!");
		review.setDateCreated("" + new Date().getTime());
		review.setBookID("B123");
		review.setCustomerInfo(EntityUtil.customerInfoSample());
		return review;
	}
	
	public static Customer customerSample() {
		Customer customer = new Customer();
		customer.setId(ID.generateID("C"));
		customer.setUsername("username");
		customer.setPassword(Password.encrypt("password"));
		
		return customer;
	}
	
	public static CustomerInfo customerInfoSample() {
		CustomerInfo info = new CustomerInfo();
		info.setCustomerInfoID(ID.generateID("CI"));
		info.setName("customer info");
		info.setAddress("123 Sherindan Av");
		info.setDate_added("" + new Date().getTime());
		info.setCustomerID("C123");
		
		return info;
	}
	
	public static ShippingAddress shippingAddressSample() {
		ShippingAddress info = new ShippingAddress();
		info.setId(ID.generateID("S"));
		info.setCustomerID("C123");
		info.setStreet("1234 ABC");
		info.setCity("XYZ");
		info.setZipcode("12345");
		
		return info;
	}
	
	public static Payment cardProfileSample() {
		Payment info = new Payment();
		info.setId(ID.generateID("CP"));
		info.setOrderID("O123");
		info.setDate_added("12334234");
		info.setExpires("06/22");
		info.setCardNumber("1234");
		info.setAmount(500);
		
		return info;
	}
	
	public static Order1 orderSample() {
		Order1 order = new Order1();
		order.setId(ID.generateID("O"));
		order.setCustomerID("C456");
		order.setPartnerID("P4567");
		order.setDate_updated("1233445545");
		order.setShippingAddressID("S123");
		order.setStatus("pending");
		order.setTotal(100.25);
		
		return order;
	}
	
	public static Order_Book order_bookSample() {
		Order_Book order_book = new Order_Book();
		order_book.setId(ID.generateID("OB"));
		order_book.setBookID("B456");
		order_book.setOrderID("O456");
		order_book.setQty(3);
		order_book.setTotal(15.0);
		
		return order_book;
	}

}
