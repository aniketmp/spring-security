package com.pebstone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.NullSecurityContextRepository;
import org.springframework.stereotype.Component;

import com.pebstone.service.EmployeeDetailsService;

@Order(1)
@Component
public class TokenBasedSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
    
    @Autowired
    EmployeeDetailsService employeeDetailsService;
    
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    AuthTokenFilter filter;
    
    protected void configure(HttpSecurity http) throws Exception {
    	 http
         .securityContext()
             .securityContextRepository(new NullSecurityContextRepository())
         .and() 
         	.antMatcher("/token/**") 
         	.addFilterBefore(filter, BasicAuthenticationFilter.class)        
         .authorizeRequests()
             .anyRequest().hasAnyAuthority("CASHIER");    	 
  
    }
 
}
