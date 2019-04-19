package com.pebstone.controller;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pebstone.model.Employee;
import com.pebstone.model.EmployeeDetails;

import javassist.bytecode.Descriptor.Iterator;

@Controller
public class HelloWorldController {
    @GetMapping({ "/", "/hello" })
    public String hello(Model model,
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        String userName = "";
        String password = "";
        String companyName = "";
        String roles = "";
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof EmployeeDetails)
        {
            EmployeeDetails empDetails=(EmployeeDetails)principal;
            Employee e=empDetails.getEmployee();
            companyName = e.getBankName();
        }
        if (principal instanceof UserDetails) 
        {
            UserDetails ud=(UserDetails)principal;
            userName=ud.getUsername();
            password=ud.getPassword();
            Collection<? extends GrantedAuthority> grantedAuthority= ud.getAuthorities() ;
            java.util.Iterator<? extends GrantedAuthority> itr=grantedAuthority.iterator();
            while(itr.hasNext())
            {
                roles+=itr.next();
            }
                
        } 
        else 
        {
            userName = principal.toString();
        }
        model.addAttribute("name", userName);
        model.addAttribute("password", password);
        model.addAttribute("companyName", companyName);
        model.addAttribute("companyName", companyName);
        model.addAttribute("roles", roles);
        return "hello";
    }

    @GetMapping({ "/login" })
    public String login() {
        return "login";
    }
    
    @GetMapping({ "/auth_code" })
    public String getOauthCodeForm() {
        return "secure";
   }
    
   
}