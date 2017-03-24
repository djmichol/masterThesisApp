package com.michal.elearning.dao;

public class UserInLessons {

	int id;
	int userId;
	int lessonId;
	int submitCount;
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
	public int isPassed() {
		return passed;
	}
	public void setPassed(int passed) {
		this.passed = passed;
	}
	
}
