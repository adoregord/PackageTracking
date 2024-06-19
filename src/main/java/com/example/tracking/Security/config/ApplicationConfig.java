package com.example.tracking.Security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.tracking.Auth.AuthRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final AuthRepository authRepository;
    
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> authRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean //data access object responsible to fetch userdetails encode password
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());

        //password encoder
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;

    }

    @Bean //authentication manager
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean 
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}


