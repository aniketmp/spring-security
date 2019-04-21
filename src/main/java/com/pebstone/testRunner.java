package com.pebstone;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class testRunner implements CommandLineRunner{

	@Override
	public void run(String... arg0) throws Exception {
        String token = Jwts.builder()

                .setSubject("aniket")

                .setExpiration(new Date(System.currentTimeMillis() + 864_000_000L))

                .signWith(SignatureAlgorithm.HS512, "Aniket")
                .claim("role", "USER")
                .compact();
        
		System.out.println("======================================== JWT Token:"+token+ " =======================");
	}

}
