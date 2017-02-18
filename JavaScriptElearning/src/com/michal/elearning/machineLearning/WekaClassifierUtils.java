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
}
