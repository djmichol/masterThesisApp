package com.michal.elearning.daoServices;

import java.sql.SQLException;
import java.util.List;

import com.michal.elearning.dao.UserInputData;
import com.michal.elearning.utils.CoreDao;

public class UserInputDataDaoService implements IUserInputData {

	@Override
	public Object insertUserInputData(UserInputData userData) throws SQLException {		
		return CoreDao.getSqlMapper().insert("UserInputData.insertInputData",userData);
	}

	@Override
	public List<UserInputData> getUserData(int userId) throws SQLException {
		List<UserInputData> result = CoreDao.getSqlMapper().queryForList("UserInputData.getUserInputData",userId);
		return result;
	}

}
