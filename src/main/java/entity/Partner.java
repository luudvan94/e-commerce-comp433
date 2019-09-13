package entity;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;  

import javax.persistence.*;

@Entity
@Table(name = "PARTNER")
public class Partner {
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	public void Partner() {}
	
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
		this.password = encrypt(password);
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	//source: https://www.baeldung.com/sha-256-hashing-java
	
	public static String encrypt(String password) {
		try {
			return byteToHex(encryptToByte(password));
		} catch (Exception e) {
			return password;
		}
	}
	
	private static byte[] encryptToByte(String password) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		return encodedhash;
	}
	
	private static String byteToHex(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
	
	
}
