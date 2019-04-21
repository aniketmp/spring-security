package com.pebstone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class JwtConfigurationAdapter extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsService userDetailsService;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
        		.antMatchers("/jwt").hasAuthority("USER")
        		.antMatchers("/oauth2/redirect","/auth_code/**").permitAll()
                .anyRequest().authenticated()
                .and()               
                .addFilter(new JwtAuthFilter(authenticationManager()));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);

    }
}
