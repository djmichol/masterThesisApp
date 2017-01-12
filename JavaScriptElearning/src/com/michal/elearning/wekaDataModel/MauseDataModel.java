package com.michal.elearning.wekaDataModel;

import com.michal.elearning.dao.UserInputForm;

public class MauseDataModel {

	private Features features;
	private UserInputForm userForm;
	private String user;
	
	public UserInputForm getUserForm() {
		return userForm;
	}
	public void setUserForm(UserInputForm userForm) {
		this.userForm = userForm;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Features getFeatures() {
		return features;
	}
	public void setFeatures(Features features) {
		this.features = features;
	}
	
}
