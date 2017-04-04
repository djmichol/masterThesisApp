package com.michal.elearning.dao;

public class UserMauseMove implements Comparable<UserMauseMove>{
	
	private int posX;
	private int posY;
	private Integer time;
	
	public UserMauseMove(){}
	
	public UserMauseMove(Integer time,int posX,int posY){
		this.posX = posX;
		this.posY = posY;
		this.time = time;
	}
	
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	
	@Override
	public int compareTo(UserMauseMove o) {
		return this.getTime().compareTo(o.getTime());
	}

}
