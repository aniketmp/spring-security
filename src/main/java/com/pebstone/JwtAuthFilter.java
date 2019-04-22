package com.pebstone;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtAuthFilter extends BasicAuthenticationFilter {

	public static final String SECRET = "Aniket";

	public static final long EXPIRATION_TIME = 864_000_000; // 10 days

	public static final String TOKEN_PREFIX = "Bearer ";

	public static final String HEADER_STRING = "Authorization";
	
	public static final String ROLE_STRING = "role";

	public JwtAuthFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override

	protected void doFilterInternal(HttpServletRequest req,

			HttpServletResponse res,

			FilterChain chain) throws IOException, ServletException {

		String header = req.getHeader(HEADER_STRING);

		if (header == null || !header.startsWith(TOKEN_PREFIX)) {

			chain.doFilter(req, res);

			return;

		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		chain.doFilter(req, res);

	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws IllegalArgumentException, UnsupportedEncodingException {

		String token = request.getHeader(HEADER_STRING);
		System.out.println("Received JWT Token:"+token);
		Algorithm algorithm;
		if (token != null) {

		    
		    algorithm = Algorithm.HMAC256(SECRET);
            //JWTVerifier verifier = JWT.require(algorithm).withIssuer("prime").build(); //Reusable verifier instance
            JWTVerifier verifier = JWT.require(algorithm).build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token.replace(TOKEN_PREFIX, ""));
            
            
            
			// parse the token.

						
			String userName=jwt.getSubject();			
			String role=jwt.getClaim(ROLE_STRING).asString();
								

			if (userName != null) {
				
				 List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
			        auths.add(new SimpleGrantedAuthority(role));			        
				return new UsernamePasswordAuthenticationToken(userName, null,auths);

			}

			return null;

		}

		return null;

	}

}
