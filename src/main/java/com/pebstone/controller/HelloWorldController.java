package com.pebstone.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {
    @GetMapping({ "/", "/hello" })
    public String hello(Model model,
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        String userName = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            UserDetails e=(UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userName=e.getUsername();
        } else {
            userName = principal.toString();
        }

        model.addAttribute("name", userName);
        return "hello";
    }

    @GetMapping({ "/login" })
    public String login(Model model) {
        return "login";
    }
    
    @GetMapping({ "/secure" })
    public String secure(Model model) {
        String userName = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }

        model.addAttribute("name", userName);
        
        return "secure";
    }
}