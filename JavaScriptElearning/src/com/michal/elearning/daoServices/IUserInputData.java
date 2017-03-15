package com.michal.elearning.daoServices;

import java.sql.SQLException;
import java.util.List;

import com.michal.elearning.dao.UserInLessons;
import com.michal.elearning.dao.UserInputData;

public interface IUserInputData {
	
	Object insertUserInputData(UserInputData userData) throws SQLException;
	
	List<UserInputData> getUserData(int userId) throws SQLException;

	UserInLessons getUserLesson(int lessonId, int userId) throws SQLException;

	void updateuserInLesson(UserInLessons userDataObject, UserInLessons userExisDataObject) throws SQLException;

	void insertUserInLesson(UserInLessons userDataObject) throws SQLException;

}
