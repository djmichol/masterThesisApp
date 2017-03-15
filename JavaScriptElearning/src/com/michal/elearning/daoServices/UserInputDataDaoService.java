package com.michal.elearning.daoServices;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.michal.elearning.dao.UserInLessons;
import com.michal.elearning.dao.UserInputData;
import com.michal.elearning.utils.CoreDao;

public class UserInputDataDaoService implements IUserInputData {

	@Override
	public Object insertUserInputData(UserInputData userData) throws SQLException {		
		return CoreDao.getSqlMapper().insert("UserInputData.insertInputData",userData);
	}

	@Override
	public List<UserInputData> getUserData(int userId) throws SQLException {
		@SuppressWarnings("unchecked")
		List<UserInputData> result = CoreDao.getSqlMapper().queryForList("UserInputData.getUserInputData",userId);
		return result;
	}

	@Override
	public UserInLessons getUserLesson(int lessonId, int userId) throws SQLException{
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("lessonId", lessonId);
		return (UserInLessons) CoreDao.getSqlMapper().queryForObject("UserInputData.getUserInLessonData",params);
	}

	@Override
	public void updateuserInLesson(UserInLessons userDataObject, UserInLessons userExisDataObject) throws SQLException {
		if(userExisDataObject.isPassed()==0){
			int submitcount = userExisDataObject.getSubmitCount()+1;
			userExisDataObject.setSubmitCount(submitcount);
			userExisDataObject.setPassed(userDataObject.isPassed());
			userExisDataObject.setKeyStrokes(userDataObject.getKeyStrokes());
			userExisDataObject.setMouseClicks(userDataObject.getMouseClicks());
			userExisDataObject.setMouseMove(userDataObject.getMouseMove());
			CoreDao.getSqlMapper().update("UserInputData.updateUserInLesson",userExisDataObject);
		}		
	}

	@Override
	public void insertUserInLesson(UserInLessons userDataObject) throws SQLException {
		userDataObject.setSubmitCount(1);
		CoreDao.getSqlMapper().insert("UserInputData.insertUserInLessonInputData", userDataObject);		
	}

}
