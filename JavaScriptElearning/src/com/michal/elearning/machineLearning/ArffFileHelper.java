package com.michal.elearning.machineLearning;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.michal.elearning.dao.UserInputForm;
import com.michal.elearning.wekaDataModel.Features;

import com.michal.elearning.wekaDataModel.DataModelWithForm;

public class ArffFileHelper {
	
	public static Map<String,byte[]> prepareArffInputStream(List<DataModelWithForm> dataToFile) throws Exception {
		List<String> emotionStates = InspectClassFields(UserInputForm.class);
		List<String> features = InspectClassFields(Features.class);
		
		Map<String, byte[]> map = new HashMap<String, byte[]>();
		
		for(String emotion : emotionStates){
			StringBuilder arffBuilder = new StringBuilder();
			arffBuilder.append("@relation "+emotion);
			arffBuilder.append("\n");
			arffBuilder.append("\n");
			for(String feature : features){
				arffBuilder.append("@attribute "+feature+" NUMERIC \n");
			}
			arffBuilder.append("@attribute "+emotion+" {Yes,No} \n");
			arffBuilder.append("\n");
			arffBuilder.append("@data \n");
			for(DataModelWithForm data : dataToFile){
				for(String feature : features){
					String function = "get"+feature.substring(0, 1).toUpperCase() + feature.substring(1);
					Method method = data.getFeatures().getClass().getMethod(function, new Class[] {});
					Object obj = method.invoke(data.getFeatures(),new Object[] {});
					arffBuilder.append(obj+",");
				}
				String state = "get"+emotion.substring(0, 1).toUpperCase() + emotion.substring(1);
				Method method = data.getUserForm().getClass().getMethod(state, new Class[] {});
				Object obj2 = method.invoke(data.getUserForm(),new Object[] {});
				arffBuilder.append(obj2+"\n");
			}	
			map.put(emotion, arffBuilder.toString().getBytes());
		}	
		return map;
	}
	
	private static <T> List<String> InspectClassFields(Class<T> klazz) {
		List<String> emotionStates = new ArrayList<String>();
		Field[] fields = klazz.getDeclaredFields();
		System.out.printf("%d fields:%n", fields.length);
		for (Field field : fields) {
			emotionStates.add(field.getName());
			System.out.printf("%s %s %s%n", Modifier.toString(field.getModifiers()), field.getType().getSimpleName(),field.getName());
		}
		return emotionStates;
	}	
}
