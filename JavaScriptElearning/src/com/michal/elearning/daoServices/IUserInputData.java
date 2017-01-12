package com.michal.elearning.daoServices;

import java.sql.SQLException;
import java.util.List;

import com.michal.elearning.dao.UserInputData;

public interface IUserInputData {
	
	Object insertUserInputData(UserInputData userData) throws SQLException;
	
	List<UserInputData> getUserData(int userId) throws SQLException;

}
