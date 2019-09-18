package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER1")
public class Order1 {
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	
	@Column(name="customerID")
	private String customerID;
	
	@Column(name="partnerID")
	private String partnerID;
	
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

	public String getPartnerID() {
		return partnerID;
	}

	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
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
	
	
	
	
}
