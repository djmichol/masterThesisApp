package com.michal.elearning.dao;

public class UserMauseClick implements Comparable<UserMauseClick>{

	private String type;
	private int time;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	public int compareTo(UserMauseClick compareClick) {

		int compareTime = ((UserMauseClick) compareClick).getTime();

		//ascending order
		return this.time - compareTime;
	}
}
