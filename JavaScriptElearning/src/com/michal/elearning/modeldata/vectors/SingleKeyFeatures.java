package com.michal.elearning.modeldata.vectors;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import com.michal.elearning.dao.UserKeystrokes;
import com.michal.elearning.modeldata.vectors.model.KeyCodes;

public class SingleKeyFeatures {

	private int tabFreq;
	private int spaceFreq;
	private int enterFreq;
	private int delFreq;
	private int backspaceFreq;
	private double writeSpeed;
	private Vector<Integer> singleKeyDwellTime;
	
	public void prepareFreqMember(List<UserKeystrokes> keystrokes) {
		clear();
		Collections.sort(keystrokes);

		writeSpeed = getWriteSpeed(keystrokes);
		
		Vector<Integer> dwellTimes = new Vector<Integer>();
		int keyCode = 0;
		int time1 = 0;
		int time2 = 0;
		
		if (keystrokes.size() >= 2) {
			for (int i = 0; i < keystrokes.size(); i++){
				if (keystrokes.get(i).getType().equals("keydown")) {
					if (keystrokes.get(i).getCode() == KeyCodes.BACK_SPACE) {
						backspaceFreq++;
					} else if (keystrokes.get(i).getCode() == KeyCodes.DELETE) {
						delFreq++;
					} else if (keystrokes.get(i).getCode() == KeyCodes.ENTER) {
						enterFreq++;
					} else if (keystrokes.get(i).getCode() == KeyCodes.SPACE) {
						spaceFreq++;
					} else if (keystrokes.get(i).getCode() == KeyCodes.TAB) {
						tabFreq++;
					}
					if(keystrokes.get(i).getCode()>=48 && keystrokes.get(i).getCode()<=111){
						keyCode = keystrokes.get(i).getCode();
						time1 = keystrokes.get(i).getTime();
					}
				}else if(keystrokes.get(i).getType().equals("keyup")){
					if(keystrokes.get(i).getCode()==keyCode){
						time2 = keystrokes.get(i).getTime();
						dwellTimes.add(time2-time1);
					}
				}
			}
			singleKeyDwellTime = new Vector<>();
			singleKeyDwellTime.addAll(dwellTimes);
		}
	}

	private double getWriteSpeed(List<UserKeystrokes> keystrokes) {
		double result = 0.0;
		if(keystrokes.size()>2){
			int start = keystrokes.get(0).getTime();
			int stop =  keystrokes.get(keystrokes.size()-1).getTime();
			double time = (double) stop-start ;
			double allTimeInMin = (time/1000.0)/60.0;
			int size = keystrokes.size();
			result =  size/allTimeInMin;
		}
		return result;
	}

	private void clear() {
		tabFreq = 0;
		spaceFreq = 0;
		enterFreq = 0;
		delFreq = 0;
		backspaceFreq = 0;
	}

	public int getTabFreq() {
		return tabFreq;
	}

	public int getSpaceFreq() {
		return spaceFreq;
	}

	public int getEnterFreq() {
		return enterFreq;
	}

	public int getDelFreq() {
		return delFreq;
	}

	public int getBackspaceFreq() {
		return backspaceFreq;
	}

	public double getWriteSpeed() {
		return writeSpeed;
	}

	public Vector<Integer> getSingleKeyDwellTime() {
		return singleKeyDwellTime;
	}
}
