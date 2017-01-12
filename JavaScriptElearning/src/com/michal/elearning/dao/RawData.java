package com.michal.elearning.dao;

import java.util.List;

public class RawData {
	
	private List<UserMauseClick> mauseClicksList;
	private List<UserKeystrokes> keystrokeList;
	private List<UserMauseMove> mauseMoveList;
	private UserInputForm userForm;
	private String user;
	private String type;
	
	public RawData(List<UserMauseClick> mauseClicksList, List<UserKeystrokes> keystrokeList, List<UserMauseMove> mauseMoveList, UserInputForm userForm, String type,String user){
		this.mauseClicksList = mauseClicksList;
		this.keystrokeList = keystrokeList;
		this.mauseMoveList = mauseMoveList;
		this.userForm = userForm;
		this.type = type;
		this.user = user;
	}
	
	public List<UserMauseClick> getMauseClicksList() {
		return mauseClicksList;
	}
	public void setMauseClicksList(List<UserMauseClick> mauseClicksList) {
		this.mauseClicksList = mauseClicksList;
	}
	public List<UserKeystrokes> getKeystrokeList() {
		return keystrokeList;
	}
	public void setKeystrokeList(List<UserKeystrokes> keystrokeList) {
		this.keystrokeList = keystrokeList;
	}
	public List<UserMauseMove> getMauseMoveList() {
		return mauseMoveList;
	}
	public void setMauseMoveList(List<UserMauseMove> mauseMoveList) {
		this.mauseMoveList = mauseMoveList;
	}
	public UserInputForm getUserForm() {
		return userForm;
	}
	public void setUserForm(UserInputForm userForm) {
		this.userForm = userForm;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
