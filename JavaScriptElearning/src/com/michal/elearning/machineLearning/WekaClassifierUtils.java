package com.michal.elearning.machineLearning;

import java.io.InputStream;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class WekaClassifierUtils {

	public static Classifier trainClassiffier(InputStream arffData, Classifier classifier) throws Exception{
		DataSource dataSource = new DataSource(arffData);
		Instances data = dataSource.getDataSet();
		data.setClassIndex(data.numAttributes()-1);					
		Evaluation evaluation = new Evaluation(data);				 
		classifier.buildClassifier(data);
		evaluation.evaluateModel(classifier,data); 
		return classifier;
	}	
	
	public static String getTrainedClass(InputStream dataToClassify, Classifier classifier) throws Exception{
		
		DataSource toClassifyDataSource = new DataSource(dataToClassify);
		Instances toClassifyInstances = toClassifyDataSource.getDataSet();
		toClassifyInstances.setClassIndex(toClassifyInstances.numAttributes()-1);	
		
		double value = classifier.classifyInstance(toClassifyInstances.instance(0));
		String prediction = toClassifyInstances.classAttribute().value((int)value); 
		System.out.println(prediction);
		return prediction;
	}	
}
