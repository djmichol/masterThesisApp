package com.michal.elearning.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.michal.elearning.dao.Lesson;
import com.michal.elearning.dao.LessonTabs;
import com.michal.elearning.utils.CoreDao;

@Path("/lessons")
public class LessonService {
	
	@SuppressWarnings("unchecked")
	@RolesAllowed("user")
    @GET
    @Path("/loadPathLessons")
    public Response getPathLessons(@QueryParam("pathId") int pathId) 
    {       
		try {
			List<Lesson> lessons = CoreDao.getSqlMapper().queryForList("Lesson.getLessonsForPath",pathId);
			JSONObject lessonsJson = new JSONObject();  
			lessonsJson.put("lessons", lessons);
			return Response.ok(lessonsJson.toString()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("B³¹d pobierania lekcji.").build();
		}	
    }
	
	@SuppressWarnings("unchecked")
	@RolesAllowed("user")
    @GET
    @Path("/loadLessonInfo")
	public Response getLessonById(@QueryParam("lessonId") int lessonId){
		try {
			List<String> lessonInstructions = CoreDao.getSqlMapper().queryForList("Lesson.getLessonInstructions",lessonId);
			List<LessonTabs> lessonTabs= CoreDao.getSqlMapper().queryForList("Lesson.getLesonTabs",lessonId);
			JSONObject lessonJson = new JSONObject();  
			lessonJson.put("lessonInstructions", lessonInstructions);
			lessonJson.put("lessonTabs", lessonTabs);
			return Response.ok(lessonJson.toString()).build();
		} catch (SQLException | IOException e) {
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("B³¹d pobierania lekcji.").build();
		}
	}

}
