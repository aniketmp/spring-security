package com.pebstone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;


@Configuration
public class SecurityConfig {
    
    
    
    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("aniket").password("aniket").roles("USER").build());
        manager.createUser(User.withUsername("admin").password("admin").roles("ADMIN").build());
        manager.createUser(User.withUsername("api").password("api").roles("API").build());
        manager.createUser(User.withUsername("apotek.no").password("").roles("PHARMACY").build());
        return manager;
    }  
    @Bean
    DigestAuthenticationEntryPoint digestEntryPoint() {
        DigestAuthenticationEntryPoint bauth = new DigestAuthenticationEntryPoint();
        bauth.setRealmName("Digest WF Realm");
        bauth.setKey("MySecureKey");
        return bauth;
    }
    @Bean
    DigestAuthenticationFilter digestAuthenticationFilter() throws Exception {
        DigestAuthenticationFilter digestAuthenticationFilter = new DigestAuthenticationFilter();
        digestAuthenticationFilter.setUserDetailsService(userDetailsService());
        digestAuthenticationFilter.setAuthenticationEntryPoint(digestEntryPoint());        
        return digestAuthenticationFilter;
    }    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
    
       
}
