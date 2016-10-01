package com.michal.elearning.utils;

import javax.xml.bind.DatatypeConverter;

public class Base64Utils {

	public static String[] decode(String encodeString) {
		// Replacing "Basic THE_BASE_64" to "THE_BASE_64" directly
		encodeString = encodeString.replaceFirst("[B|b]asic ", "");

		// Decode the Base64 into byte[]
		byte[] decodedBytes = DatatypeConverter.parseBase64Binary(encodeString);

		// If the decode fails in any case
		if (decodedBytes == null || decodedBytes.length == 0) {
			return null;
		}

		return new String(decodedBytes).split(":", 2);
	}
	
}
