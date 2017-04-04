package com.michal.elearning.utils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.WebApplicationException;

import org.json.JSONObject;

import com.michal.elearning.dao.User;
import com.michal.elearning.filter.StandardRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenUtils {

	public final static String apiKey = "928617f50b35130f48dd9a09d6183636";

	private static String issueToken(User user) {
		// alg to sign token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		// date
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		// random id
		UUID idOne = UUID.randomUUID();
		// construct JWT
		JwtBuilder builder = Jwts.builder().setId(idOne.toString()).setIssuedAt(now).setSubject("auth").setIssuer("/login").claim("userId", user.getId()).claim("userName", user.getName())
				.claim("userRoles", user.getRole()).claim("userMail", user.getRole()).signWith(signatureAlgorithm, apiKey);

		return builder.compact();
	}

	/**
	 * Check if token send by user is valid
	 * 
	 * @param token
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static User validateToken(String token) {
		if (token == null) {
			throw new WebApplicationException(StandardRequestFilter.ACCESS_DENIED);
		}

		Claims claims = Jwts.parser().setSigningKey(TokenUtils.apiKey).parseClaimsJws(token).getBody();
		// when no info about user in token
		if (claims.get("userId") == null || claims.get("userName") == null || claims.get("userRoles") == null) {
			throw new WebApplicationException(StandardRequestFilter.ACCESS_DENIED);
		}
		// when no addional data
		if (claims.getSubject() == null || claims.getIssuer() == null) {
			throw new WebApplicationException(StandardRequestFilter.ACCESS_DENIED);
		}
		// return logged user
		User validUser = new User();
		validUser.setId((int) claims.get("userId"));
		validUser.setName((String) claims.get("userName"));
		validUser.setRole((List<String>) claims.get("userRoles"));
		return validUser;
	}

	/**
	 * Get JWT token with info anout user
	 * 
	 * @param user
	 * @return
	 */
	public static String getUserToken(User user) {
		String token = issueToken(user);
		JSONObject tokenJson = new JSONObject();
		tokenJson.put("token", token);
		return tokenJson.toString();
	}
}
