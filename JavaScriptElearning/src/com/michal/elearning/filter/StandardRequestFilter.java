package com.michal.elearning.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import com.michal.elearning.dao.User;
import com.michal.elearning.services.AuthService;

@Provider
public class StandardRequestFilter implements ContainerRequestFilter{
	
	 private static final Status ACCESS_DENIED = Response.Status.UNAUTHORIZED;
	 private static final Status ACCESS_FORBIDDEN = Response.Status.FORBIDDEN;

	
	@Context
    private ResourceInfo resourceInfo;
	
	@Context 
	SecurityContext securityContext;
	
	@Override
	public void filter(ContainerRequestContext containerRequest) throws IOException {
		//CORS preflight
		if(containerRequest.getMethod().equals("OPTIONS")){
			 return;
		}
		Method method = resourceInfo.getResourceMethod();
        //Access allowed for all
        if(!method.isAnnotationPresent(PermitAll.class))
        {
        	 //Access denied for all
            if(method.isAnnotationPresent(DenyAll.class))
            {
            	throw new WebApplicationException(ACCESS_FORBIDDEN);
                
            }            
            //Get the authentification passed in HTTP headers parameters
            String auth = containerRequest.getHeaderString("authorization");  
            //If the user does not have the right (does not provide any HTTP Auth)
            if(auth == null) {
            	throw new WebApplicationException(ACCESS_DENIED);
            }        
            //auth only if auth header start with Bearer
            if(auth.startsWith("Bearer ")){
            	User authentificationResult = null;
    	        // try to authenticate
    			try {
    				//validate user token
    				authentificationResult = validateToken(auth.replaceFirst("Bearer ", ""));
    			} catch (Exception e) {
    				throw new WebApplicationException(ACCESS_DENIED);
    			}	  
    			
    			//Our system refuse login and password
    	        if(authentificationResult == null) {
    	        	throw new WebApplicationException(ACCESS_DENIED);
    	        }
    			
    	        //validate user roles
    			if(method.isAnnotationPresent(RolesAllowed.class))
	            {
	                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
	                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
	                if(!isUserInRole(rolesSet, authentificationResult))
	                {
	                	throw new WebApplicationException(ACCESS_DENIED);
	                }
	            }
    			
    	        String scheme = containerRequest.getUriInfo().getBaseUri().getScheme();
    	        containerRequest.setSecurityContext(new StandardSecurityContext(authentificationResult, scheme));
            }  		
        }
	}
	
	@SuppressWarnings("unchecked")
	private User validateToken(String token){
		if(token == null){
			throw new WebApplicationException(ACCESS_DENIED);
		}
		
		Claims claims = Jwts.parser()
				   .setSigningKey(AuthService.apiKey)
				   .parseClaimsJws(token).getBody();
		//when no info about user in token
		if(claims.get("userId")==null || claims.get("userName")==null || claims.get("userRoles")==null){
			throw new WebApplicationException(ACCESS_DENIED);
		}
		//when no addional data
		if(claims.getSubject()==null || claims.getIssuer()==null || claims.getExpiration()==null){
			throw new WebApplicationException(ACCESS_DENIED);
		}
		//return logged user
		User tokenUser = new User();
		tokenUser.setId((int) claims.get("userId"));
		tokenUser.setName((String) claims.get("userName"));
		tokenUser.setRole((List<String>) claims.get("userRoles"));
		return tokenUser;		
	}
	
	private boolean isUserInRole(Set<String> rolesSet, User u) {
        if (u.getRole() != null) {
        	for(String role : u.getRole()){
        		if (rolesSet.contains(role)){
        			return true;
        		}
        	}
        }
        return false;
    }
}
