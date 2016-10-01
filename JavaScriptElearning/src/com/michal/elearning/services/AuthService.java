package com.michal.elearning.services;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.michal.elearning.dao.User;
import com.michal.elearning.daoServices.IUserInterface;
import com.michal.elearning.daoServices.UserDaoService;
import com.michal.elearning.utils.Base64Utils;
import com.michal.elearning.utils.TokenUtils;

@Path("/auth")
public class AuthService {
	
	private IUserInterface userService = new UserDaoService();	
	private static final Response NOT_ACCEPT_USRER = Response.status(Response.Status.NOT_ACCEPTABLE).entity("B³¹d logowania.").build();
	
	@GET
	@PermitAll
	@Path("/login")
	public Response validateUser(@Context HttpHeaders headers) {
		String userAuth = headers.getHeaderString("log-on-user");
		//gdy nie ma nag³ówka z info o user
		if(userAuth==null){
			return NOT_ACCEPT_USRER;
		}
		//dekodowanie nag³ówka
        String[] decodedUserInfo = Base64Utils.decode(userAuth);
        //gdy nie mamy zdekodowanego hasla lub nazwy
        if(decodedUserInfo == null || decodedUserInfo.length != 2) {
        	return NOT_ACCEPT_USRER;
        }
        //sprobuj zalogowac
        User authentificationResult = null;
		try {
			authentificationResult = userService.authentificationUser(decodedUserInfo[0], decodedUserInfo[1]);
			String token = TokenUtils.getUserToken(authentificationResult);
			return Response.ok(token).build();
		} catch (Exception e) {
			return NOT_ACCEPT_USRER;
		}	
	}
	
	@POST
	@PermitAll
	@Path("/logOn")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(String user, @Context HttpHeaders headers) throws IOException{
		if(user== null){
			return Response.status(Response.Status.NO_CONTENT).entity("Przes³ane dane s¹ puste").build();
		}
		JSONObject userJson = new JSONObject(user);
		User newUser = new User(userJson.getString("name"),userJson.getString("password"),userJson.getString("mail"));
		try {
			Object result = userService.insertUser(newUser);
			if(result!=null){
				String token = TokenUtils.getUserToken(newUser);
				return Response.ok(token).build();
			}else{
				return Response.status(Response.Status.PRECONDITION_FAILED).entity("Nie mozna dodaæ uzytkownika.").build();
			}
		} catch (SQLException e) {
			if(e.getCause().getClass().equals(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException.class)){
				return Response.status(Response.Status.PRECONDITION_FAILED).entity("Mail ju¿ istnieje w bazie danych.").build();
			}else{
				e.printStackTrace();
				return Response.status(Response.Status.PRECONDITION_FAILED).entity("Inny b³¹d.").build();				
			}
		} 		
	}
}
