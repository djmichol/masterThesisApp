package com.michal.elearning.dao;

public class LessonTabs {
	
	private int id;
	private String name;
	private String content;
	private String contentSubmit;
	private String editorResult;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentSubmit() {
		return contentSubmit;
	}
	public void setContentSubmit(String contentSubmit) {
		this.contentSubmit = contentSubmit;
	}
	public String getEditorResult() {
		return editorResult;
	}
	public void setEditorResult(String editorResult) {
		this.editorResult = editorResult;
	}

}