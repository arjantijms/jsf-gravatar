package de.proskor.gravatar;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GravatarUtils {
	
	public static final String BASE_URL = "http://www.gravatar.com/avatar/";
	private static GravatarUtils instance;
	
	private MessageDigest md5Digest;

	private GravatarUtils() {
		try {
			md5Digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// should not happen
		}
	}

	public static GravatarUtils getInstance() {
		if (instance == null) {
			instance = new GravatarUtils();
		}
		
		return instance;
	}

	public String getHash(String email) {
		try {
			return hex(md5Digest.digest(email.getBytes("ISO-8859-1")));
		} catch (UnsupportedEncodingException e) {
			// should not happen
		}
		return null;
	}

	private String hex(byte[] array) {
		StringBuilder hexBuilder = new StringBuilder();
		
		for (byte element : array) {
			hexBuilder.append(Integer.toHexString((element & 0xFF) | 0x100).substring(1, 3));
		}
		
		return hexBuilder.toString();
	}

}
