package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CARD_PROFILE")
public class CardProfile {
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	
	@Column(name="date_added")
	private String date_added;
	
	@Column(name = "customerID")
	private String customerID;
	
	@Column(name="four_digit")
	private String four_digit;
	
	@Column(name="expires")
	private String expires;
	
	public CardProfile() {}
	
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

	public String getFour_digit() {
		return four_digit;
	}

	public void setFour_digit(String four_digit) {
		this.four_digit = four_digit;
	}


	public String getExpires() {
		return expires;
	}

	public void setExpires(String expires) {
		this.expires = expires;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
}
