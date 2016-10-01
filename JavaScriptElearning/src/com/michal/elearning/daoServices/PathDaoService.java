package com.michal.elearning.daoServices;

import java.sql.SQLException;
import java.util.List;

import com.michal.elearning.dao.LearningPath;
import com.michal.elearning.utils.CoreDao;

public class PathDaoService implements IPathInterface {

	/**
	 * Pobiera i zwraca wszystkie sciezki rozwoju
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LearningPath> getAllPaths() throws SQLException {
		List<LearningPath> paths = CoreDao.getSqlMapper().queryForList("Path.getPaths");
		return paths;
	}

}
