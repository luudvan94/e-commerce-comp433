package entity.customer;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import entity.book_review.BookReview;
import entity.order.Order1;
import entity.payment.Payment;

@Entity
@Table(name = "CUSTOMER_INFO")
public class CustomerInfo {
	
	@Id
	@Column(name = "customerInfoID", unique = true, nullable = false)
	private String customerInfoID;
	
	@Column(name = "customerID")
	private String customerID;
	
	@Column(name = "name")
	private String name;
	
	@Column(name="address")
	private String address;
	
	@Column(name="date_added")
	private String date_added;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="customerInfo")
	private List<BookReview> reviews;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="customerInfo")
	private List<Order1> orders;
	
	public List<Order1> getOrders() {
		return orders;
	}

	public void setOrders(List<Order1> orders) {
		this.orders = orders;
	}

	public CustomerInfo() {}
	
	public static String generateID() {
		return "CI" + new Date().getTime();
	}


	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDate_added() {
		return date_added;
	}

	public void setDate_added(String date_added) {
		this.date_added = date_added;
	}

	public List<BookReview> getReviews() {
		return reviews;
	}

	public void setReviews(List<BookReview> reviews) {
		this.reviews = reviews;
	}

	public String getCustomerInfoID() {
		return customerInfoID;
	}

	public void setCustomerInfoID(String customerInfoID) {
		this.customerInfoID = customerInfoID;
	}

	
}
