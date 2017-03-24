package com.michal.elearning.services;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.michal.elearning.dao.LearningPath;
import com.michal.elearning.daoServices.IPathInterface;
import com.michal.elearning.daoServices.PathDaoService;

@Path("/paths")
public class PathService {

	private IPathInterface pathService = new PathDaoService();
	
	@RolesAllowed("user")
    @GET
    public Response getAllPaths() 
    {       
		try {
			List<LearningPath> paths = pathService.getAllPaths();
			JSONObject pathJson = new JSONObject();  
			pathJson.put("paths", paths);
			return Response.ok(pathJson.toString()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("B³¹d ³adowania œciezek rozwoju.").build();
		}	
    }
	
}
