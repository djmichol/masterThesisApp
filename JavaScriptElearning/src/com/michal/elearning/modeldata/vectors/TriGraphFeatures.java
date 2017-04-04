package com.michal.elearning.modeldata.vectors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.michal.elearning.dao.UserKeystrokes;
import com.michal.elearning.machineLearning.MathHelperUtils;
import com.michal.elearning.utils.VectorsUtils;
import com.michal.elearning.wekaDataModel.NGraph;
import com.michal.elearning.wekaDataModel.TriGraphsCodes;

public class TriGraphFeatures implements GraphsFeatures{

	private Vector<Integer> dwellFirst;
	private Vector<Integer> dwellSecond;
	private Vector<Integer> dwellThird;
	private Vector<Integer> pressFirstSec;
	private Vector<Integer> pressSecThird;
	private Vector<Integer> flightFirstSec;
	private Vector<Integer> flightSecThird;
	private Vector<Integer> pressToPress;
	private Vector<Integer> keyDwell;
	private Vector<Integer> wordDuration;
	
	public void clear(){
		pressToPress = new Vector<>();
		keyDwell = new Vector<>();
		wordDuration = new Vector<>();
		dwellFirst = new Vector<>();
		dwellSecond = new Vector<>();
		dwellThird = new Vector<>();
		pressFirstSec = new Vector<>();
		pressSecThird = new Vector<>();
		flightFirstSec = new Vector<>();
		flightSecThird = new Vector<>();
	}
	
	public List<NGraph> prepareVector(List<UserKeystrokes> keystrokes) {
		clear();
		Collections.sort(keystrokes);

		List<NGraph> ngraphs = new ArrayList<NGraph>();
		List<int[]> list = VectorsUtils.getNgraphsList(TriGraphsCodes.class);
		Map<int[], List<UserKeystrokes>> trigraph = new HashMap<int[], List<UserKeystrokes>>();
		boolean graphStarted = false;
		int index = 0;

		if (keystrokes.size() >= 3) {
			for (int i = 0; i < keystrokes.size(); i++) {
				try {
					if (keystrokes.get(i).getType().equals("keydown")) {
						if (!graphStarted) {
							for (int[] object : list) {
								if (object[0] == keystrokes.get(i).getCode()) {
									graphStarted = true;
									List<UserKeystrokes> tmp = new ArrayList<UserKeystrokes>();
									tmp.add(keystrokes.get(i));
									trigraph.put(object, tmp);
								}
							}
						} else {
							boolean isGraphContionue = false;
							index++;
							for (Map.Entry<int[], List<UserKeystrokes>> entry : trigraph.entrySet()) {
								if (entry.getKey()[index] == keystrokes.get(i).getCode()) {
									entry.getValue().add(keystrokes.get(i));
									isGraphContionue = true;
								}
							}
							if (!isGraphContionue) {
								graphStarted = false;
								if (index > 1) {
									i--;
								}
								index = 0;
								trigraph = new HashMap<int[], List<UserKeystrokes>>();
								for (int[] object : list) {
									if (object[0] == keystrokes.get(i).getCode()) {
										graphStarted = true;
										List<UserKeystrokes> tmp = new ArrayList<UserKeystrokes>();
										tmp.add(keystrokes.get(i));
										trigraph.put(object, tmp);
									}
								}
							}
						}
					} else if (keystrokes.get(i).getType().equals("keyup")) {
						if (graphStarted) {
							for (Map.Entry<int[], List<UserKeystrokes>> entry : trigraph.entrySet()) {
								entry.getValue().add(keystrokes.get(i));
								int lastElement = entry.getKey().length - 1;
								if (index >= lastElement) {
									if (entry.getKey()[index] == keystrokes.get(i).getCode() && entry.getValue().size() >= 6) {
										index = 0;
										i--;
										graphStarted = false;
										ngraphs.add(new NGraph(entry.getValue(), entry.getKey()));
										trigraph = new HashMap<int[], List<UserKeystrokes>>();
									}
								}
							}
						}
					}
				} catch (Exception e) {
					index = 0;
					i--;
					graphStarted = false;
					trigraph = new HashMap<int[], List<UserKeystrokes>>();
				}
			}
		}
		return ngraphs;
	}
	
	public  void calculateVectors(List<NGraph> trigraphs){
		for(NGraph trigraph : trigraphs){
			setPressToPressTime(trigraph);		
			setMeanDwell(trigraph);
			setTrigraphDuration(trigraph);
			setDwell(trigraph);
			setPressToPress(trigraph);
			setFlight(trigraph);
		}
	}
	
