package com.pebstone;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pebstone.model.EmployeeDetails;
import com.pebstone.service.EmployeeDetailsService;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

	private EmployeeDetailsService customUserDetailsService;
	private String userNameHeaderName = "access-key";
	private String authTokenHeaderName = "access-token";

	public AuthTokenFilter(EmployeeDetailsService userDetailsService) {
		this.customUserDetailsService = userDetailsService;
	}

	
	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
			throws ServletException, IOException {
		try {			
			
			String userName = httpServletRequest.getHeader(userNameHeaderName);
			String authToken = httpServletRequest.getHeader(authTokenHeaderName);

			if (StringUtils.hasText(authToken)) {				

				EmployeeDetails userDetails = (EmployeeDetails) customUserDetailsService.loadUserByUsername(userName);

				if (userDetails.getEmployee().getToken().equals(authToken)) {
					UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,
							userDetails.getPassword(), userDetails.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(token);
				}
			}

			filterChain.doFilter(httpServletRequest, httpServletResponse);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		
	}

}
