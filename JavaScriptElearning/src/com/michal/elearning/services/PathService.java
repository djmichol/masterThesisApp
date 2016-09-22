package com.michal.elearning.services;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.michal.elearning.utils.CoreDao;

@Path("/path")
public class PathService {

	@SuppressWarnings("unchecked")
	@RolesAllowed("user")
    @GET
    public Response getAllPaths() 
    {       
		try {
			List<Path> paths = CoreDao.getSqlMapper().queryForList("Path.getPaths");
			JSONObject pathJson = new JSONObject();  
			pathJson.put("paths", paths);
			return Response.ok(pathJson.toString()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}	
    }
	
}
