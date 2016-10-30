package com.michal.elearning.daoServices;

import java.sql.SQLException;

import com.michal.elearning.dao.UserInputData;
import com.michal.elearning.utils.CoreDao;

public class UserInputDataDaoService implements IUserInputData {

	@Override
	public Object insertUserInputData(UserInputData userData) throws SQLException {		
		return CoreDao.getSqlMapper().insert("UserInputData.insertInputData",userData);
	}

}
