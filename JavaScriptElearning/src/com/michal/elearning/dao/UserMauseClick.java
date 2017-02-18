package com.michal.elearning.dao;

public class UserMauseClick implements Comparable<UserMauseClick>{

	private String type;
	private Integer time;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	
	public int compareTo(UserMauseClick compareClick) {
		return this.getTime().compareTo(compareClick.getTime());
	}
}
