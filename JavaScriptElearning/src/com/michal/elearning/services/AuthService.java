package com.michal.elearning.services;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import org.json.JSONObject;

import com.michal.elearning.dao.User;
import com.michal.elearning.utils.CoreDao;

@Path("/auth")
public class AuthService {
	
	public final static String apiKey="928617f50b35130f48dd9a09d6183636";
	private final static int expMili = 12000000;
	
	@GET
	@PermitAll
	@Path("/login")
	public Response validateUser(@Context HttpHeaders headers) {
		String userAuth = headers.getHeaderString("log-on-user");
		//gdy nie ma nag³ówka z info o user
		if(userAuth==null){
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("B³¹d logowania.").build();
		}
		//dekodowanie nag³ówka
        String[] lap = AuthService.decode(userAuth);
        //gdy nie mamy zdekodowanego hasla lub nazwy
        if(lap == null || lap.length != 2) {
        	return Response.status(Response.Status.NOT_ACCEPTABLE).entity("B³¹d logowania.").build();
        }
        //sprobuj zalogowac
        User authentificationResult = null;
		try {
			authentificationResult = authentification(lap[0], lap[1]);
			String token = issueToken(authentificationResult);
			JSONObject tokenJson = new JSONObject();
			tokenJson.put("token", token);
			return Response.ok(tokenJson.toString()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("B³¹d logowania.").build();
		}	
	}
	
	@POST
	@PermitAll
	@Path("/createUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(String user, @Context HttpHeaders headers) throws IOException{
		if(user== null){
			return Response.status(Response.Status.NO_CONTENT).entity("Przes³ane dane s¹ puste").build();
		}
		Object result = null;
		JSONObject userJson = new JSONObject(user);
		User newUser = new User(userJson.getString("name"),userJson.getString("password"),userJson.getString("mail"));
		try {
			result = CoreDao.getSqlMapper().insert("User.insertUser", newUser);
			if(result!=null){
				String token = issueToken(newUser);
				JSONObject tokenJson = new JSONObject();
				tokenJson.put("token", token);
				return Response.ok(tokenJson.toString()).build();
			}else{
				return Response.status(Response.Status.PRECONDITION_FAILED).entity("Nie mozna dodaæ uzytkownika.").build();
			}
		} catch (SQLException e) {
			if(e.getCause().getClass().equals(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException.class)){
				return Response.status(Response.Status.PRECONDITION_FAILED).entity("Mail ju¿ istnieje w bazie danych.").build();
			}else{
				e.printStackTrace();
				return Response.status(Response.Status.PRECONDITION_FAILED).entity("Inny b³¹d.").build();				
			}
		} 
		
	}
	
	@SuppressWarnings("unchecked")
	private User authentification(String lap1,String lap2) throws Exception{		
		Map<String, Object> params = new HashMap<>();
		params.put("mail", lap1);
		params.put("password", lap2);
		User user = (User) CoreDao.getSqlMapper().queryForObject("User.getUser", params);
		if(user==null){
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
		user.setRole(CoreDao.getSqlMapper().queryForList("User.getUserRoles",user.getId()));
		return user;
	}
	
	private String issueToken(User user){
		//alg to sign token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		//date 
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		//exp date
		long expMillis = nowMillis + AuthService.expMili;
		Date exp = new Date(expMillis);
		//random id
		UUID idOne = UUID.randomUUID();
		//construct JWT
		JwtBuilder builder = Jwts.builder().setId(idOne.toString())
				.setIssuedAt(now).setExpiration(exp).setSubject("auth")
				.setIssuer("/login").claim("userId", user.getId())
				.claim("userName", user.getName()).claim("userRoles", user.getRole())
				.claim("userMail", user.getRole()).signWith(signatureAlgorithm, AuthService.apiKey);

		return builder.compact();
	}
	
	private static String[] decode(String auth) {
		// Replacing "Basic THE_BASE_64" to "THE_BASE_64" directly
		auth = auth.replaceFirst("[B|b]asic ", "");

		// Decode the Base64 into byte[]
		byte[] decodedBytes = DatatypeConverter.parseBase64Binary(auth);

		// If the decode fails in any case
		if (decodedBytes == null || decodedBytes.length == 0) {
			return null;
		}

		return new String(decodedBytes).split(":", 2);
	}


}
