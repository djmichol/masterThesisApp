package com.michal.elearning.filter;

import java.security.Principal;
import javax.ws.rs.core.SecurityContext;
import com.michal.elearning.dao.User;

public class StandardSecurityContext implements SecurityContext {
	private User user;
	private String scheme;

	public StandardSecurityContext(User user, String scheme) {
		this.user = user;
		this.scheme = scheme;
	}

	@Override
	public Principal getUserPrincipal() {
		return this.user;
	}

	@Override
	public boolean isUserInRole(String s) {
		if (user.getRole() != null) {
			return user.getRole().contains(s);
		}
		return false;
	}

	@Override
	public boolean isSecure() {
		return "https".equals(this.scheme);
	}

	@Override
	public String getAuthenticationScheme() {
		return SecurityContext.BASIC_AUTH;
	}

}
