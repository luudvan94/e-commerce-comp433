package entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Transient
	private CustomerInfo info;
	
	@Transient
	private List<CardProfile> profiles;
	
	@Transient
	private List<ShippingAddress> shippingAddress;
	
	public Customer() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CustomerInfo getInfo() {
		return info;
	}

	public void setInfo(CustomerInfo info) {
		this.info = info;
	}

	public List<CardProfile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<CardProfile> profiles) {
		this.profiles = profiles;
	}

	public List<ShippingAddress> getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(List<ShippingAddress> shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
}
