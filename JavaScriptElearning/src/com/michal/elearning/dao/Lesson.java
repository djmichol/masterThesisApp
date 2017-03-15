package com.michal.elearning.dao;

import java.util.List;

public class Lesson {
	
	private int id;
	private String title;
	private String article;
	private String brief;
	private int blockId;
	private List<String> instructions;
	private List<LessonTabs> tabs;
	private LessonVideo video;
	private List<LessonQuizQuestion> quiz;
	private String type;
	private int order;
	private boolean passed;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public List<String> getInstructions() {
		return instructions;
	}
	public void setInstructions(List<String> instructions) {
		this.instructions = instructions;
	}
	public List<LessonTabs> getTabs() {
		return tabs;
	}
	public void setTabs(List<LessonTabs> tabs) {
		this.tabs = tabs;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public int getBlockId() {
		return blockId;
	}
	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public LessonVideo getVideo() {
		return video;
	}
	public void setVideo(LessonVideo video) {
		this.video = video;
	}
	public List<LessonQuizQuestion> getQuiz() {
		return quiz;
	}
	public void setQuiz(List<LessonQuizQuestion> quiz) {
		this.quiz = quiz;
	}
	public boolean isPassed() {
		return passed;
	}
	public void setPassed(boolean passed) {
		this.passed = passed;
	}
}
