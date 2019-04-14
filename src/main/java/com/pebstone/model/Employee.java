package com.pebstone.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity 
public class Employee {    

    @Id    
    private int id;
    private String userName;
    private String password;
    private String roles;
    private String bankName;
    private String token;
    private int loginAttempt;
    private boolean isActive=true;
    
    
    public int getLoginAttempt() {
		return loginAttempt;
	}

	public void setLoginAttempt(int loginAttempt) {
		this.loginAttempt = loginAttempt;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Employee() {       
    }
    
    public Employee(int id, String userName, String password, String roles, String bankName) {        
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.bankName = bankName;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRoles() {
        return roles;
    }
    public void setRoles(String roles) {
        this.roles = roles;
    }
    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    
    public String getToken() {
        return "12121212";
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Employee [userName=" + userName + ", password=" + password + ", roles=" + roles + ", bankName="
                + bankName + "]";
    }
       
    
}
