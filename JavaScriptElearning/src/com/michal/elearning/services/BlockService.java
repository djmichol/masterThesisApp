package com.michal.elearning.services;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.michal.elearning.dao.LearningPath;
import com.michal.elearning.dao.LessonBlock;
import com.michal.elearning.daoServices.IBlockInterface;
import com.michal.elearning.daoServices.IPathInterface;
import com.michal.elearning.daoServices.LessonBlockDaoService;
import com.michal.elearning.daoServices.PathDaoService;

@Path("/lessonBlock")
public class BlockService {
	
	private IBlockInterface blockService = new LessonBlockDaoService();
	private IPathInterface pathService = new PathDaoService();
	
	@RolesAllowed("user")
    @GET
    public Response getLessonsBloskcForPath(@QueryParam("pathId") int pathId) 
    {       
		try {
			List<LessonBlock> lessonBlock = blockService.getLessonBlockByPathId(pathId);
			LearningPath pathInfo = pathService.getPathById(pathId);
			JSONObject path = new JSONObject(pathInfo);
			JSONObject lessonBlockJson = new JSONObject();  
			lessonBlockJson.put("lessonsBlocks", lessonBlock);
			lessonBlockJson.put("pathInfo",path);
			return Response.ok(lessonBlockJson.toString()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("B³¹d ³adowania blokow lekcji.").build();
		}	
    }
}
