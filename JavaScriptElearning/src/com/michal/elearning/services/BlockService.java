package com.michal.elearning.services;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.michal.elearning.dao.LessonBlock;
import com.michal.elearning.daoServices.IBlockInterface;
import com.michal.elearning.daoServices.LessonBlockDaoService;

@Path("/learningLessonBlock")
public class BlockService {
	
	private IBlockInterface blockService = new LessonBlockDaoService();
	
	@RolesAllowed("user")
    @GET
    @Path("/lessonBlock")
    public Response getLessonsBloskcForPath(@QueryParam("pathId") int pathId) 
    {       
		try {
			List<LessonBlock> lessonBlock = blockService.getLessonBlockByPathId(pathId);
			JSONObject lessonBlockJson = new JSONObject();  
			lessonBlockJson.put("lessonsBlocks", lessonBlock);
			return Response.ok(lessonBlockJson.toString()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("B��d �adowania blokow lekcji.").build();
		}	
    }

}
