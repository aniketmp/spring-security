package com.pebstone.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller
public class MyCustomErrorController // implements ErrorController 
{

    @GetMapping({"/error"})
    public String hello(HttpServletRequest request,Model model) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        model.addAttribute("statusCode", statusCode);
        if(exception!=null)
            model.addAttribute("statusMessage", exception.getMessage());
        else
            model.addAttribute("statusMessage", "N/A");
        return "error";
  }  
  
 // @Override
  public String getErrorPath() {
      return "/error";
  }
}