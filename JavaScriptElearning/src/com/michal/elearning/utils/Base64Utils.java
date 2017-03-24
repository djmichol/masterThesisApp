package com.michal.elearning.utils;

import javax.xml.bind.DatatypeConverter;

public class Base64Utils {

	public static String[] decode(String encodeString) {
		encodeString = encodeString.replaceFirst("[B|b]asic ", "");
		byte[] decodedBytes = DatatypeConverter.parseBase64Binary(encodeString);
		if (decodedBytes == null || decodedBytes.length == 0) {
			return null;
		}
		return new String(decodedBytes).split(":", 2);
	}
	
}
