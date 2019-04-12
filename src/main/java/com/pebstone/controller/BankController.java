package com.pebstone.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pebstone.service.CustomerService;

@RestController
public class BankController {

	@Autowired
	private CustomerService customerService;
	
	@PostConstruct
	public void initialize()
	{
	    customerService.add(1000);                ;
	}
	
	@RequestMapping("/basic")
	public String healthCheckBasic() {
		return "OK";
	}
	@RequestMapping("/digest")
    public String healthCheckDigest() {
        return "OK";
    }
	@RequestMapping("/certificate")
    public String healthCheckCertificate() {
        return "OK";
    }
	
	@ResponseBody
	@RequestMapping("/api/deposit")	
    public int deposite(@RequestParam(value="balance") int balance) {
	    return customerService.deposit(balance);                
    }
	
	@ResponseBody
	@RequestMapping("/api/withdraw")	
    public int withdraw(@RequestParam(value="balance") int balance) {
        return customerService.withdraw(balance);                
    }
	
	@RequestMapping("/api/initialize")
    public void add(@RequestParam(value="balance") int balance) {
	    customerService.add(balance);                
    }
	
	@RequestMapping("/api/display")
    public int show()  {
	    return customerService.show();               
    }
}
