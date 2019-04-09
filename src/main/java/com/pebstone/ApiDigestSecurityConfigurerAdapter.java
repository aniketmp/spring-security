package com.pebstone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;
import org.springframework.security.web.context.NullSecurityContextRepository;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class ApiDigestSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Autowired
    DigestAuthenticationFilter digestAuthenticationFilter;
    
    @Autowired
    DigestAuthenticationEntryPoint digestEntryPoint;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http     
            .securityContext()
                .securityContextRepository(new NullSecurityContextRepository()) //It  will prevent the security context from being stored, even if a session has already been created during the request
            .and()
            .antMatcher("/digest/**")                               
            .authorizeRequests()
                .anyRequest().hasRole("API")
            .and()
            .addFilter(digestAuthenticationFilter)              // register digest entry point
            .exceptionHandling().authenticationEntryPoint(digestEntryPoint); // on exception ask for digest authentication
    }
}