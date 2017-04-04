package com.michal.elearning.machineLearning;

import java.util.Vector;

import com.michal.elearning.dao.UserMauseMove;

public class MathHelperUtils {

	public static double calculateMean(Vector<Integer> data) {
		if(data==null){
			return 0;
		}
		if (data.size() == 0) {
			return 0;
		}
		int suma = 0;
		for (Integer dwell : data) {
			suma += dwell;
		}
		return (double)suma / (double)data.size();
	}

	public static double calculateStandardDeviation(Vector<Integer> data) {
		if(data==null){
			return 0;
		}
		if (data.size() == 0) {
			return 0;
		}

		double mean = calculateMean(data);
		double sum = 0;
		double deviation = 0;

		for (Integer dwell : data) {
			deviation = Math.pow(dwell - mean, 2);
			sum += deviation;
		}

		double standardDeviation = sum / (double)data.size();
		return Math.sqrt(standardDeviation);
	}

	public static double calculateDistence(UserMauseMove start, UserMauseMove stop) {
		if(start==null || stop==null){
			return 0;
		}
		
		double result = 0.0;
		double X = Math.pow((stop.getPosX() - start.getPosX()), 2.0);
		double Y = Math.pow((stop.getPosY() - start.getPosY()), 2.0);
		result = Math.sqrt((X + Y));
		return result;
	}

	public static Integer calculateElapsedTime(UserMauseMove start, UserMauseMove stop) {
		if(start==null || stop==null){
			return 0;
		}
		
		return stop.getTime() - start.getTime();
	}

	public static final int LEFT = 1;
	public static final int UP = 2;
	public static final int RIGTH = 3;
	public static final int DOWN = 4;

	public static Integer calculateDirection(UserMauseMove start, UserMauseMove stop) {
		if(start==null || stop==null){
			return 0;
		}
		
		// get the change from last position to this position
		int deltaX = start.getPosX() - stop.getPosX();
		int deltaY = start.getPosY() - stop.getPosY();

		// check which direction had the highest amplitude and then figure out direction by checking if the value is greater or less than zero
		if (Math.abs(deltaX) > Math.abs(deltaY) && deltaX > 0) {
			return LEFT;
		} else if (Math.abs(deltaX) > Math.abs(deltaY) && deltaX < 0) {
			return RIGTH;
		} else if (Math.abs(deltaY) > Math.abs(deltaX) && deltaY > 0) {
			return DOWN;
		} else if (Math.abs(deltaY) > Math.abs(deltaX) && deltaY < 0) {
			return UP;
		}
		
		return 0;
	}
}
