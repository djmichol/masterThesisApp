package com.michal.elearning.services;

import java.sql.SQLException;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.json.JSONObject;

import com.michal.elearning.dao.User;
import com.michal.elearning.dao.UserInLessons;
import com.michal.elearning.dao.UserInputData;
import com.michal.elearning.daoServices.IUserInputData;
import com.michal.elearning.daoServices.UserInputDataDaoService;

@Path("/userInputData")
public class UserDataInput {

	private IUserInputData dataService = new UserInputDataDaoService();
	
	@Context 
	SecurityContext securityContext;
	
	@RolesAllowed("user")
    @POST
    @Path("/insertData")
    public Response insertUserData(String inputData) 
    {   
		JSONObject inpoutData = new JSONObject(inputData);
		UserInputData userDataObject = new UserInputData();
		userDataObject.setKeyStrokes(inpoutData.getJSONArray("keyStroke").toString().getBytes());
		userDataObject.setMouseMove(inpoutData.getJSONArray("mauseMove").toString().getBytes());
		userDataObject.setMouseClicks(inpoutData.getJSONArray("mauseClick").toString().getBytes());
		userDataObject.setForm(inpoutData.getJSONObject("form").toString().getBytes());
		userDataObject.setUsrId(((User)securityContext.getUserPrincipal()).getId());
		userDataObject.setLessonId(inpoutData.getInt("lessonId"));
		
		try {
			dataService.insertUserInputData(userDataObject);
			return Response.ok().build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Blad dodawania danych .").build();
    }
	
	@RolesAllowed("user")
    @POST
    @Path("/insertPassedLesson")
    public Response insertPassedLesson(String inputData) 
    {   		
		JSONObject inpoutData = new JSONObject(inputData);
		UserInLessons userDataObject = new UserInLessons();
		userDataObject.setKeyStrokes(inpoutData.getJSONArray("keyStroke").toString().getBytes());
		userDataObject.setMouseMove(inpoutData.getJSONArray("mauseMove").toString().getBytes());
		userDataObject.setMouseClicks(inpoutData.getJSONArray("mauseClick").toString().getBytes());
		boolean passed = inpoutData.getBoolean("passed");
		userDataObject.setPassed(passed ? 1 : 0);
		userDataObject.setUserId(((User)securityContext.getUserPrincipal()).getId());
		userDataObject.setLessonId(inpoutData.getInt("lessonId"));
		
		try {
			UserInLessons userExisDataObject = dataService.getUserLesson(userDataObject.getLessonId(), userDataObject.getUserId());		
			if(userExisDataObject==null){
				dataService.insertUserInLesson(userDataObject);
			}else{
				dataService.updateuserInLesson(userDataObject,userExisDataObject);
			}
		} catch (SQLException e) {
			Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Blad dodawania danych.").build();
			e.printStackTrace();
		}		
		return Response.ok().build();
    }
	
}
