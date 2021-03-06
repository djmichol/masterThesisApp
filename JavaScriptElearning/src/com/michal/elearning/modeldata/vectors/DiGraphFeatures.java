package com.michal.elearning.modeldata.vectors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.michal.elearning.dao.UserKeystrokes;
import com.michal.elearning.utils.VectorsUtils;
import com.michal.elearning.wekaDataModel.DigraphsCodes;
import com.michal.elearning.wekaDataModel.NGraph;

public class DiGraphFeatures implements GraphsFeatures{

	private Vector<Integer> firstDwell;
	private Vector<Integer> secondDwell;
	private Vector<Integer> timeBetweenFirsAndSecondPress;
	private Vector<Integer> timeBetweenFirstUpAndSecondDown;
	private Vector<Integer> digraphDuration;

	public List<NGraph> prepareVector(List<UserKeystrokes> keystrokes) {
		clear();
		List<NGraph> digraphs = new ArrayList<NGraph>();

		List<int[]> list = VectorsUtils.getNgraphsList(DigraphsCodes.class);
		
		// sortowanie po czasie
		Collections.sort(keystrokes);
		boolean digraphStarted = false;
		Map<int[],List<UserKeystrokes>> digraph = new HashMap<int[],List<UserKeystrokes>>();
		if (keystrokes.size() >= 2) {
			for (int i = 0; i < keystrokes.size(); i++) {				
				if (keystrokes.get(i).getType().equals("keydown")) {
					if(!digraphStarted){						
						for(int[] object : list){
							if(object[0]==keystrokes.get(i).getCode()){
								digraphStarted = true;								
								List<UserKeystrokes> tmp = new ArrayList<UserKeystrokes>();
								tmp.add(keystrokes.get(i));
								digraph.put(object,tmp);
							}
						}
					}else{
						boolean isDigraphContionue = false;
						for (Map.Entry<int[],List<UserKeystrokes>> entry : digraph.entrySet())
						{
							if(entry.getKey()[1]==keystrokes.get(i).getCode()){
								entry.getValue().add(keystrokes.get(i));
								isDigraphContionue = true;
							}
						}
						if(!isDigraphContionue){
							digraphStarted = false;
							digraph = new HashMap<int[],List<UserKeystrokes>>();
							for(int[] object : list){
								if(object[0]==keystrokes.get(i).getCode()){
									digraphStarted = true;								
									List<UserKeystrokes> tmp = new ArrayList<UserKeystrokes>();
									tmp.add(keystrokes.get(i));
									digraph.put(object,tmp);
								}
							}
						}
					}
				} else if (keystrokes.get(i).getType().equals("keyup")) {
					if(digraphStarted){
						for (Map.Entry<int[],List<UserKeystrokes>> entry : digraph.entrySet())
						{
							entry.getValue().add(keystrokes.get(i));
							if(entry.getKey()[1]==keystrokes.get(i).getCode() && entry.getValue().size()>=3){
								digraphStarted = false;
								i--;
								digraphs.add(new NGraph(entry.getValue(), entry.getKey()));
								digraph = new HashMap<int[],List<UserKeystrokes>>();
							}
						}
					}
				}
			}
		}
		return digraphs;
	}
	
	public void calculateVectors(List<NGraph> digraphs){
		for(NGraph digraph : digraphs){
			firstDwell.add(getFirstDwell(digraph));			
			timeBetweenFirsAndSecondPress.add(getTimeBetweenFirstAndSecondPress(digraph));
			timeBetweenFirstUpAndSecondDown.add(getTimeBetweenFirstUpAndSecondDown(digraph));
			digraphDuration.add(getDigraphDuration(digraph));
			secondDwell.add(getSecondDwell(digraph));
		}
	}

	private Integer getDigraphDuration(NGraph digraph) {
		Integer dwell = 0;
		int stop = digraph.getKeystrokes().get(digraph.getKeystrokes().size()-1).getTime().intValue();
		int start = digraph.getKeystrokes().get(0).getTime().intValue();
		dwell = stop-start;
		return dwell;
	}

	private Integer getTimeBetweenFirstUpAndSecondDown(NGraph digraph) {
		Integer dwell = 0;
		int start = 0;
		int stop = 0;
		int counter = 0;
		for (UserKeystrokes key : digraph.getKeystrokes()) {
			if (key.getCode() == digraph.getKeyList()[0] && key.getType().equals("keyup")) {
				start = key.getTime().intValue();
				break;
			} 
		}
		for (UserKeystrokes key : digraph.getKeystrokes()) {
			if(digraph.getKeyList()[1]!=digraph.getKeyList()[0] || counter == 1){
				if (key.getCode() == digraph.getKeyList()[1] && key.getType().equals("keydown")){
					stop = key.getTime().intValue();
					break;
				}
			}
			else if (key.getCode() == digraph.getKeyList()[1] && key.getType().equals("keydown")){
				counter++;
			}
		}
		dwell = stop-start;
		return dwell;
	}

	private Integer getTimeBetweenFirstAndSecondPress(NGraph digraph) {
		Integer dwell = 0;
		int start = digraph.getKeystrokes().get(0).getTime().intValue();
		int stop = 0;
		int counter = 0;
		for(UserKeystrokes key : digraph.getKeystrokes()){
			if(digraph.getKeyList()[1]!=digraph.getKeyList()[0] || counter == 1){
				if(key.getCode() == digraph.getKeyList()[1] && key.getType().equals("keydown")){
					stop = key.getTime().intValue();
					break;
				}
			}else{
				counter++;
			}
		}
		dwell = stop-start;
		return dwell;
	}

	private Integer getSecondDwell(NGraph digraph) {
		Collections.sort(digraph.getKeystrokes(), new Comparator<UserKeystrokes>() {
			public int compare(UserKeystrokes o1, UserKeystrokes o2) {
				return Integer.compare(o2.getTime().intValue(), o1.getTime().intValue());
			}
		});

		Integer dwell = 0;
		int stop = digraph.getKeystrokes().get(0).getTime().intValue();
		int start = 0;
		for(UserKeystrokes key : digraph.getKeystrokes()){
			if(key.getCode() == digraph.getKeyList()[1] && key.getType().equals("keydown")){
				start = key.getTime().intValue();
				break;
			}
		}
		dwell = stop-start;
		return dwell;
	}

	private Integer getFirstDwell(NGraph digraph) {
		Integer dwell = 0;
		int start = digraph.getKeystrokes().get(0).getTime().intValue();
		int stop = 0;
		for(UserKeystrokes key : digraph.getKeystrokes()){
			if(key.getCode() == digraph.getKeyList()[0] && key.getType().equals("keyup")){
				stop = key.getTime().intValue();
				break;
			}
		}
		dwell = stop-start;
		return dwell;		
	}

	public void clear(){
		firstDwell = new Vector<>();
		secondDwell = new Vector<>();
		timeBetweenFirsAndSecondPress = new Vector<>();
		timeBetweenFirstUpAndSecondDown = new Vector<>();
		digraphDuration = new Vector<>();
	}
	
	public Vector<Integer> getFirstDwell() {
		return firstDwell;
	}

	public Vector<Integer> getSecondDwell() {
		return secondDwell;
	}

	public Vector<Integer> getTimeBetweenFirsAndSecondPress() {
		return timeBetweenFirsAndSecondPress;
	}

	public Vector<Integer> getTimeBetweenFirstUpAndSecondDown() {
		return timeBetweenFirstUpAndSecondDown;
	}

	public Vector<Integer> getDigraphDuration() {
		return digraphDuration;
	}

}
