package com.michal.elearning.modeldata.vectors;

import java.util.List;
import java.util.Vector;

import com.michal.elearning.dao.UserMauseClick;

public class MauseClickVector {

	public static Vector<Integer> mauseClickVector(List<UserMauseClick> mauseClick){
		Vector<Integer> dwellTimes = new Vector<Integer>();
		if(mauseClick.size()>=2){
			for(int i=1; i<mauseClick.size();i++){
				int time1 = mauseClick.get(i-1).getTime();
				int time2 = mauseClick.get(i).getTime();
				if(time1!=0 && time2!=0){
					dwellTimes.add(time2-time1);
				}
			}
		}
		return dwellTimes;
	}
	
}
