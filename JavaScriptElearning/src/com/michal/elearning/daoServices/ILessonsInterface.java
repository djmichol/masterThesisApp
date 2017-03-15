package com.michal.elearning.daoServices;

import java.sql.SQLException;
import java.util.List;

import com.michal.elearning.dao.Lesson;
import com.michal.elearning.dao.LessonQuizQuestion;
import com.michal.elearning.dao.LessonTabs;
import com.michal.elearning.dao.LessonVideo;

public interface ILessonsInterface {
	
	Lesson getLessonById(int lessonId) throws SQLException;
	List<String> getLessonInstructions(int lessonId)throws SQLException;
	List<LessonTabs> getLessonTabs(int lessonId) throws SQLException;
	Lesson getEditorLessonById(int lessonId) throws SQLException;
	Lesson getVideoLessonById(int lessonId) throws SQLException;
	Lesson getQuizLessonById(int lessonId) throws SQLException;
	List<Lesson> getBlockLessons(int blockId, int userId) throws SQLException;
	LessonVideo getLessonVideo(int lessonId) throws SQLException;
	List<LessonQuizQuestion> getLessonQuestion(int lessonId) throws SQLException;
}
