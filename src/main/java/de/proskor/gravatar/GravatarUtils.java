package de.proskor.gravatar;

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
			return hex(MessageDigest.getInstance("MD5").digest(email.getBytes("UTF-8")));
		}
		catch (NoSuchAlgorithmException e) {
			// should not happen
		}
		catch (UnsupportedEncodingException e) {
			// should not happen
		}
		return null;
	}

	private static String hex(byte[] array) {
		StringBuilder hexBuilder = new StringBuilder();

		for (byte element : array) {
			hexBuilder.append(Integer.toHexString((element & 0xFF) | 0x100).substring(1, 3));
		}

		return hexBuilder.toString();
	}

}