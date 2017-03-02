package com.michal.elearning.modeldata.vectors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.michal.elearning.dao.UserKeystrokes;
import com.michal.elearning.utils.VectorsUtils;

public class OtherWordsGraphVectors {

	private Vector<Integer> pressToPress;
	private Vector<Integer> keyDwell;
	private Vector<Integer> wordDuration;
	
	public List<NGraph> prepareVector(List<UserKeystrokes> keystrokes) {
		clear();
		List<NGraph> digraphs = new ArrayList<NGraph>();

		List<int[]> list = VectorsUtils.getDigraphsList(JavaScriptKeyWords.class);
		
		// sortowanie po czasie
		Collections.sort(keystrokes);
		boolean graphStarted = false;
		Map<int[],List<UserKeystrokes>> digraph = new HashMap<int[],List<UserKeystrokes>>();
		int index = 0;
		if (keystrokes.size() >= 2) {
			for (int i = 0; i < keystrokes.size(); i++) {				
				if (keystrokes.get(i).getType().equals("keydown")) {
					if(!graphStarted){						
						for(int[] object : list){
							if(object[0]==keystrokes.get(i).getCode()){
								graphStarted = true;								
								List<UserKeystrokes> tmp = new ArrayList<UserKeystrokes>();
								tmp.add(keystrokes.get(i));
								digraph.put(object,tmp);
							}
						}
					}else{
						boolean isDigraphContionue = false;
						index++;
						for (Map.Entry<int[],List<UserKeystrokes>> entry : digraph.entrySet())
						{
							
							if(entry.getKey()[index]==keystrokes.get(i).getCode()){
								entry.getValue().add(keystrokes.get(i));								
								isDigraphContionue = true;
							}
						}
						if(!isDigraphContionue){
							graphStarted = false;
							if(index>1){
								i--;
							}
							index = 0;
							digraph = new HashMap<int[],List<UserKeystrokes>>();
							for(int[] object : list){
								if(object[0]==keystrokes.get(i).getCode()){
									graphStarted = true;								
									List<UserKeystrokes> tmp = new ArrayList<UserKeystrokes>();
									tmp.add(keystrokes.get(i));
									digraph.put(object,tmp);
								}
							}
						}
					}
				} else if (keystrokes.get(i).getType().equals("keyup")) {
					if(graphStarted){
						for (Map.Entry<int[],List<UserKeystrokes>> entry : digraph.entrySet())
						{
							entry.getValue().add(keystrokes.get(i));
							int lastElement = entry.getKey().length-1;
							if(index >= lastElement){
								if(entry.getKey()[index]==keystrokes.get(i).getCode() && entry.getValue().size()>=3){
									index = 0;
									i--;
									graphStarted = false;
									digraphs.add(new NGraph(entry.getValue(), entry.getKey()));
									digraph = new HashMap<int[],List<UserKeystrokes>>();
								}
							}
						}
					}
				}
			}
		}
		return digraphs;
	}	
	
	private void clear(){
		pressToPress = new Vector<>();
		keyDwell = new Vector<>();
		wordDuration = new Vector<>();
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
