package com.pebstone;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.token.OAuth2AccessTokenSupport;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

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

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

		String token = request.getHeader(HEADER_STRING);

		if (token != null) {

			// parse the token.

			Claims claims = Jwts.parser()

					.setSigningKey(SECRET)

					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))

					.getBody();
			
			String userName=claims.getSubject();
			String role=(String) claims.getOrDefault(ROLE_STRING, "");//Setting appllication specific data					

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
