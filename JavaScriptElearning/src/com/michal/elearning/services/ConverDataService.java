package com.michal.elearning.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.michal.elearning.dao.*;
import com.michal.elearning.daoServices.ILessonsInterface;
import com.michal.elearning.daoServices.IUserInputData;
import com.michal.elearning.daoServices.IUserInterface;
import com.michal.elearning.daoServices.LessonDaoService;
import com.michal.elearning.daoServices.UserDaoService;
import com.michal.elearning.daoServices.UserInputDataDaoService;
import com.michal.elearning.wekaDataModel.Features;
import com.michal.elearning.wekaDataModel.MauseDataModel;
import com.mysql.fabric.xmlrpc.base.Array;

@Path("/convertData")
public class ConverDataService {
	
	private IUserInputData dataService = new UserInputDataDaoService();
	private ILessonsInterface lessonService = new LessonDaoService();
	private IUserInterface userService = new UserDaoService();
	private final static String path="C:\\DataWeka";
	
	@Context 
	SecurityContext securityContext;
	
	//@RolesAllowed("user")
	@PermitAll
    @GET
    @Path("/getData")
    public Response getUserData() 
    {   
    	int userId = 1;//((User)securityContext.getUserPrincipal()).getId();
    	List<RawData> rawData = getRawData(userId);
    	
    	return Response.ok().build(); 
    }
	
	private List<RawData> getRawData(int userId){
    	List<RawData> rawData = new ArrayList<RawData>();
    	try {
			List<UserInputData> userDataList = dataService.getUserData(userId);	
			User user = userService.getUserByID(userId);
			Gson converter = new Gson();
			if(userDataList!=null){
				for(UserInputData userData : userDataList){
					
					JSONArray mauseClicks = new JSONArray(new String(userData.getMouseClicks(), StandardCharsets.UTF_8));
					JSONArray keystrokes = new JSONArray(new String(userData.getKeyStrokes(), StandardCharsets.UTF_8));
					JSONArray mauseMoves = new JSONArray(new String(userData.getMouseMove(), StandardCharsets.UTF_8));
					JSONObject form = new JSONObject(new String(userData.getForm(), StandardCharsets.UTF_8));
					
					Lesson lesson = lessonService.getLessonById(userData.getLessonId());
					
					List<UserMauseClick> mauseClicksList = new ArrayList<UserMauseClick>();
					List<UserKeystrokes> keystrokeList = new ArrayList<UserKeystrokes>();
					List<UserMauseMove> mauseMoveList = new ArrayList<UserMauseMove>();
					UserInputForm userForm = converter.fromJson(form.toString(), UserInputForm.class);
					
					UserMauseClick mauseClick = null;
					for(int i=0; i<mauseClicks.length();i++){
						mauseClick = converter.fromJson(mauseClicks.getJSONObject(i).toString(), UserMauseClick.class);
						mauseClicksList.add(mauseClick);
					}
					
					UserKeystrokes keystroke = null;
					for(int i=0; i<keystrokes.length();i++){
						keystroke = converter.fromJson(keystrokes.getJSONObject(i).toString(), UserKeystrokes.class);
						keystrokeList.add(keystroke);
					}
					
					UserMauseMove mauseMove = null;
					for(int i=0; i<mauseMoves.length();i++){
						mauseMove = converter.fromJson(mauseMoves.getJSONObject(i).toString(), UserMauseMove.class);
						mauseMoveList.add(mauseMove);
					}	
					
					rawData.add(new RawData(mauseClicksList, keystrokeList, mauseMoveList, userForm, lesson.getType(),user.getMail()));
				}
				computeMauseClicksVectors(rawData,user.getMail());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    	return rawData;
	}
	
	private void computeMauseClicksVectors(List<RawData> rawData,String userName){
		List<MauseDataModel> dataToFile = new ArrayList<MauseDataModel>();
		
		for(RawData data : rawData){
			Collections.sort(data.getMauseClicksList());
			Vector<Integer> dwellTimes = new Vector<Integer>();
			if(data.getMauseClicksList().size()>=2){
				for(int i=1; i<data.getMauseClicksList().size();i++){
					int time1 = data.getMauseClicksList().get(i-1).getTime();
					int time2 = data.getMauseClicksList().get(i).getTime();
					if(time1!=0 && time2!=0){
						dwellTimes.add(time2-time1);
					}
				}
			}
			if(dwellTimes.size()>0){
				MauseDataModel dataToAdd = new MauseDataModel();
				Features features = new Features();
				features.setMauseClicksMean(calculateMean(dwellTimes));
				features.setMauseClickDeviation(calculateStandardDeviation(dwellTimes));
				features.setMauseCliskEventCount(data.getMauseClicksList().size());
				dataToAdd.setFeatures(features);
				dataToAdd.setUserForm(data.getUserForm());
				dataToAdd.setUser(data.getUser());
				dataToFile.add(dataToAdd);
			}
		}
		
		try {
			prepareFile(dataToFile,userName);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	private void prepareFile(List<MauseDataModel> dataToFile,String userName) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<String> emotionStates = InspectClassFields(UserInputForm.class);
		List<String> features = InspectClassFields(Features.class);
		String FILENAME = null;
		FileWriter fw;
		BufferedWriter bw;
		
		for(String emotion : emotionStates){
			FILENAME = path+"\\"+emotion+userName+".arff"; 
			try {
				fw = new FileWriter(FILENAME);
				bw = new BufferedWriter(fw);
				
				bw.append("@relation "+emotion);
				bw.append("\n");
				bw.append("\n");
				for(String feature : features){
					bw.append("@attribute "+feature+" NUMERIC \n");
				}
				bw.append("@attribute "+emotion+" {Neutral,Yes,No} \n");
				bw.append("\n");
				bw.append("@data \n");
				for(MauseDataModel data : dataToFile){
					for(String feature : features){
						String function = "get"+feature.substring(0, 1).toUpperCase() + feature.substring(1);
						Method method = data.getFeatures().getClass().getMethod(function, new Class[] {});
						Object obj = method.invoke(data.getFeatures(),new Object[] {});
						bw.append(obj+",");
					}
					String state = "get"+emotion.substring(0, 1).toUpperCase() + emotion.substring(1);
					Method method = data.getUserForm().getClass().getMethod(state, new Class[] {});
					Object obj2 = method.invoke(data.getUserForm(),new Object[] {});
					bw.append(obj2+"\n");
				}
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}		
	}
	
	static <T> List<String> InspectClassFields(Class<T> klazz) {
		List<String> emotionStates = new ArrayList<String>();
		Field[] fields = klazz.getDeclaredFields();
		System.out.printf("%d fields:%n", fields.length);
		for (Field field : fields) {
			emotionStates.add(field.getName());
			System.out.printf("%s %s %s%n", Modifier.toString(field.getModifiers()), field.getType().getSimpleName(),field.getName());
		}
		return emotionStates;
	}

	private int calculateMean(Vector<Integer> data){
		int suma = 0;
		for(Integer dwell : data){
			suma += dwell;
		}
		return suma/data.size();
	}
	 
	private int calculateStandardDeviation(Vector<Integer> data){
		int mean = calculateMean(data);
		int sum = 0;
		int deviation = 0;
		
		for(Integer dwell : data){
			deviation = (int) Math.pow(dwell - mean,2);
			sum += deviation;
		}
		
		int standardDeviation = sum / data.size();
		return (int) Math.sqrt(standardDeviation);
	}

}
