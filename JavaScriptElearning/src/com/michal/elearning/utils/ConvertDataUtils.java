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
import com.michal.elearning.modeldata.vectors.*;
import com.michal.elearning.modeldata.vectors.model.NGraph;
import com.michal.elearning.wekaDataModel.DataModelWithForm;
import com.michal.elearning.wekaDataModel.Features;

public class ConvertDataUtils {
	
	private static ILessonsInterface lessonService = new LessonDaoService();

	public static List<DataModelWithForm> getVectorsFromEditorLesson(List<UserInputData> userDataList, User user){
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
						try{
							mauseClick = converter.fromJson(mauseClicks.getJSONObject(i).toString(), UserMauseClick.class);
							mauseClicksList.add(mauseClick);
						}catch (Exception e) {
							//DO NOTHING
						}
					}
					
					UserKeystrokes keystroke = null;
					for(int i=0; i<keystrokes.length();i++){
						keystroke = converter.fromJson(keystrokes.getJSONObject(i).toString(), UserKeystrokes.class);
						keystrokeList.add(keystroke);
					}
					
					UserMauseMove mauseMove = null;
					for(int i=0; i<mauseMoves.length();i++){
						try{
							mauseMove = converter.fromJson(mauseMoves.getJSONObject(i).toString(), UserMauseMove.class);
							mauseMoveList.add(mauseMove);
						}catch (Exception e) {
							//DO NOTHING
						}
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
					
			setMauceClickFeatures(data.getMauseClicksList(), dataToAdd);
			setSingleKeysFeatures(data.getKeystrokeList(), dataToAdd);				
			setDigraphFeatures(data.getKeystrokeList(), dataToAdd);
			setTriGraphFeatuers(data.getKeystrokeList(), dataToAdd);
			setNGraphFeatures(data.getKeystrokeList(), dataToAdd);
			
			dataToFile.add(dataToAdd);
		}
		return dataToFile;			
	}

	private static void setMauceClickFeatures(List<UserMauseClick> mauseClicks, DataModelWithForm dataToAdd) {
		Vector<Integer> timeBetweenClicks = MauseClickFeatures.mauseClickVector(mauseClicks);
		if(timeBetweenClicks.size()>0){				
			dataToAdd.getFeatures().setMauseClicksMean(MathHelperUtils.calculateMean(timeBetweenClicks));
			dataToAdd.getFeatures().setMauseClickDeviation(MathHelperUtils.calculateStandardDeviation(timeBetweenClicks));
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
	
	private static void setDigraphFeatures(List<UserKeystrokes> keystrokeList, DataModelWithForm dataToAdd) {
		DiGraphFeatures digraphFeatures = new DiGraphFeatures();
		List<NGraph> result = digraphFeatures.prepareVector(keystrokeList);
		digraphFeatures.calculateVectors(result);
		dataToAdd.getFeatures().setDiGraphFirstDwellMean(MathHelperUtils.calculateMean(digraphFeatures.getFirstDwell()));
		dataToAdd.getFeatures().setDiGraphSecondDwellMean(MathHelperUtils.calculateMean(digraphFeatures.getSecondDwell()));
		dataToAdd.getFeatures().setDiGraphPressToPressMean(MathHelperUtils.calculateMean(digraphFeatures.getTimeBetweenFirsAndSecondPress()));
		dataToAdd.getFeatures().setDiGraphFlightMean(MathHelperUtils.calculateMean(digraphFeatures.getTimeBetweenFirstUpAndSecondDown()));
		dataToAdd.getFeatures().setDigraphDurationMean(MathHelperUtils.calculateMean(digraphFeatures.getDigraphDuration()));
		dataToAdd.getFeatures().setDiGraphFirstDwellDeviation(MathHelperUtils.calculateStandardDeviation(digraphFeatures.getFirstDwell()));
		dataToAdd.getFeatures().setDiGraphSecondDwellDeviation(MathHelperUtils.calculateStandardDeviation(digraphFeatures.getSecondDwell()));
		dataToAdd.getFeatures().setDiGraphPressToPressDeviation(MathHelperUtils.calculateStandardDeviation(digraphFeatures.getTimeBetweenFirsAndSecondPress()));
		dataToAdd.getFeatures().setDiGraphFlightDeviation(MathHelperUtils.calculateStandardDeviation(digraphFeatures.getTimeBetweenFirstUpAndSecondDown()));
		dataToAdd.getFeatures().setDigraphDurationDeviation(MathHelperUtils.calculateStandardDeviation(digraphFeatures.getDigraphDuration()));		
	}
	
	private static void setTriGraphFeatuers(List<UserKeystrokes> keystrokeList, DataModelWithForm dataToAdd) {
		TriGraphFeatures triGraphsFeatures = new TriGraphFeatures();
		List<NGraph> result = triGraphsFeatures.prepareVector(keystrokeList);
		triGraphsFeatures.calculateVectors(result);
		dataToAdd.getFeatures().setTriGraphDwellFirstMean(MathHelperUtils.calculateMean(triGraphsFeatures.getDwellFirst()));
		dataToAdd.getFeatures().setTriGraphDwellSecondMean(MathHelperUtils.calculateMean(triGraphsFeatures.getDwellSecond()));
		dataToAdd.getFeatures().setTriGraphDwellThirdMean(MathHelperUtils.calculateMean(triGraphsFeatures.getDwellThird()));
		dataToAdd.getFeatures().setTriGraphPressFirstSecMean(MathHelperUtils.calculateMean(triGraphsFeatures.getPressFirstSec()));
		dataToAdd.getFeatures().setTriGraphPressSecThirdMean(MathHelperUtils.calculateMean(triGraphsFeatures.getPressSecThird()));
		dataToAdd.getFeatures().setTriGraphFlightFirstSecMean(MathHelperUtils.calculateMean(triGraphsFeatures.getFlightFirstSec()));
		dataToAdd.getFeatures().setTriGraphFlightSecThirdMean(MathHelperUtils.calculateMean(triGraphsFeatures.getFlightSecThird()));
		dataToAdd.getFeatures().setTriGraphPressToPressMean(MathHelperUtils.calculateMean(triGraphsFeatures.getPressToPress()));
		dataToAdd.getFeatures().setTriGraphKeyDwellMean(MathHelperUtils.calculateMean(triGraphsFeatures.getKeyDwell()));
		dataToAdd.getFeatures().setTriGraphDurationMean(MathHelperUtils.calculateMean(triGraphsFeatures.getWordDuration()));
		dataToAdd.getFeatures().setTriGraphDwellFirstDeviation(MathHelperUtils.calculateStandardDeviation(triGraphsFeatures.getDwellFirst()));
		dataToAdd.getFeatures().setTriGraphDwellSecondDeviation(MathHelperUtils.calculateStandardDeviation(triGraphsFeatures.getDwellSecond()));
		dataToAdd.getFeatures().setTriGraphDwellThirdDeviation(MathHelperUtils.calculateStandardDeviation(triGraphsFeatures.getDwellThird()));
		dataToAdd.getFeatures().setTriGraphPressFirstSecDeviation(MathHelperUtils.calculateStandardDeviation(triGraphsFeatures.getPressFirstSec()));
		dataToAdd.getFeatures().setTriGraphPressSecThirdDeviation(MathHelperUtils.calculateStandardDeviation(triGraphsFeatures.getPressSecThird()));
		dataToAdd.getFeatures().setTriGraphFlightFirstSecDeviation(MathHelperUtils.calculateStandardDeviation(triGraphsFeatures.getFlightFirstSec()));
		dataToAdd.getFeatures().setTriGraphFlightSecThirdDeviation(MathHelperUtils.calculateStandardDeviation(triGraphsFeatures.getFlightSecThird()));
		dataToAdd.getFeatures().setTriGraphPressToPressDeviation(MathHelperUtils.calculateStandardDeviation(triGraphsFeatures.getPressToPress()));
		dataToAdd.getFeatures().setTriGraphKeyDwellDeviation(MathHelperUtils.calculateStandardDeviation(triGraphsFeatures.getKeyDwell()));
		dataToAdd.getFeatures().setTriGraphDurationDeviation(MathHelperUtils.calculateStandardDeviation(triGraphsFeatures.getWordDuration()));
	}
	
	private static void setNGraphFeatures(List<UserKeystrokes> keystrokeList, DataModelWithForm dataToAdd) {
		NGraphsFeatures nGraphFeatures = new NGraphsFeatures();
		List<NGraph> result = nGraphFeatures.prepareVector(keystrokeList);
		nGraphFeatures.calculateVectors(result);		
		dataToAdd.getFeatures().setGraphPressToPressMean(MathHelperUtils.calculateMean(nGraphFeatures.getPressToPress()));
		dataToAdd.getFeatures().setGraphKeyDwellMean(MathHelperUtils.calculateMean(nGraphFeatures.getKeyDwell()));
		dataToAdd.getFeatures().setGraphWordDurationMean(MathHelperUtils.calculateMean(nGraphFeatures.getWordDuration()));
		dataToAdd.getFeatures().setGraphPressToPressDeviation(MathHelperUtils.calculateStandardDeviation(nGraphFeatures.getPressToPress()));
		dataToAdd.getFeatures().setGraphKeyDwellDeviation(MathHelperUtils.calculateStandardDeviation(nGraphFeatures.getKeyDwell()));
		dataToAdd.getFeatures().setGraphWordDurationDeviation(MathHelperUtils.calculateStandardDeviation(nGraphFeatures.getWordDuration()));
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
