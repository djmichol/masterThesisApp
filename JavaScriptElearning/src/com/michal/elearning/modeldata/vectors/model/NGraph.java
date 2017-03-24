package com.michal.elearning.modeldata.vectors.model;

import java.util.List;

import com.michal.elearning.dao.UserKeystrokes;

public class NGraph {

	private List<UserKeystrokes> keystrokes;
	private int[] keyList;
	
	public NGraph(List<UserKeystrokes> keystrokes, int[] keyList){
		this.keystrokes = keystrokes;
		this.keyList = keyList;
	}
	
	public List<UserKeystrokes> getKeystrokes() {
		return keystrokes;
	}

	public int[] getKeyList() {
		return keyList;
	}

	
}
