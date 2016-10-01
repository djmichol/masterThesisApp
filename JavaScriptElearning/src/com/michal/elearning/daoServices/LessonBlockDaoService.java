package com.michal.elearning.daoServices;

import java.sql.SQLException;
import java.util.List;

import com.michal.elearning.dao.LessonBlock;
import com.michal.elearning.utils.CoreDao;

public class LessonBlockDaoService implements IBlockInterface {

	@SuppressWarnings("unchecked")
	@Override
	public List<LessonBlock> getLessonBlockByPathId(int pathId) throws SQLException {
		return CoreDao.getSqlMapper().queryForList("LessonBlock.getLessonBlockByPathId",pathId);
	}

}
