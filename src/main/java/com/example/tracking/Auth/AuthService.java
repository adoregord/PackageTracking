package com.example.tracking.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tracking.Security.JwtService;

import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final AuthRepository authRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse userRegister(RegisterRequest request) throws AuthException {
        //check if the user already exists
        if (authRepository.existsByUsername(request.getUsername())) {
            throw new AuthException("Username already exists");
        }
        //check if the password less than 5 character
        else if (request.getPassword().length() < 5) {
            throw new AuthException("Password must be at least 5 character");
        }

        var user = Auth.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();
        authRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }

    public AuthenticationResponse userAuthenticate(RegisterRequest auth) throws AuthException {
        //check if the data is empty
        if(authRepository.findAll().isEmpty()){
            throw new AuthException("Tidak ada data user, register terlebih dahulu");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
        
        //if the user is authenticated then
        var user = authRepository.findByUsername(auth.getUsername())
            .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();

    }
    


}
