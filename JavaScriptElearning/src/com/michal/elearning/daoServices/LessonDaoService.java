package com.michal.elearning.daoServices;

import java.sql.SQLException;
import java.util.List;

import com.michal.elearning.dao.Lesson;
import com.michal.elearning.dao.LessonQuizQuestion;
import com.michal.elearning.dao.LessonTabs;
import com.michal.elearning.dao.LessonVideo;
import com.michal.elearning.utils.CoreDao;

public class LessonDaoService implements ILessonsInterface {

	@Override
	public Lesson getLessonById(int lessonId) throws SQLException {
		return (Lesson) CoreDao.getSqlMapper().queryForObject("Lesson.getLessonById", lessonId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getLessonInstructions(int lessonId) throws SQLException {		
		return CoreDao.getSqlMapper().queryForList("Lesson.getLessonInstructions",lessonId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LessonTabs> getLessonTabs(int lessonId) throws SQLException {
		return CoreDao.getSqlMapper().queryForList("Lesson.getLesonTabs",lessonId);
	}

	@Override
	public Lesson getEditorLessonById(int lessonId) throws SQLException {
		Lesson lessonResult = getLessonById(lessonId);
		lessonResult.setInstructions(getLessonInstructions(lessonId));
		lessonResult.setTabs(getLessonTabs(lessonId));
		return lessonResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lesson> getBlockLessons(int blockId) throws SQLException {
		return CoreDao.getSqlMapper().queryForList("Lesson.getLessonsForBlock",blockId);
	}

	@Override
	public LessonVideo getLessonVideo(int lessonId) throws SQLException {
		return (LessonVideo) CoreDao.getSqlMapper().queryForObject("Lesson.getLesonVideo",lessonId);
	}

	@Override
	public Lesson getVideoLessonById(int lessonId) throws SQLException {
		Lesson lessonResult = getLessonById(lessonId);
		lessonResult.setVideo(getLessonVideo(lessonId));
		return lessonResult;
	}

	@Override
	public Lesson getQuizLessonById(int lessonId) throws SQLException {
		Lesson lessonResult = getLessonById(lessonId);
		lessonResult.setQuiz(getLessonQuestion(lessonId));
		return lessonResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LessonQuizQuestion> getLessonQuestion(int lessonId) throws SQLException {
		return CoreDao.getSqlMapper().queryForList("Lesson.getLessonQuizQuestion", lessonId);
	}

}
