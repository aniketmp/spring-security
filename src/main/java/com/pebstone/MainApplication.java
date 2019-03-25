package com.pebstone;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.pebstone.dao.BankRepository;
import com.pebstone.model.Account;

@SpringBootApplication
public class MainApplication extends SpringBootServletInitializer{

	private static final Logger log = LoggerFactory.getLogger(MainApplication.class);
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MainApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
	
}
