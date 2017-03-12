package com.michal.elearning.utils;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.michal.elearning.dao.Lesson;
import com.michal.elearning.dao.RawData;
import com.michal.elearning.dao.User;
import com.michal.elearning.dao.UserInputData;
import com.michal.elearning.dao.UserInputForm;
import com.michal.elearning.dao.UserKeystrokes;
import com.michal.elearning.dao.UserMauseClick;
import com.michal.elearning.dao.UserMauseMove;
import com.michal.elearning.daoServices.ILessonsInterface;
import com.michal.elearning.daoServices.LessonDaoService;
import com.michal.elearning.machineLearning.MathHelperUtils;
import com.michal.elearning.modeldata.vectors.DiGraphFeatures;
import com.michal.elearning.modeldata.vectors.MauseClickVector;
import com.michal.elearning.modeldata.vectors.NGraph;
import com.michal.elearning.modeldata.vectors.SingleKeyFeatures;
import com.michal.elearning.wekaDataModel.DataModelWithForm;
import com.michal.elearning.wekaDataModel.Features;

public class ConvertDataUtils {
	
	private static ILessonsInterface lessonService = new LessonDaoService();

	public static List<DataModelWithForm> getArrfFromRawData(List<UserInputData> userDataList, User user){
		List<RawData> rawData = new ArrayList<RawData>();
    	try {			
			Gson converter = new Gson();
			if(userDataList!=null){
				for(UserInputData userData : userDataList){					
					JSONArray mauseClicks = new JSONArray(new String(userData.getMouseClicks(), StandardCharsets.UTF_8));
					JSONArray keystrokes = new JSONArray(new String(userData.getKeyStrokes(), StandardCharsets.UTF_8));
					JSONArray mauseMoves = new JSONArray(new String(userData.getMouseMove(), StandardCharsets.UTF_8));
					JSONObject form = null;
					if(userData.getForm()!=null){
						form = new JSONObject(new String(userData.getForm(), StandardCharsets.UTF_8));
					}
					
					Lesson lesson = lessonService.getLessonById(userData.getLessonId());
					
					List<UserMauseClick> mauseClicksList = new ArrayList<UserMauseClick>();
					List<UserKeystrokes> keystrokeList = new ArrayList<UserKeystrokes>();
					List<UserMauseMove> mauseMoveList = new ArrayList<UserMauseMove>();
					UserInputForm userForm = null;
					if(userData.getForm()!=null){
						userForm = converter.fromJson(form.toString(), UserInputForm.class);
					}
					
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
					if(lesson.getType().equals("editor")){
						rawData.add(new RawData(mauseClicksList, keystrokeList, mauseMoveList, userForm, lesson.getType(),user.getMail()));
					}
				}
				
				List<DataModelWithForm> dataToFile = computeVectors(rawData);		
				return dataToFile;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return null;
	}
	
	private static List<DataModelWithForm> computeVectors(List<RawData> rawData){
		List<DataModelWithForm> dataToFile = new ArrayList<DataModelWithForm>();
		
		for(RawData data : rawData){
			Collections.sort(data.getMauseClicksList());
			DataModelWithForm dataToAdd = prepareEmptyDataToAdd(data);	
						
			setSingleKeysFeatures(data.getKeystrokeList(), dataToAdd);				
			setMauceClickFeatures(data.getMauseClicksList(), dataToAdd);
			setDigraphFeatures(data.getKeystrokeList(), dataToAdd);
			
			dataToFile.add(dataToAdd);
		}
		return dataToFile;			
	}

	private static void setDigraphFeatures(List<UserKeystrokes> keystrokeList, DataModelWithForm dataToAdd) {
		DiGraphFeatures digraphFeatures = new DiGraphFeatures();
		List<NGraph> result = digraphFeatures.prepareVector(keystrokeList);
		digraphFeatures.calculateVectors(result);
		dataToAdd.getFeatures().setFirstDwellMean(MathHelperUtils.calculateMean(digraphFeatures.getFirstDwell()));
		dataToAdd.getFeatures().setSecondDwellMean(MathHelperUtils.calculateMean(digraphFeatures.getSecondDwell()));
		dataToAdd.getFeatures().setTimeBetweenFirsAndSecondPressMean(MathHelperUtils.calculateMean(digraphFeatures.getTimeBetweenFirsAndSecondPress()));
		dataToAdd.getFeatures().setTimeBetweenFirstUpAndSecondDownMean(MathHelperUtils.calculateMean(digraphFeatures.getTimeBetweenFirstUpAndSecondDown()));
		dataToAdd.getFeatures().setDigraphDurationMean(MathHelperUtils.calculateMean(digraphFeatures.getDigraphDuration()));
		dataToAdd.getFeatures().setFirstDwellDeviation(MathHelperUtils.calculateStandardDeviation(digraphFeatures.getFirstDwell()));
		dataToAdd.getFeatures().setSecondDwellDeviation(MathHelperUtils.calculateStandardDeviation(digraphFeatures.getSecondDwell()));
		dataToAdd.getFeatures().setTimeBetweenFirsAndSecondPressDeviation(MathHelperUtils.calculateStandardDeviation(digraphFeatures.getTimeBetweenFirsAndSecondPress()));
		dataToAdd.getFeatures().setTimeBetweenFirstUpAndSecondDownDeviation(MathHelperUtils.calculateStandardDeviation(digraphFeatures.getTimeBetweenFirstUpAndSecondDown()));
		dataToAdd.getFeatures().setDigraphDurationDeviation(MathHelperUtils.calculateStandardDeviation(digraphFeatures.getDigraphDuration()));		
	}

	private static void setMauceClickFeatures(List<UserMauseClick> mauseClicks, DataModelWithForm dataToAdd) {
		Vector<Integer> timeBeetwwnClicks = MauseClickVector.mauseClickVector(mauseClicks);
		if(timeBeetwwnClicks.size()>0){				
			dataToAdd.getFeatures().setMauseClicksMean(MathHelperUtils.calculateMean(timeBeetwwnClicks));
			dataToAdd.getFeatures().setMauseClickDeviation(MathHelperUtils.calculateStandardDeviation(timeBeetwwnClicks));
			dataToAdd.getFeatures().setMauseCliskEventCount(mauseClicks.size());			
		}
	}

	private static void setSingleKeysFeatures(List<UserKeystrokes> keystrokes, DataModelWithForm dataToAdd) {
		SingleKeyFeatures singleKeyFeatures = new SingleKeyFeatures();
		singleKeyFeatures.prepareFreqMember(keystrokes);
		dataToAdd.getFeatures().setEnterFreq(singleKeyFeatures.getEnterFreq());	
		dataToAdd.getFeatures().setBackSpaceFreq(singleKeyFeatures.getBackspaceFreq());
		dataToAdd.getFeatures().setDelFreq(singleKeyFeatures.getDelFreq());
		dataToAdd.getFeatures().setEnterFreq(singleKeyFeatures.getEnterFreq());	
		dataToAdd.getFeatures().setTabFreq(singleKeyFeatures.getTabFreq());
		dataToAdd.getFeatures().setSingleKeyDwellDeviation(MathHelperUtils.calculateStandardDeviation(singleKeyFeatures.getSingleKeyDwellTime()));
		dataToAdd.getFeatures().setSingleKeyDwellMean(MathHelperUtils.calculateMean(singleKeyFeatures.getSingleKeyDwellTime()));
		dataToAdd.getFeatures().setWritingSpeed(singleKeyFeatures.getWriteSpeed());
	}
	
	private static DataModelWithForm prepareEmptyDataToAdd(RawData data){
		DataModelWithForm dataToAdd = new DataModelWithForm();
		Features features = new Features();
		dataToAdd.setFeatures(features);
		dataToAdd.setUserForm(data.getUserForm());
		dataToAdd.setUser(data.getUser());	
		return dataToAdd;
	}
	
}
