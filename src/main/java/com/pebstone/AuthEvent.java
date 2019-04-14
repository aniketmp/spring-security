package com.pebstone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.pebstone.dao.EmployeeRepository;
import com.pebstone.model.Employee;

@Component
public class AuthEvent  implements ApplicationListener<ApplicationEvent> {
 
	@Autowired
	EmployeeRepository empRepo;
  
	@Override
	public void onApplicationEvent(ApplicationEvent e) {		
		if(e instanceof AuthenticationFailureBadCredentialsEvent)
		{
			AuthenticationFailureBadCredentialsEvent failEvent=(AuthenticationFailureBadCredentialsEvent)e;
	        String userName=failEvent.getAuthentication().getName();
	        System.out.println("Failure UserName:"+userName);	
	        Employee emp=empRepo.findByUserName(userName);
	        if(emp==null)
	        	return;
	        if(emp.getLoginAttempt()==2)
	        {
	        	emp.setActive(false);
	        }
	        else
	        {
	        	emp.setLoginAttempt(emp.getLoginAttempt()+1);
	        }
	        
	        empRepo.save(emp);
	        
		}
		
		if(e instanceof AuthenticationSuccessEvent)
		{
			AuthenticationSuccessEvent successEvent=(AuthenticationSuccessEvent)e;
			Authentication auth=successEvent.getAuthentication();	        	        
	        System.out.println("Success Principle:"+auth.getPrincipal());	        
	        Employee emp=empRepo.findByUserName(auth.getName());
	        if(emp==null)
	        	return;
	        emp.setLoginAttempt(0);
	        
		}
		
	}
}
