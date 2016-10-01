package com.michal.elearning.daoServices;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.michal.elearning.dao.User;
import com.michal.elearning.utils.CoreDao;

public class UserDaoService implements IUserInterface{

	@Override
	public User getLoggedUser(String mail, String password) throws SQLException {
		Map<String, Object> params = new HashMap<>();
		params.put("mail", mail);
		params.put("password", password);
		User loggedUser = (User) CoreDao.getSqlMapper().queryForObject("User.getUser", params);
		return loggedUser;
	}

	@Override
	public Object insertUser(User newUser) throws SQLException {
		Object result = CoreDao.getSqlMapper().insert("User.insertUser", newUser);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getUserRoles(int userId) throws SQLException {
		return CoreDao.getSqlMapper().queryForList("User.getUserRoles",userId);
	}
	
	/**
	 * Try to authenticate user
	 */
	public User authentificationUser(String mail,String password) throws SQLException{		
		User user = getLoggedUser(mail,password);
		if(user==null){
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
		user.setRole(getUserRoles(user.getId()));
		return user;
	}



}
