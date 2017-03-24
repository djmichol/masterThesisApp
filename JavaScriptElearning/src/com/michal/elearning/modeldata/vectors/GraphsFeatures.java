package com.michal.elearning.modeldata.vectors;

import java.util.List;

import com.michal.elearning.dao.UserKeystrokes;
import com.michal.elearning.modeldata.vectors.model.NGraph;

public interface GraphsFeatures {

	void clear();
	List<NGraph> prepareVector(List<UserKeystrokes> keystrokes);
	void calculateVectors(List<NGraph> digraphs);
	
}
