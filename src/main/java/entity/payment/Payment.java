package entity.payment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PAYMENT")
public class Payment {
	
	@Id
	@Column(name = "paymentID", unique = true, nullable = false)
	private String id;
	
	@Column(name="date_added")
	private String date_added;
	
	@Column(name = "orderID")
	private String orderID;
	
	@Column(name="card_number")
	private String cardNumber;
	
	@Column(name="expires")
	private String expires;
	
	@Column(name="amount")
	private double amount;
	
	public Payment() {}
	
	public static String generateID() {
		return "CP" + new Date().getTime();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate_added() {
		return date_added;
	}

	public void setDate_added(String date_added) {
		this.date_added = date_added;
	}

	


	public String getExpires() {
		return expires;
	}

	public void setExpires(String expires) {
		this.expires = expires;
	}


	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	
}
