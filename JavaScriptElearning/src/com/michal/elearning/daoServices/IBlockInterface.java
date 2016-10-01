package com.michal.elearning.daoServices;

import java.sql.SQLException;
import java.util.List;

import com.michal.elearning.dao.LessonBlock;

public interface IBlockInterface {

	List<LessonBlock> getLessonBlockByPathId(int pathId) throws SQLException;
}
