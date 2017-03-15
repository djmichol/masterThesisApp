package com.michal.elearning.services;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.json.JSONObject;

import com.michal.elearning.dao.Lesson;
import com.michal.elearning.dao.LessonBlock;
import com.michal.elearning.dao.User;
import com.michal.elearning.daoServices.IBlockInterface;
import com.michal.elearning.daoServices.ILessonsInterface;
import com.michal.elearning.daoServices.LessonBlockDaoService;
import com.michal.elearning.daoServices.LessonDaoService;

@Path("/lessons")
public class LessonService {
	
	private ILessonsInterface lessonService = new LessonDaoService();
	private Response LESSON_LOAD_ERROR = Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("B³¹d pobierania lekcji.").build();

	@Context 
	SecurityContext securityContext;
	
	@RolesAllowed("user")
    @GET
    @Path("/loadBlockLessons")
    public Response getBlockLessons(@QueryParam("blockId") int blockId) 
    {       
		try {
			List<Lesson> lessons = lessonService.getBlockLessons(blockId,((User)securityContext.getUserPrincipal()).getId());
			LessonBlock lessonBlock = getLessonBlockService().getLessonBlockByID(blockId);
			JSONObject lessonBlockJson = new JSONObject(lessonBlock);
			JSONObject lessonsJson = new JSONObject();  
			lessonsJson.put("lessons", lessons);
			lessonsJson.put("lessonBlock", lessonBlockJson);
			return Response.ok(lessonsJson.toString()).build();
		} catch (Exception e) {
			return LESSON_LOAD_ERROR;
		}	
    }
	
	@RolesAllowed("user")
    @GET
    @Path("/loadLessonEditorById")
	public Response getLessonEditorById(@QueryParam("lessonId") int lessonId){
		try {
			Lesson lessonResult = lessonService.getEditorLessonById(lessonId);
			List<Lesson> lessons = lessonService.getBlockLessons(lessonResult.getBlockId(),((User)securityContext.getUserPrincipal()).getId());
			JSONObject lesson = new JSONObject(lessonResult);
			JSONObject lessonsJson = new JSONObject();  
			lessonsJson.put("lesson", lesson);
			lessonsJson.put("lessons", lessons);
			return Response.ok(lessonsJson.toString()).build();
		} catch (SQLException e) {
			return LESSON_LOAD_ERROR;
		}
	}
	
	@RolesAllowed("user")
    @GET
    @Path("/loadLessonVideoById")
	public Response getLessonVideoById(@QueryParam("lessonId") int lessonId){
		try {
			Lesson lessonResult = lessonService.getVideoLessonById(lessonId);
			List<Lesson> lessons = lessonService.getBlockLessons(lessonResult.getBlockId(),((User)securityContext.getUserPrincipal()).getId());
			JSONObject lesson = new JSONObject(lessonResult);
			JSONObject lessonsJson = new JSONObject();  
			lessonsJson.put("lesson", lesson);
			lessonsJson.put("lessons", lessons);
			return Response.ok(lessonsJson.toString()).build();
		} catch (SQLException e) {
			return LESSON_LOAD_ERROR;
		}
	}	
	
	@RolesAllowed("user")
    @GET
    @Path("/loadLessonQuizById")
	public Response getLessonQuizById(@QueryParam("lessonId") int lessonId){
		try {
			Lesson lessonResult = lessonService.getQuizLessonById(lessonId);
			List<Lesson> lessons = lessonService.getBlockLessons(lessonResult.getBlockId(),((User)securityContext.getUserPrincipal()).getId());
			JSONObject lesson = new JSONObject(lessonResult);
			JSONObject lessonsJson = new JSONObject();  
			lessonsJson.put("lesson", lesson);
			lessonsJson.put("lessons", lessons);
			return Response.ok(lessonsJson.toString()).build();
		} catch (SQLException e) {
			return LESSON_LOAD_ERROR;
		}
	}	
	
	private IBlockInterface getLessonBlockService(){
		return new LessonBlockDaoService();
	}

}
