package com.michal.elearning.modeldata.vectors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import com.michal.elearning.dao.UserMauseClick;
import com.michal.elearning.dao.UserMauseMove;
import com.michal.elearning.machineLearning.MathHelperUtils;
import com.michal.elearning.wekaDataModel.SingleMove;

public class MauseMoveFeatures {
	
	private double mauseSpeed;
	private double horizontalToTotalRatio;
	private double verticalToTotalRatio;
	private Vector<Double> clickToClickDistanceToTotalPathLengthRatio;
	
	private Vector<Double> pausesTimes;
	private int pauses;
	
	private static final int minPauseTimeInMilis = 3000; //3sek
	
	public void clear(){
		mauseSpeed = 0;
		horizontalToTotalRatio = 0;
		verticalToTotalRatio = 0;
		pauses = 0;
		clickToClickDistanceToTotalPathLengthRatio = new Vector<>();
		pausesTimes = new Vector<>();
	}
	
	public void prepareVector(List<UserMauseMove> mauseMoves, List<UserMauseClick> mauseClicks){
		Collections.sort(mauseMoves);
		Collections.sort(mauseClicks);
		
		double allDistance = 0.0;
		double allHorizontalDistance = 0.0;
		double allVerticalDistance = 0.0;
		Integer allTime = 0;
		
		List<SingleMove> singleMoves = new ArrayList<>();
		
		int clickIndex = 0;
		
		List<List<UserMauseMove>> paths = new ArrayList<>();
		List<UserMauseMove> path = new ArrayList<>();
		for(int i=0;i<mauseMoves.size()-1;i++){
				
			if(clickIndex+1<mauseClicks.size()){
				if(mauseClicks.get(clickIndex).getTime().intValue()<mauseMoves.get(i).getTime().intValue() && mauseClicks.get(clickIndex+1).getTime().intValue()>mauseMoves.get(i).getTime().intValue()){
					path.add(mauseMoves.get(i));
				}else if(mauseClicks.get(clickIndex+1).getTime().intValue()<=mauseMoves.get(i).getTime().intValue()){
					if(path.size()>1){
						paths.add(path);
					}
					path = new ArrayList<>();
					clickIndex++;
				}
			}
			
			Integer timeBetween = mauseMoves.get(i+1).getTime().intValue()-mauseMoves.get(i).getTime().intValue();
			if(timeBetween>=minPauseTimeInMilis){
				pauses++;
				pausesTimes.add((double) timeBetween);
			}
			
			int elapsedTime  =  MathHelperUtils.calculateElapsedTime(mauseMoves.get(i), mauseMoves.get(i+1));
			double distance = MathHelperUtils.calculateDistence(mauseMoves.get(i), mauseMoves.get(i+1));
			int direction = MathHelperUtils.calculateDirection(mauseMoves.get(i), mauseMoves.get(i+1));
			SingleMove sm = new SingleMove(distance, direction, elapsedTime);
			if(direction==MathHelperUtils.LEFT || direction==MathHelperUtils.RIGTH){
				allHorizontalDistance+=distance;
			}else if(direction==MathHelperUtils.UP || direction==MathHelperUtils.DOWN){
				allVerticalDistance+=distance;
			}
			singleMoves.add(sm);
			allTime += elapsedTime;
			allDistance += distance;
		}
		double allTimeInSec = (allTime/1000.0);
		mauseSpeed = allDistance/allTimeInSec;
		if(allDistance>0){
			horizontalToTotalRatio = (allHorizontalDistance/allDistance)*100.0;
			verticalToTotalRatio = (allVerticalDistance/allDistance)*100.0;
		}else{
			horizontalToTotalRatio = 0.0;
			verticalToTotalRatio =0.0;
		}
		
		clickToClickDistanceToTotalPathLengthRatio = calculateDistanceToPathRatio(paths);		
	}
	
	private Vector<Double> calculateDistanceToPathRatio(List<List<UserMauseMove>> paths){
		Vector<Double> ratio = new Vector<>();
		for(List<UserMauseMove> path : paths){
			double distance = MathHelperUtils.calculateDistence(path.get(0), path.get(path.size()-1));
			double pathLength = 0.0;
			for(int i=0;i<path.size()-1;i++){
				pathLength += MathHelperUtils.calculateDistence(path.get(i), path.get(i+1));
			}
			ratio.add((pathLength/distance)*100);
		}
		return ratio;
	}

	public double getMauseSpeed() {
		return mauseSpeed;
	}

	public double getHorizontalToTotalRatio() {
		return horizontalToTotalRatio;
	}

	public double getVerticalToTotalRatio() {
		return verticalToTotalRatio;
	}

	public Vector<Double> getClickToClickDistanceToTotalPathLengthRatio() {
		return clickToClickDistanceToTotalPathLengthRatio;
	}

	public Vector<Double> getPausesTimes() {
		return pausesTimes;
	}

	public int getPauses() {
		return pauses;
	}

}
