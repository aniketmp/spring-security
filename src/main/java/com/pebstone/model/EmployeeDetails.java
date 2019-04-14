package com.pebstone.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class EmployeeDetails implements UserDetails{

    Employee employee;
    public EmployeeDetails(Employee employee) {
        this.employee=employee;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
        auths.add(new SimpleGrantedAuthority(employee.getRoles()));
        return auths;        
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return employee.getPassword();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return employee.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return employee.isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    public Employee getEmployee()
    {
        return employee;
    }

}
