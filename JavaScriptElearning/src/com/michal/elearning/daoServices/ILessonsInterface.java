package com.michal.elearning.daoServices;

import java.sql.SQLException;
import java.util.List;

import com.michal.elearning.dao.Lesson;
import com.michal.elearning.dao.LessonTabs;

public interface ILessonsInterface {
	
	Lesson getLessonById(int lessonId) throws SQLException;
	List<String> getLessonInstructions(int lessonId)throws SQLException;
	List<LessonTabs> getLessonTabs(int lessonId) throws SQLException;
	Lesson getLessonDetailsById(int lessonId) throws SQLException;
	List<Lesson> getBlockLessons(int blockId) throws SQLException;
}
