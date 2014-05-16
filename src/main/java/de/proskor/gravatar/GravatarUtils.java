package de.proskor.gravatar;

import static de.proskor.gravatar.Gravatar.BASE_URL;
import static de.proskor.gravatar.Gravatar.DEFAULT_IMAGE;
import static de.proskor.gravatar.Gravatar.DEFAULT_SIZE;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class GravatarUtils {

	private GravatarUtils() {
		//
	}

	public static String getURL(Gravatar gravatar) {
		return getURL(gravatar.getEmail(), gravatar.getSize(), gravatar.getDefault());
	}

	public static String getURL(String email) {
		return getURL(email, DEFAULT_SIZE, DEFAULT_IMAGE);
	}

	public static String getURL(String email, int size) {
		return getURL(email, size, DEFAULT_IMAGE);
	}

	public static String getURL(String email, int size, String defaultImage) {
		String url = BASE_URL + getHash(email) + "?d=" + encodeURL(defaultImage);

		if (size != DEFAULT_SIZE) {
			url += "&s=" + size;
		}

		return url;
	}

	public static String getHash(String email) {
		try {
			return hex(MessageDigest.getInstance("MD5").digest(email.getBytes("UTF-8")));
		}
		catch (NoSuchAlgorithmException e) {
			throw new UnsupportedOperationException(e);
		}
		catch (UnsupportedEncodingException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	private static String encodeURL(String string) {
		try {
			return URLEncoder.encode(string, "UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	private static String hex(byte[] array) {
		StringBuilder hexBuilder = new StringBuilder();

		for (byte element : array) {
			hexBuilder.append(Integer.toHexString((element & 0xFF) | 0x100).substring(1, 3));
		}

		return hexBuilder.toString();
	}

}