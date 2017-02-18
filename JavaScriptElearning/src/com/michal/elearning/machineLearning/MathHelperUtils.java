package com.michal.elearning.machineLearning;

import java.util.Vector;

public class MathHelperUtils {

	public static int calculateMean(Vector<Integer> data){
		int suma = 0;
		for(Integer dwell : data){
			suma += dwell;
		}
		return suma/data.size();
	}
	 
	public static int calculateStandardDeviation(Vector<Integer> data){
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
