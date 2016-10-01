package com.michal.elearning.services;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.michal.elearning.dao.Lesson;
import com.michal.elearning.dao.LessonTabs;
import com.michal.elearning.daoServices.ILessonsInterface;
import com.michal.elearning.daoServices.LessonDaoService;

@Path("/lessons")
public class LessonService {
	
	private ILessonsInterface lessonService = new LessonDaoService();
	private Response LESSON_LOAD_ERROR = Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("B³¹d pobierania lekcji.").build();

	@RolesAllowed("user")
    @GET
    @Path("/loadBlockLessons")
    public Response getBlockLessons(@QueryParam("blockId") int blockId) 
    {       
		try {
			List<Lesson> lessons = lessonService.getBlockLessons(blockId);
			JSONObject lessonsJson = new JSONObject();  
			lessonsJson.put("lessons", lessons);
			return Response.ok(lessonsJson.toString()).build();
		} catch (Exception e) {
			return LESSON_LOAD_ERROR;
		}	
    }
	
	@RolesAllowed("user")
    @GET
    @Path("/loadLessonInfo")
	public Response getLessonInfoById(@QueryParam("lessonId") int lessonId){
		try {
			List<String> lessonInstructions = lessonService.getLessonInstructions(lessonId);
			List<LessonTabs> lessonTabs= lessonService.getLessonTabs(lessonId);
			JSONObject lessonJson = new JSONObject();  
			lessonJson.put("lessonInstructions", lessonInstructions);
			lessonJson.put("lessonTabs", lessonTabs);
			return Response.ok(lessonJson.toString()).build();
		} catch (SQLException e) {
			return LESSON_LOAD_ERROR;
		}
	}
	
	@RolesAllowed("user")
    @GET
    @Path("/loadLessonById")
	public Response getLessonById(@QueryParam("lessonId") int lessonId){
		try {
			Lesson lessonResult = lessonService.getLessonDetailsById(lessonId);
			Gson gson = new Gson();
			String json = gson.toJson(lessonResult);
			return Response.ok(json).build();
		} catch (SQLException e) {
			return LESSON_LOAD_ERROR;
		}
	}

}
