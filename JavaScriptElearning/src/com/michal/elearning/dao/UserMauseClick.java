package com.michal.elearning.dao;

import java.math.BigDecimal;

public class UserMauseClick implements Comparable<UserMauseClick>{

	private String type;
	private BigDecimal time;
	
	public UserMauseClick(){}
	
	public UserMauseClick(String type, BigDecimal time){
		this.type = type;
		this.time = time;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getTime() {
		return time;
	}
	public void setTime(BigDecimal time) {
		this.time = time;
	}
	
	public int compareTo(UserMauseClick compareClick) {
		return this.getTime().compareTo(compareClick.getTime());
	}
}
