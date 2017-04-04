package com.michal.elearning.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class StandardResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		// dodanie nag³ówków CORS
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, UPDATE, DELETE, OPTIONS");
		responseContext.getHeaders().add("Access-Control-Max-Age", "151200");
		responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, Authorization, Log-On-User");
	}

}
