package com.michal.elearning.test;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.michal.elearning.dao.ModelData;
import com.michal.elearning.daoServices.ModelDaoService;
import com.michal.elearning.machineLearning.WekaClassifierUtils;

import weka.classifiers.Classifier;
import weka.core.SerializationHelper;

public class WekaTest {

	private String dataArff = "@relation engaged \n"
			+"@attribute mauseClicksMean NUMERIC \n"
			+"@attribute mauseClickDeviation NUMERIC \n"
			+"@attribute mauseCliskEventCount NUMERIC \n"
			+"@attribute tabFreq NUMERIC \n"
			+"@attribute delFreq NUMERIC \n"
			+"@attribute enterFreq NUMERIC \n" 
			+"@attribute backSpaceFreq NUMERIC \n"
			+"@attribute spaceFreq NUMERIC \n"
			+"@attribute writingSpeed NUMERIC \n"
			+"@attribute singleKeyDwellMean NUMERIC \n"
			+"@attribute singleKeyDwellDeviation NUMERIC \n"
			+"@attribute firstDwellMean NUMERIC \n"
			+"@attribute secondDwellMean NUMERIC \n"
			+"@attribute timeBetweenFirsAndSecondPressMean NUMERIC \n"
			+"@attribute timeBetweenFirstUpAndSecondDownMean NUMERIC \n"
			+"@attribute digraphDurationMean NUMERIC \n"
			+"@attribute firstDwellDeviation NUMERIC \n"
			+"@attribute secondDwellDeviation NUMERIC \n"
			+"@attribute timeBetweenFirsAndSecondPressDeviation NUMERIC \n"
			+"@attribute timeBetweenFirstUpAndSecondDownDeviation NUMERIC \n"
			+"@attribute digraphDurationDeviation NUMERIC \n"
			+"@attribute engaged {Yes,No} \n"
			+"@data \n"
			+"5937,4151,11,0,0,4,0,0,185.4850581480936,105,20,109,78,218,109,296,15,31,15,0,15,?";

	private ModelDaoService modelService = new ModelDaoService();	
	@Test
	public void testClassifi() throws Exception{
		
		int userId = 1;
		
		List<ModelData> userModels = modelService.getUserTrainedModels(userId);
    	
    	Map<String,byte[]> arffInputStream = new HashMap<>();    	
    	arffInputStream.put("engaged", dataArff.getBytes());
		for (Map.Entry<String, byte[]> entry : arffInputStream.entrySet())
		{
			for(ModelData trainedModel : userModels){
				if(trainedModel.getEmotion().equals(entry.getKey())){
					Classifier cls = (Classifier)SerializationHelper.read(new ByteArrayInputStream(trainedModel.getClassifier()));
					WekaClassifierUtils.getTrainedClass(new ByteArrayInputStream(entry.getValue()), cls);
				}
			}
		}		
	}
	
}
