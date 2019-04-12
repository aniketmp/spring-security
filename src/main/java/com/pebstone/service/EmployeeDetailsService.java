package com.pebstone.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pebstone.dao.EmployeeRepository;
import com.pebstone.model.Employee;
import com.pebstone.model.EmployeeDetails;

@Service
public class EmployeeDetailsService implements UserDetailsService{
    
    @Autowired
    private EmployeeRepository empRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @PostConstruct
    public void initialize()
    {
        empRepository.save(new Employee(1,"emp1",bCryptPasswordEncoder.encode("emp1"),"CASHIER","HDFC"));
        empRepository.save(new Employee(2,"emp2",bCryptPasswordEncoder.encode("emp2"),"MANAGER","HDFC"));
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee user = empRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
       
       EmployeeDetails e=new EmployeeDetails(user);
       return e;
    }

}
