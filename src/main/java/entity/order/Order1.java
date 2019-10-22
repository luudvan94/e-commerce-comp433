package entity.order;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import entity.book_review.BookReview;
import entity.customer.Customer;
import entity.customer.CustomerInfo;
import entity.partner.Partner;
import entity.payment.Payment;
import entity.shipping_address.ShippingAddress;

@Entity
@Table(name = "ORDER1")
public class Order1 {
	
	@Id
	@Column(name = "orderID", unique = true, nullable = false)
	private String orderID;
	
	@ManyToOne
    @JoinColumn(name="customerInfoID", nullable=true)
    private CustomerInfo customerInfo;
	
//	@Column(name="partnerID")
//	private String partnerID;
	
	@Column(name="status")
	private String status;
	
	@Column(name="date_updated")
	private String date_updated;
	
	@Column(name="total")
	private double total;
	
	@Column(name="shippingAddress", nullable=true)
	private String shippingAddress;
	
//	@Transient
//	private ShippingAddress address;
//	
//	@Transient
//	private Payment profile;
//	
//	@Transient
//	private Customer customerInfo;
//	
//	@Transient
//	private Partner partnerInfo;
//	
//	@Transient
//	private List<Order_Book> order_book;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="order")
	private List<Order_Book> products;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="paymentID")
	private Payment payment;
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public List<Order_Book> getProducts() {
		return products;
	}

	public void setProducts(List<Order_Book> products) {
		this.products = products;
	}

	public Order1() {}


	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}


	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate_updated() {
		return date_updated;
	}

	public void setDate_updated(String date_updated) {
		this.date_updated = date_updated;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}


}
