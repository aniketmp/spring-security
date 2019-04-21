package com.pebstone;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class testRunner implements CommandLineRunner{

	@Override
	public void run(String... arg0) throws Exception {
		 byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("Aniket");
		    Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.toString());
		
        String token = Jwts.builder()

                .setSubject("aniket")

                .setExpiration(new Date(System.currentTimeMillis() + 864_000_000L))
                .claim("role", "USER")	
                .signWith(SignatureAlgorithm.HS512,signingKey)
                
                .compact();
        
       
		System.out.println("=============================secret:"+apiKeySecretBytes+" JWT Token:"+token+ " =======================");
	}

}
