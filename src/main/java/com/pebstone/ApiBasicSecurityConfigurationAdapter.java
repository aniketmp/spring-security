package com.pebstone;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.context.NullSecurityContextRepository;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ApiBasicSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity http) throws Exception {
        http
            .securityContext()
                .securityContextRepository(new NullSecurityContextRepository())
            .and() 
            .antMatcher("/basic/**")                               
            .authorizeRequests()
                .anyRequest().hasRole("API")
                .and() 
            .httpBasic();
    }
}
