package com.michal.elearning.dao;

import java.sql.Timestamp;

public class UserKeystrokes {
	
	private int keyCode;
	private int time;
	private String type;
	
	public int getKeyCode() {
		return keyCode;
	}
	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
