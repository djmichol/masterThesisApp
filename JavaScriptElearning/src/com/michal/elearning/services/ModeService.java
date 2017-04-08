package com.michal.elearning.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.michal.elearning.utils.CoreDao;

@Path("/mode")
public class ModeService {

	@GET
	@PermitAll
	public Response getMode() throws SQLException {
		int active = (int) CoreDao.getSqlMapper().queryForObject("Mode.getMode","collect");
		boolean isCollectMode = active == 1 ? true : false;
		return Response.ok(isCollectMode).build();
	}
	
	@Context 
	SecurityContext securityContext;
		
	@RolesAllowed("admin")
	@POST
	public Response setMode(String data) throws SQLException {
		boolean isCollectMode = data.equals("false") ? false : true;
		Map<String,Object> params = new HashMap<>();
		params.put("mode", "collect");
		params.put("value", isCollectMode ? 1 : 0);
		CoreDao.getSqlMapper().update("Mode.setMode",params);
		params = new HashMap<>();
		params.put("mode", "predict");
		params.put("value", isCollectMode ? 0 : 1);
		CoreDao.getSqlMapper().update("Mode.setMode",params);
		return Response.ok().build();
	}
	
}
