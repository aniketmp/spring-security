package com.pebstone;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
@Order(1)   
public class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity http) throws Exception {
        http
            .antMatcher("/api/**")                               
            .authorizeRequests()
                .anyRequest().hasRole("API")
                .and()
            .httpBasic();
    }
}
