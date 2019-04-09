package com.pebstone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.NullSecurityContextRepository;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ApiBasicSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
    
    @Autowired
    UserDetailsService userDetailsService;
    
    protected void configure(HttpSecurity http) throws Exception {
        http
            .securityContext()
                .securityContextRepository(new NullSecurityContextRepository())
            .and() 
            .antMatcher("/basic/**")                               
            .authorizeRequests()
                .anyRequest().hasAnyRole("API")
                .and() 
            .httpBasic();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService);
    }
    
}
