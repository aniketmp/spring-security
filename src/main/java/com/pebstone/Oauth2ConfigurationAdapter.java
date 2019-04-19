package com.pebstone;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.context.NullSecurityContextRepository;
import org.springframework.stereotype.Component;


@Order(5)
@Component
public class Oauth2ConfigurationAdapter extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()        
        .antMatchers(HttpMethod.GET,"/oauth2").access("#oauth2.hasScope('mobileclient')")        
        .antMatchers("/redirect","/auth_code").permitAll()
        .anyRequest().authenticated()
        .and()
        .csrf().disable();
        }           
}
