package com.michal.elearning.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.json.JSONObject;

import com.michal.elearning.dao.*;
import com.michal.elearning.daoServices.IUserInputData;
import com.michal.elearning.daoServices.IUserInterface;
import com.michal.elearning.daoServices.ModelDaoService;
import com.michal.elearning.daoServices.UserDaoService;
import com.michal.elearning.daoServices.UserInputDataDaoService;
import com.michal.elearning.machineLearning.ArffFileHelper;
import com.michal.elearning.machineLearning.WekaClassifierUtils;
import com.michal.elearning.utils.ConvertDataUtils;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.SerializationHelper;

import com.michal.elearning.wekaDataModel.DataModelWithForm;

@Path("/convertData")
public class ConverDataService {
	
	private IUserInputData dataService = new UserInputDataDaoService();
	private IUserInterface userService = new UserDaoService();
	private ModelDaoService modelService = new ModelDaoService();			
	
	@Context 
	SecurityContext securityContext;
	
	private int userId;
	
	@RolesAllowed("user")
    @POST
    @Path("/predict")
    public Response getUserModel(String inputData) throws Exception 
    {   
		this.userId = ((User)securityContext.getUserPrincipal()).getId();		
		JSONObject json = predict(inputData);
		if(json==null){
			Response.noContent().build();
		}
    	return Response.ok(json.toString()).build(); 
    }
	
	@RolesAllowed("admin")
    @POST
    @Path("/generateModel")
    public Response saveUserModel(@QueryParam("userId") int userId) 
    {   
		this.userId = userId;
    	try {
			prepareModel();
		} catch (SQLException e) {
			e.printStackTrace();
		}    	
    	return Response.ok().build(); 
    }
	
	@RolesAllowed("admin")
    @POST
    @Path("/generateModelTest")
    public Response saveUserTestModel(@QueryParam("userId") int userId) 
    {   
		this.userId = userId;
    	try {
			prepareTestModel();
		} catch (SQLException e) {
			e.printStackTrace();
		}    	
    	return Response.ok().build(); 
    }
	
	private void prepareTestModel() throws SQLException {
		List<UserInputData> userDataList = dataService.getUserData(userId);	
		User user = userService.getUserByID(userId);
		List<DataModelWithForm> dataToFile = ConvertDataUtils.getVectorsFromEditorLesson(userDataList, user, "quiz");
		if(dataToFile==null){
			return;
		}
		tryToInsertQuizUserLernedModel(dataToFile,"quiz");		
	}
	
	private void tryToInsertQuizUserLernedModel(List<DataModelWithForm> dataToFile, String type) {
		try {
			Map<String,byte[]> arffInputStream = ArffFileHelper.prepareArffInputStream(dataToFile,false,"quiz");
			for (Map.Entry<String, byte[]> entry : arffInputStream.entrySet())
			{
				J48 j48Classifier = new J48();	
				WekaClassifierUtils.trainClassiffier(new ByteArrayInputStream(entry.getValue()), j48Classifier);				
				tryToSaveTmpArffFile(entry, type);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JSONObject predict(String inputData) throws SQLException, Exception {
		Map<String,String> predictions = new HashMap<>();
		
		List<UserInputData> data = getUserInputDataFromString(inputData, userId);		
		List<ModelData> userModels = modelService.getUserTrainedModels(userId);
		if(userModels==null || userModels.isEmpty()){
			return null;
		}
		Map<String, byte[]> arffInputStream = getDataToPredict(userId, data);    	
		
		for (Map.Entry<String, byte[]> entry : arffInputStream.entrySet())
		{
			for(ModelData trainedModel : userModels){
				if(trainedModel.getEmotion().equals(entry.getKey())){
					Classifier cls = (Classifier)SerializationHelper.read(new ByteArrayInputStream(trainedModel.getClassifier()));
					predictions.put(entry.getKey(), WekaClassifierUtils.getTrainedClass(new ByteArrayInputStream(entry.getValue()), cls));
				}
			}
		}    	
		JSONObject json = new JSONObject();
	    json.put("predictions", predictions);
		return json;
	}
	
	private List<UserInputData> getUserInputDataFromString(String inputData, int userId) {
		JSONObject inpoutData = new JSONObject(inputData);
		UserInputData userDataObject = new UserInputData();
		userDataObject.setKeyStrokes(inpoutData.getJSONArray("keyStroke").toString().getBytes());
		userDataObject.setMouseMove(inpoutData.getJSONArray("mauseMove").toString().getBytes());
		userDataObject.setMouseClicks(inpoutData.getJSONArray("mauseClick").toString().getBytes());
		userDataObject.setUsrId(userId);
		userDataObject.setLessonId(inpoutData.getInt("lessonId"));
		List<UserInputData> data = new ArrayList<>();
		data.add(userDataObject);
		return data;
	}
	
	private Map<String, byte[]> getDataToPredict(int userId, List<UserInputData> data) throws SQLException, Exception {
		List<DataModelWithForm> dataToFile = ConvertDataUtils.getVectorsFromEditorLesson(data, userService.getUserByID(userId),"editor");
    	Map<String,byte[]> arffInputStream = ArffFileHelper.prepareArffInputStream(dataToFile,true,"editor");
		return arffInputStream;
	}	
	
	private void prepareModel() throws SQLException{
		List<UserInputData> userDataList = dataService.getUserData(userId);	
		User user = userService.getUserByID(userId);
		List<DataModelWithForm> dataToFile = ConvertDataUtils.getVectorsFromEditorLesson(userDataList, user, "editor");
		if(dataToFile==null){
			return;
		}
		tryToInsertUserLernedModel(dataToFile,"editor");
	}

	private void tryToInsertUserLernedModel(List<DataModelWithForm> dataToFile, String type) {
		try {
			modelService.deleteUserModels(userId);
			Map<String,byte[]> arffInputStream = ArffFileHelper.prepareArffInputStream(dataToFile,false,"editor");
			for (Map.Entry<String, byte[]> entry : arffInputStream.entrySet())
			{
				J48 j48Classifier = new J48();	
				WekaClassifierUtils.trainClassiffier(new ByteArrayInputStream(entry.getValue()), j48Classifier);				
				tryToSaveTmpArffFile(entry, type);	
				insertUserModel(entry.getKey(), j48Classifier);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void tryToSaveTmpArffFile(Map.Entry<String, byte[]> entry, String type) throws IOException {		
		File targetFile = new File("C:\\DataWeka\\"+ entry.getKey()+"-"+type+".arff");
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
}
