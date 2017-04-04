package com.michal.elearning.wekaDataModel;

public class SingleMove {

	private double distance;
	private int direction;
	private int elapsedTime;
	
	public SingleMove(){}
	
	public SingleMove(double dist,int dir, int elapsedTime){
		this.distance = dist;
		this.direction = dir;
		this.elapsedTime = elapsedTime;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(int elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

}
