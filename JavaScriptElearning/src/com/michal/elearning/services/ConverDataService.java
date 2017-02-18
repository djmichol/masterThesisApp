package com.michal.elearning.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
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
import com.michal.elearning.daoServices.ModelDaoService;
import com.michal.elearning.daoServices.UserDaoService;
import com.michal.elearning.daoServices.UserInputDataDaoService;
import com.michal.elearning.machineLearning.ArffFileHelper;
import com.michal.elearning.machineLearning.MathHelperUtils;
import com.michal.elearning.machineLearning.WekaClassifierUtils;
import com.michal.elearning.modeldata.vectors.SingleKeyFeatures;
import com.michal.elearning.modeldata.vectors.MauseClickVector;
import com.michal.elearning.wekaDataModel.Features;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;

import com.michal.elearning.wekaDataModel.DataModelWithForm;

@Path("/convertData")
public class ConverDataService {
	
	private IUserInputData dataService = new UserInputDataDaoService();
	private ILessonsInterface lessonService = new LessonDaoService();
	private IUserInterface userService = new UserDaoService();
	private ModelDaoService modelService = new ModelDaoService();			
	
	@Context 
	SecurityContext securityContext;
	
	private int userId;
	
	@RolesAllowed("admin")
	@PermitAll
    @GET
    @Path("/userModel")
    public Response getUserModel(@QueryParam("userId") int userId) throws SQLException 
    {   
		this.userId = userId;
    	@SuppressWarnings("unused")
		List<ModelData> userModels = modelService.getUserTrainedModels(userId);
    	return Response.ok().build(); 
    }
	
	@RolesAllowed("admin")
	@PermitAll
    @POST
    @Path("/saveModel")
    public Response saveUserModel(@QueryParam("userId") int userId) 
    {   
		this.userId = userId;
    	prepareModel();    	
    	return Response.ok().build(); 
    }
	
	private void prepareModel(){
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
					if(lesson.getType().equals("editor")){
						rawData.add(new RawData(mauseClicksList, keystrokeList, mauseMoveList, userForm, lesson.getType(),user.getMail()));
					}
				}
				
				List<DataModelWithForm> dataToFile = computeVectors(rawData);
				
				tryToInsertUserLernedModel(dataToFile);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void tryToInsertUserLernedModel(List<DataModelWithForm> dataToFile) {
		try {
			modelService.deleteUserModels(userId);
			Map<String,byte[]> arffInputStream = ArffFileHelper.prepareArffInputStream(dataToFile);
			for (Map.Entry<String, byte[]> entry : arffInputStream.entrySet())
			{
				J48 j48Classifier = new J48();	
				WekaClassifierUtils.trainClassiffier(new ByteArrayInputStream(entry.getValue()), j48Classifier);
				
				tryToSaveTmpArffFile(entry);
				
				insertUserModel(entry.getKey(), j48Classifier);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void tryToSaveTmpArffFile(Map.Entry<String, byte[]> entry) throws IOException {		
		File targetFile = new File("C:\\DataWeka\\"+ entry.getKey()+".arff");
	    OutputStream outStream = new FileOutputStream(targetFile);
	    outStream.write(entry.getValue());
	    outStream.close();
	}

	private void insertUserModel(String emotion, Classifier classifier)throws IOException, SQLException {		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = new ObjectOutputStream(bos);    
		out.writeObject(classifier);
		out.flush();
		
		ModelData modelData = new ModelData();
		modelData.setEmotion(emotion);
		modelData.setUserId(userId);
		modelData.setClassifier(bos.toByteArray());
		modelService.insertUserModel(modelData);
		
		out.close();
		bos.close();		
	}
	
	private List<DataModelWithForm> computeVectors(List<RawData> rawData){
		List<DataModelWithForm> dataToFile = new ArrayList<DataModelWithForm>();
		
		for(RawData data : rawData){
			Collections.sort(data.getMauseClicksList());
			DataModelWithForm dataToAdd = prepareEmptyDataToAdd(data);	
						
			setSingleKeysFeatures(data.getKeystrokeList(), dataToAdd);				
			setMauceClickFeatures(data.getMauseClicksList(), dataToAdd);
			
			dataToFile.add(dataToAdd);
		}
		return dataToFile;			
	}

	private void setMauceClickFeatures(List<UserMauseClick> mauseClicks, DataModelWithForm dataToAdd) {
		Vector<Integer> timeBeetwwnClicks = MauseClickVector.mauseClickVector(mauseClicks);
		if(timeBeetwwnClicks.size()>0){				
			dataToAdd.getFeatures().setMauseClicksMean(MathHelperUtils.calculateMean(timeBeetwwnClicks));
			dataToAdd.getFeatures().setMauseClickDeviation(MathHelperUtils.calculateStandardDeviation(timeBeetwwnClicks));
			dataToAdd.getFeatures().setMauseCliskEventCount(mauseClicks.size());			
		}
	}

	private void setSingleKeysFeatures(List<UserKeystrokes> keystrokes, DataModelWithForm dataToAdd) {
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
	
	private DataModelWithForm prepareEmptyDataToAdd(RawData data){
		DataModelWithForm dataToAdd = new DataModelWithForm();
		Features features = new Features();
		dataToAdd.setFeatures(features);
		dataToAdd.setUserForm(data.getUserForm());
		dataToAdd.setUser(data.getUser());	
		return dataToAdd;
	}
	
	
}
