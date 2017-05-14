package com.michal.elearning.dao;

import java.math.BigDecimal;

public class UserKeystrokes implements Comparable<UserKeystrokes>{
	
	private int code;
	private BigDecimal time;
	private String type;
	
	private int[] foundGraph;
	
	public UserKeystrokes(){}
	
	public UserKeystrokes(int code, BigDecimal time, String type){
		this.code = code;
		this.time = time;
		this.type = type;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public BigDecimal getTime() {
		return time;
	}
	public void setTime(BigDecimal time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public int compareTo(UserKeystrokes o) {
		return this.getTime().compareTo(o.getTime());
	}

	public int[] getFoundGraph() {
		return foundGraph;
	}

	public void setFoundGraph(int[] foundGraph) {
		this.foundGraph = foundGraph;
	}

}
