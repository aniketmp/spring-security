package com.pebstone;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.stereotype.Component;


@Order(5)
@Component
public class Oauth2ConfigurationAdapter extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()        
        .antMatchers(HttpMethod.GET,"/oauth2/client_credentials").access("#oauth2.hasScope('mobileclient')")
        .antMatchers(HttpMethod.GET,"/oauth2/password").hasAnyRole("USER")        
        .anyRequest().authenticated()
        .and()
        .csrf().disable();
        }     
    
    /*@Bean
    public RemoteTokenServices LocalTokenService() {
        final RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl("http://localhost:8901/auth/oauth/check_token");
        tokenService.setClientId("springsecurity");
        tokenService.setClientSecret("Teno2019");
        return tokenService;
    }*/
}
