package com.michal.elearning.dao;

public class UserInLessons {

	int id;
	int userId;
	int lessonId;
	int submitCount;
	private byte[] keyStrokes;
	private byte[] mouseMove;
	private byte[] mouseClicks;
	private int passed;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getLessonId() {
		return lessonId;
	}
	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}
	public int getSubmitCount() {
		return submitCount;
	}
	public void setSubmitCount(int submitCount) {
		this.submitCount = submitCount;
	}
	public byte[] getKeyStrokes() {
		return keyStrokes;
	}
	public void setKeyStrokes(byte[] keyStrokes) {
		this.keyStrokes = keyStrokes;
	}
	public byte[] getMouseMove() {
		return mouseMove;
	}
	public void setMouseMove(byte[] mouseMove) {
		this.mouseMove = mouseMove;
	}
	public byte[] getMouseClicks() {
		return mouseClicks;
	}
	public void setMouseClicks(byte[] mouseClicks) {
		this.mouseClicks = mouseClicks;
	}
	public int isPassed() {
		return passed;
	}
	public void setPassed(int passed) {
		this.passed = passed;
	}
	
}
