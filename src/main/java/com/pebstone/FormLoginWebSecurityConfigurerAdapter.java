package com.pebstone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

import com.pebstone.service.EmployeeDetailsService;

@Order(100)
@Component
public class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    
    @Autowired
    EmployeeDetailsService employeeDetailsService;
    
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()                
                .antMatchers("/trace","/css/**").permitAll()                 
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll();              
  
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(employeeDetailsService);
    }
}
