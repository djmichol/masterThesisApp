package com.michal.elearning.dao;

public class LessonBlock {
	
	private int id;
	private String name;
	private String description;
	private int pathId;
	private int order;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPathId() {
		return pathId;
	}
	public void setPathId(int pathId) {
		this.pathId = pathId;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}

}
