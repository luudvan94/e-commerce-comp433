package domain.partner;

import java.util.Date;  

import javax.persistence.*;

import util.Password;

@Entity
@Table(name = "PARTNER")
public class Partner {
	
	@Id
	@Column(name = "partnerID", unique = true, nullable = false)
	private String id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Transient
	private PartnerInfo info;
	
	public Partner() {}
	
	public static String generateID() {
		return "P" + new Date().getTime();
	}

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

	public void encryptAndSetPassword(String password) {
		this.password = Password.encrypt(password);
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public PartnerInfo getInfo() {
		return info;
	}

	public void setInfo(PartnerInfo info) {
		this.info = info;
	}
	
	
}