	private void setPressToPressTime(NGraph digraph) {
		Vector<Integer> dwellMean= new Vector<>();
		int start = 0;
		int stop = 0;
		int keyCode = 0;
		for (UserKeystrokes key : digraph.getKeystrokes()) {
			if (key.getType().equals("keydown")) {
				start = key.getTime();
				keyCode = key.getCode();
			} else if (key.getType().equals("keyup")) {
				if(key.getCode()==keyCode){
					stop = key.getTime();
					dwellMean.add(stop-start);
					keyCode = 0;
				}
			}
		}
		if(dwellMean.size()!=0){
			pressToPress.add(MathHelperUtils.calculateMean(dwellMean));
		}
	}
	
	private void setMeanDwell(NGraph triGraph) {
		Vector<Integer> dwellMean= new Vector<>();
		int start = 0;
		int stop = 0;
		boolean isAdded = false;
		for (UserKeystrokes key : triGraph.getKeystrokes()) {
			if (key.getType().equals("keydown")) {
				if(!isAdded){
					isAdded = true;
					start = key.getTime();
				}else{
					stop = key.getTime();
					dwellMean.add(stop-start);
					start = stop;
					stop = 0;
					isAdded = false;
				}
			} 
		}
		if(dwellMean.size()!=0){
			keyDwell.add(MathHelperUtils.calculateMean(dwellMean));
		}
	}
	
	private void setTrigraphDuration(NGraph triGraph) {
		Integer dwell = 0;
		int stop = triGraph.getKeystrokes().get(triGraph.getKeystrokes().size()-1).getTime();
		int start = triGraph.getKeystrokes().get(0).getTime();
		dwell = stop-start;
		wordDuration.add(dwell);
	}
	
	private void setDwell(NGraph trigraph) {
		Vector<Integer> dwellMean= new Vector<>();
		int start = 0;
		int stop = 0;
		int keyCode = 0;
		for (UserKeystrokes key : trigraph.getKeystrokes()) {
			if (key.getType().equals("keydown")) {
				start = key.getTime();
				keyCode = key.getCode();
			} else if (key.getType().equals("keyup")) {
				if(key.getCode()==keyCode){
					stop = key.getTime();
					dwellMean.add(stop-start);
					keyCode = 0;
				}
			}
		}
		if(dwellMean.size()==3){
			dwellFirst.add(dwellMean.get(0));
			dwellSecond.add(dwellMean.get(1));
			dwellThird.add(dwellMean.get(2));
		}
	}
	
	private void setPressToPress(NGraph trigraph) {
		int start = 0;
		int stop = 0;
		for (UserKeystrokes key : trigraph.getKeystrokes()) {
			if(key.getCode()==trigraph.getKeyList()[0] && key.getType().equals("keydown")){
				start = key.getTime();
			}else if(key.getCode()==trigraph.getKeyList()[1] && key.getType().equals("keydown")){
				stop = key.getTime();
				pressFirstSec.add(stop-start);
				start = stop;
				stop = 0;				
			}else if(key.getCode()==trigraph.getKeyList()[2] && key.getType().equals("keydown")){
				stop = key.getTime();
				pressSecThird.add(stop-start);
			}
		}
	}
	
	private void setFlight(NGraph trigraph) {
		int start = 0;
		int stop = 0;
		for (UserKeystrokes key : trigraph.getKeystrokes()) {
			if(key.getCode()==trigraph.getKeyList()[0] && key.getType().equals("keyup")){
				start = key.getTime();
			}else if(key.getCode()==trigraph.getKeyList()[1] && key.getType().equals("keydown")){
				stop = key.getTime();
				flightFirstSec.add(stop-start);
				stop = 0;
				start = 0;
			}else if(key.getCode()==trigraph.getKeyList()[1] && key.getType().equals("keyup")){
				start = key.getTime();
			}else if(key.getCode()==trigraph.getKeyList()[2] && key.getType().equals("keydown")){
				stop = key.getTime();
				flightSecThird.add(stop-start);
				stop = 0;
				start = 0;
			}
		}
	}
	
	/*
	 * Gettery
	 */	
	public Vector<Integer> getPressToPress() {
		return pressToPress;
	}
	public Vector<Integer> getKeyDwell() {
		return keyDwell;
	}
	public Vector<Integer> getWordDuration() {
		return wordDuration;
	}

	public Vector<Integer> getDwellFirst() {
		return dwellFirst;
	}

	public Vector<Integer> getDwellSecond() {
		return dwellSecond;
	}

	public Vector<Integer> getDwellThird() {
		return dwellThird;
	}

	public Vector<Integer> getPressFirstSec() {
		return pressFirstSec;
	}

	public Vector<Integer> getPressSecThird() {
		return pressSecThird;
	}

	public Vector<Integer> getFlightFirstSec() {
		return flightFirstSec;
	}

	public Vector<Integer> getFlightSecThird() {
		return flightSecThird;
	}
	
}
