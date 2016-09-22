package com.michal.elearning.filter;

import org.glassfish.jersey.server.ResourceConfig;

public class StandardApplicationConfig extends ResourceConfig  {
	
	 public StandardApplicationConfig() 
	    {
	        packages("com.michal.elearning");
	        register(StandardRequestFilter.class);
	        register(StandardResponseFilter.class);
	       
	    }

}
