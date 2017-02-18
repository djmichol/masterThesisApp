package com.michal.elearning.daoServices;

import java.sql.SQLException;
import java.util.List;

import com.michal.elearning.dao.ModelData;
import com.michal.elearning.utils.CoreDao;

public class ModelDaoService {

	public Object insertUserModel(ModelData userModel) throws SQLException {		
		return CoreDao.getSqlMapper().insert("Model.insertModel",userModel);
	}
	
	@SuppressWarnings("unchecked")
	public List<ModelData> getUserTrainedModels(int userId) throws SQLException{
		return CoreDao.getSqlMapper().queryForList("Model.getModel", userId);
	}
	
	public void deleteUserModels(int userId) throws SQLException{
		CoreDao.getSqlMapper().delete("Model.deleteUserModels",userId);
	}
}
