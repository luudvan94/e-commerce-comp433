package util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {
	
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
