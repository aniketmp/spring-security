package com.pebstone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.NullSecurityContextRepository;

@Order(1)
@Configuration
public class X509CertificateSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
 
    @Autowired
    UserDetailsService userDetailsService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .securityContext()
            .securityContextRepository(new NullSecurityContextRepository()) //It  will prevent the security context from being stored, even if a session has already been created during the request
        .and()
        .antMatcher("/certificate/**")                               
        .authorizeRequests()
            .anyRequest().hasRole("PHARMACY")        
        .and()
        .x509()
            .subjectPrincipalRegex("CN=(.*?)(?:,|$)")
            .userDetailsService(userDetailsService);
    }
}