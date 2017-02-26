package com.michal.elearning.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class VectorsUtils {

	public static <T> List<int[]> getDigraphsList(Class<T> klazz) {
		Field[] fields = klazz.getDeclaredFields();
		List<int[]> list = new ArrayList<int[]>();
		for (Field field : fields) {
			try {
					list.add((int[]) field.get(null));
			} catch (IllegalAccessException e) {
				// DO NOTHING
			}
		}
		return list;
	}

	
}
