package entity.order;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import entity.customer.Customer;
import entity.partner.Partner;
import entity.payment.Payment;
import entity.shipping_address.ShippingAddress;

@Entity
@Table(name = "ORDER1")
public class Order1 {
	
	@Id
	@Column(name = "orderID", unique = true, nullable = false)
	private String id;
	
	@Column(name="customerID")
	private String customerID;
	
//	@Column(name="partnerID")
//	private String partnerID;
	
	@Column(name="status")
	private String status;
	
	@Column(name="date_updated")
	private String date_updated;
	
	@Column(name="total")
	private double total;
	
	@Column(name="cardProfileID")
	private String cardProfileID;
	
	@Column(name="shippingAddressID")
	private String shippingAddressID;
	
	@Transient
	private ShippingAddress address;
	
	@Transient
	private Payment profile;
	
	@Transient
	private Customer customerInfo;
	
	@Transient
	private Partner partnerInfo;
	
	@Transient
	private List<Order_Book> order_book;
	
	public Order1() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

//	public String getPartnerID() {
//		return partnerID;
//	}
//
//	public void setPartnerID(String partnerID) {
//		this.partnerID = partnerID;
//	}

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

	public String getCardProfileID() {
		return cardProfileID;
	}

	public void setCardProfileID(String cardProfileID) {
		this.cardProfileID = cardProfileID;
	}

	public String getShippingAddressID() {
		return shippingAddressID;
	}

	public void setShippingAddressID(String shippingAddressID) {
		this.shippingAddressID = shippingAddressID;
	}

	public ShippingAddress getAddress() {
		return address;
	}

	public void setAddress(ShippingAddress address) {
		this.address = address;
	}

	public Payment getProfile() {
		return profile;
	}

	public void setProfile(Payment profile) {
		this.profile = profile;
	}

	public Customer getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(Customer customerInfo) {
		this.customerInfo = customerInfo;
	}

	public Partner getPartnerInfo() {
		return partnerInfo;
	}

	public void setPartnerInfo(Partner partnerInfo) {
		this.partnerInfo = partnerInfo;
	}

	public List<Order_Book> getOrder_book() {
		return order_book;
	}

	public void setOrder_book(List<Order_Book> order_book) {
		this.order_book = order_book;
	}
	
	
	
	
}
