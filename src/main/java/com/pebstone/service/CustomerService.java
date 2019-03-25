package com.pebstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pebstone.dao.BankRepository;
import com.pebstone.model.Account;

@Service
public class CustomerService {
	
	@Autowired
	private BankRepository customerRepository;
			
	public int deposit(int balance){
	    return 0;	    
	}
	
	public void add(int balance){
        Account account=new Account(balance);        
        customerRepository.save(account);        
    }
		
	public int withdraw(int balance){        
	    return 0;
    }
		
	public int show(){
        Account account=customerRepository.findOne(1);
        return account.getBalance();
                       
    }	
}