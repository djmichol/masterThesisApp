package com.michal.elearning.utils;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class CoreDao {

	public static SqlMapClient getSqlMapper() throws IOException{
		Reader rd = Resources.getResourceAsReader("/com/michal/elearning/dao/SqlMapConfig.xml");
	    SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
	    return smc;
	}
	
}
