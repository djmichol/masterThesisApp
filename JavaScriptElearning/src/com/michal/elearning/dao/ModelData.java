package com.michal.elearning.dao;

public class ModelData {

	private int id;
	private byte[] classifier; 
	private int userId;
	private String emotion;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte[] getClassifier() {
		return classifier;
	}
	public void setClassifier(byte[] classifier) {
		this.classifier = classifier;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmotion() {
		return emotion;
	}
	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}
}
