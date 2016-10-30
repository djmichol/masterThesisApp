package com.michal.elearning.dao;

public class UserInputData {
	
	private int id;
	private int usrId;
	private byte[] keyStrokes;
	private byte[] mouseMove;
	private byte[] mouseClicks;
	private byte[] form;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUsrId() {
		return usrId;
	}
	public void setUsrId(int usrId) {
		this.usrId = usrId;
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
	public byte[] getForm() {
		return form;
	}
	public void setForm(byte[] form) {
		this.form = form;
	}

}
