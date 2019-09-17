package util;

import java.util.Date;

public class ID {
	public static String generateID(String prefix) {
		return prefix + new Date().getTime();
	}
}
