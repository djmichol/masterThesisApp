package com.michal.elearning.daoServices;

import java.sql.SQLException;

import com.michal.elearning.dao.UserInputData;

public interface IUserInputData {
	
	Object insertUserInputData(UserInputData userData) throws SQLException;

}
