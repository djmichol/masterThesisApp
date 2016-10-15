package com.michal.elearning.filter;


import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
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
import com.michal.elearning.utils.TokenUtils;

@Provider
public class StandardRequestFilter implements ContainerRequestFilter{
	
	public static final Status ACCESS_DENIED = Response.Status.UNAUTHORIZED;
	public static final Status ACCESS_FORBIDDEN = Response.Status.FORBIDDEN;
	
	@Context
    private ResourceInfo resourceInfo;
	
	@Context 
	SecurityContext securityContext;
	
	private String authHeader;
	
	@Override
	public void filter(ContainerRequestContext containerRequest) throws IOException {
		if(isCORSPreflight(containerRequest.getMethod())){
			 return;
		}
		Method method = resourceInfo.getResourceMethod();
        if(isPerrmitForAll(method))
        {
        	return;
        }
        if(isDenyForAll(method))
        {
        	throw new WebApplicationException(ACCESS_FORBIDDEN);            
        }       
        this.setAuthHeader(containerRequest.getHeaderString("authorization"));
        if(!isAuthorizationHeaderAvaliable()) {
        	throw new WebApplicationException(ACCESS_DENIED);
        }        
        if(isAuthHeaderStartWithBearer()){
        	User authentificationResult = getUserFromToken();
        	if(authentificationResult == null) {
        		throw new WebApplicationException(ACCESS_DENIED);
            }
			if(isRolesAllowedPresent(method))
            {
                if(!isRolesValid(method, authentificationResult))
                {
                	throw new WebApplicationException(ACCESS_DENIED);
                }
            }			
	        String scheme = containerRequest.getUriInfo().getBaseUri().getScheme();
	        containerRequest.setSecurityContext(new StandardSecurityContext(authentificationResult, scheme));
        }  		
	}
	
	private boolean isCORSPreflight(String method){
		if(method.equals("OPTIONS")){
			return true;
		}
		return false;
	}
	
	private boolean isPerrmitForAll(Method method){
		if(method.isAnnotationPresent(PermitAll.class)){
			return true;
		}
		return false;
	}
	
	private boolean isDenyForAll(Method method){
		if(method.isAnnotationPresent(DenyAll.class)){
			return true;
		}
		return false;
	}
	
	private boolean isAuthorizationHeaderAvaliable(){
		return getAuthHeader() == null ? false : true;
	}
	
	private boolean isAuthHeaderStartWithBearer(){
		return getAuthHeader().startsWith("Bearer ") ? true : false;
	}
	
	private User getUserFromToken(){
		User userFromToken = null;
		try {
			userFromToken = TokenUtils.validateToken(getAuthHeader().replaceFirst("Bearer ", ""));
		} catch (Exception e) {
			throw new WebApplicationException(ACCESS_DENIED);
		}	  			
        return userFromToken;
	}
	
	private boolean isRolesAllowedPresent(Method method){
		return method.isAnnotationPresent(RolesAllowed.class) ? true : false;
	}
	
	private boolean isRolesValid(Method method, User authUser){
		RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
        Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
        if(!isUserInRole(rolesSet, authUser))
        {
        	return false;
        }
        return true;
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

	public String getAuthHeader() {
		return authHeader;
	}

	public void setAuthHeader(String authHeader) {
		this.authHeader = authHeader;
	}
}
