package br.cefetmg.vitor.sappserver.security;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.cefetmg.vitor.sappserver.dto.AuthUserDTO;
import br.cefetmg.vitor.sappserver.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {
	
	private static final Logger LOGGER = Logger.getLogger(TokenAuthenticationService.class.toString());
	
	final public static String SEPARATOR = ";|;";
	
	private static final long EXPIRATION_TIME = 86400000;
	private static final String SECRET = "Secret";
	private static final String TOKEN_PREFIX = "token:";
	private static final String HEADER_STRING = "X-Auth-Token";

	public static class Token implements Serializable {
		public String token;
	}
	
	public static String generateToken(User user) {
		
		String JWT = Jwts.builder().setSubject(user.getLogin() + "")
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		
		
		return JWT;
	}
	
	static void addAuthentication(HttpServletResponse response, User user) {
		
		String JWT = generateToken(user);
		
		AuthUserDTO auth = new AuthUserDTO();
		auth.setToken(JWT);
		auth.setName(user.getName());
		auth.setPermissions(user.getIntListPermissions());
		auth.setLogin(user.getLogin());
		auth.setId(user.getId());
		
		
		response.setContentType("application/json");
		OutputStream os;
		try {
			os = response.getOutputStream();
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(os, auth);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	static Authentication getAuthenticationByToken(String token) {
		if (token != null) {
			
			String user = null;
			try {
				user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
						.getSubject();
			} catch (Exception ex) {
				user = null;
			}

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
			}

		}
		
		return null;
	}
	
	static Authentication getAuthentication(HttpServletRequest request) {
		
		String token = request.getHeader(HEADER_STRING);

		return getAuthenticationByToken(token);
	}
	
	
	public static String[] splitToken(String token) {
		
		String sToken[] = new String[2];
		
		for (int i = 0; i < token.length(); i++) {
			
			if (i + 3 < token.length()) {
				if (token.charAt(i) == ';' && token.charAt(i + 1) == '|' && token.charAt(i + 2) == ';') {
					sToken[0] = token.substring(0, i);
					sToken[1] = token.substring(i + 3, token.length());
				}
			}
			
		}
		
		return sToken;
		
	}
}