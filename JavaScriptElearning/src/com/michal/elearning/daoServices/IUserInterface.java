package com.michal.elearning.daoServices;

import java.sql.SQLException;
import java.util.List;

import com.michal.elearning.dao.User;

public interface IUserInterface {
	
	User getLoggedUser(String mail, String password) throws SQLException;
	
	Object insertUser(User newUser) throws SQLException;
	
	List<String> getUserRoles(int user)  throws SQLException;
	
	User authentificationUser(String mail,String password) throws SQLException;
}
