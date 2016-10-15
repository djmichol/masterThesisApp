package com.michal.elearning.utils;

import com.google.gson.Gson;

public class JsonUtils {
	
	public static String getParsedJsonFromObject(Object objectToParse){
		Gson gson = new Gson();
		return gson.toJson(objectToParse);
	}
}
