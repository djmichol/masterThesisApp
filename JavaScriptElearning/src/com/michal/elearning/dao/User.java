package com.michal.elearning.dao;

import java.security.Principal;
import java.util.List;

public class User implements Principal{

	private int id;
	private String name;
	private String password;
	private String mail;
	private List<String> role;
	
	public User(){
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRole() {
		return role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (!User.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    final User other = (User) obj;
	    if (!this.mail.equals(other.mail)) {
	        return false;
	    }
	    return true;
	}
}
