package de.proskor.gravatar;

import static javax.xml.bind.DatatypeConverter.printHexBinary;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class GravatarUtils {

	public static final String BASE_URL = "//www.gravatar.com/avatar/";

	private GravatarUtils() {
		//
	}

	public static String getHash(String email) {
		try {
			return printHexBinary(MessageDigest.getInstance("MD5").digest(email.getBytes("UTF-8")));
		}
		catch (NoSuchAlgorithmException e) {
			// should not happen
		}
		catch (UnsupportedEncodingException e) {
			// should not happen
		}
		return null;
	}

}