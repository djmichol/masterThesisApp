package com.michal.elearning.utils;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class CoreDao {

	public static SqlMapClient getSqlMapper(){
		Reader configReader;
		SqlMapClient mapClient = null;
		try {
			configReader = Resources.getResourceAsReader("/com/michal/elearning/dao/SqlMapConfig.xml");
			mapClient = SqlMapClientBuilder.buildSqlMapClient(configReader);
		} catch (IOException e) {
			e.printStackTrace();
		}	    
	    return mapClient;
	}
	
}
