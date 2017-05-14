package com.michal.elearning.dao;

import java.math.BigDecimal;

import com.google.gson.annotations.SerializedName;

public class UserMauseMove implements Comparable<UserMauseMove>{
	@SerializedName("X")
	private int posX;
	@SerializedName("Y")
	private int posY;
	@SerializedName("time")
	private BigDecimal time;
	
	public UserMauseMove(){}
	
	public UserMauseMove(BigDecimal time,int posX,int posY){
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
	public BigDecimal getTime() {
		return time;
	}
	public void setTime(BigDecimal time) {
		this.time = time;
	}
	
	@Override
	public int compareTo(UserMauseMove o) {
		return this.getTime().compareTo(o.getTime());
	}

}
