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
import com.michal.elearning.wekaDataModel.JavaScriptKeyWordsCodes;
import com.michal.elearning.wekaDataModel.NGraph;

public class NGraphsFeatures implements GraphsFeatures{

	private Vector<Integer> pressToPress;
	private Vector<Integer> keyDwell;
	private Vector<Integer> wordDuration;
	
	public void clear(){
		pressToPress = new Vector<>();
		keyDwell = new Vector<>();
		wordDuration = new Vector<>();
	}
	
	public List<NGraph> prepareVector(List<UserKeystrokes> keystrokes) {
		clear();
		Collections.sort(keystrokes);
		
		List<NGraph> nGraphs = new ArrayList<NGraph>();
		List<int[]> list = VectorsUtils.getNgraphsList(JavaScriptKeyWordsCodes.class);		
		boolean graphStarted = false;
		Map<int[],List<UserKeystrokes>> nGraph = new HashMap<int[],List<UserKeystrokes>>();
		int index = 0;
		if (keystrokes.size() >= 2) {
			for (int i = 0; i < keystrokes.size(); i++) {			
				try{
					if (keystrokes.get(i).getType().equals("keydown")) {
						if(!graphStarted){						
							for(int[] object : list){
								if(object[0]==keystrokes.get(i).getCode()){
									graphStarted = true;								
									List<UserKeystrokes> tmp = new ArrayList<UserKeystrokes>();
									tmp.add(keystrokes.get(i));
									nGraph.put(object,tmp);
								}
							}
						}else{
							boolean isNGraphContionue = false;
							index++;
							for (Map.Entry<int[],List<UserKeystrokes>> entry : nGraph.entrySet())
							{
								
								if(entry.getKey()[index]==keystrokes.get(i).getCode()){
									entry.getValue().add(keystrokes.get(i));								
									isNGraphContionue = true;
								}
							}
							if(!isNGraphContionue){
								graphStarted = false;
								if(index>1){
									i--;
								}
								index = 0;
								nGraph = new HashMap<int[],List<UserKeystrokes>>();
								for(int[] object : list){
									if(object[0]==keystrokes.get(i).getCode()){
										graphStarted = true;								
										List<UserKeystrokes> tmp = new ArrayList<UserKeystrokes>();
										tmp.add(keystrokes.get(i));
										nGraph.put(object,tmp);
									}
								}
							}
						}
					} else if (keystrokes.get(i).getType().equals("keyup")) {
						if(graphStarted){
							for (Map.Entry<int[],List<UserKeystrokes>> entry : nGraph.entrySet())
							{
								entry.getValue().add(keystrokes.get(i));
								int lastElement = entry.getKey().length-1;
								if(index >= lastElement){
									if(entry.getKey()[index]==keystrokes.get(i).getCode() && entry.getValue().size()>=3){
										index = 0;
										i--;
										graphStarted = false;
										nGraphs.add(new NGraph(entry.getValue(), entry.getKey()));
										nGraph = new HashMap<int[],List<UserKeystrokes>>();
									}
								}
							}
						}
					}
				}catch (Exception e) {
					index = 0;
					i--;
					graphStarted = false;
					nGraph = new HashMap<int[],List<UserKeystrokes>>();
				}
			}
		}
		return nGraphs;
	}
	
	public  void calculateVectors(List<NGraph> digraphs){
		for(NGraph digraph : digraphs){
			pressToPress.add(getDwell(digraph));			
			keyDwell.add(getTimeFlight(digraph));
			wordDuration.add(getDigraphDuration(digraph));
		}
	}
	
	private Integer getTimeFlight(NGraph digraph) {
		Vector<Integer> dwellMean= new Vector<>();
		int start = 0;
		int stop = 0;
		boolean isAdded = false;
		for (UserKeystrokes key : digraph.getKeystrokes()) {
			if (key.getType().equals("keydown")) {
				if(!isAdded){
					isAdded = true;
					start = key.getTime().intValue();
				}else{
					stop = key.getTime().intValue();
					dwellMean.add(stop-start);
					start = stop;
					stop = 0;
					isAdded = false;
				}
			} 
		}
		if(dwellMean.size()==0){
			return 0;
		}
		return (int)MathHelperUtils.calculateMean(dwellMean);
	}

	private Integer getDwell(NGraph digraph) {
		Vector<Integer> dwellMean= new Vector<>();
		int start = 0;
		int stop = 0;
		int keyCode = 0;
		for (UserKeystrokes key : digraph.getKeystrokes()) {
			if (key.getType().equals("keydown")) {
				start = key.getTime().intValue();
				keyCode = key.getCode();
			} else if (key.getType().equals("keyup")) {
				if(key.getCode()==keyCode){
					stop = key.getTime().intValue();
					dwellMean.add(stop-start);
					keyCode = 0;
				}
			}
		}
		if(dwellMean.size()==0){
			return 0;
		}
		return (int)MathHelperUtils.calculateMean(dwellMean);
	}

	private Integer getDigraphDuration(NGraph digraph) {
		Integer dwell = 0;
		int stop = digraph.getKeystrokes().get(digraph.getKeystrokes().size()-1).getTime().intValue();
		int start = digraph.getKeystrokes().get(0).getTime().intValue();
		dwell = stop-start;
		return dwell;
	}
	
	public Vector<Integer> getPressToPress() {
		return pressToPress;
	}
	public Vector<Integer> getKeyDwell() {
		return keyDwell;
	}
	public Vector<Integer> getWordDuration() {
		return wordDuration;
	}
	
}
