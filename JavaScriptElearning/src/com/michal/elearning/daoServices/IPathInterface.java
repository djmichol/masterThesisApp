package com.michal.elearning.daoServices;

import java.sql.SQLException;
import java.util.List;

import com.michal.elearning.dao.LearningPath;

public interface IPathInterface {

	List<LearningPath> getAllPaths() throws SQLException;	
	LearningPath getPathById(int pathId) throws SQLException;
	
}
