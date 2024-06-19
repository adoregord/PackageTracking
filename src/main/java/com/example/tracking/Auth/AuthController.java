package com.example.tracking.Auth;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;



@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> userRegister(@RequestBody RegisterRequest request) {
        try {
            if(request.getUsername().length()>=10){
                log.info("Username harus kurang dari 10 karakter");
                return ResponseEntity.badRequest().build();
            }
            else if(request.getPassword() == null || request.getPassword().isEmpty()){
                log.info("Password tidak boleh kosong");
                return ResponseEntity.badRequest().build();
            }
            log.info("Berhasil menambahkan user " + request.getUsername());
            return ResponseEntity.ok(authService.userRegister(request));
        } 
        catch (Exception e) {
            e.printStackTrace();
            log.info("Gagal menambahkan user " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
            
        
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> userAuthenticate(@RequestBody RegisterRequest auth) {
        try {
            Optional<Auth> auth2 = authRepository.findByUsername(auth.getUsername());
            if(auth2.get().getUsername() == null){
                log.info("Username atau password salah");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            boolean isPasswordMatch = passwordEncoder.matches(auth.getPassword(), auth2.get().getPassword());
            if(!isPasswordMatch){
                log.info("Username atau password salah");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            return ResponseEntity.ok(authService.userAuthenticate(auth));
        } 
        catch (Exception e) {
            e.printStackTrace();
            log.info("Gagal login " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

}


    // try {
    //         Auth auth2 = authRepository.findByUsername(auth.getUsername());
    //         if (auth2 == null) {
    //             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username atau password salah");
    //         }
    //         boolean isPasswordMatch = passwordEncoder.matches(auth.getPassword(), auth2.getPassword());
    //         if (!isPasswordMatch) {
    //             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username atau password salah");
    //         }
    //         return ResponseEntity.ok("User dan password cocok ");
    //     } 
    //     catch (Exception e) {
    //         e.printStackTrace();
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Gagal login " + e.getMessage());
    //     }  

    // try {
        //     if(auth.getUsername().length()>=10){
        //         return ResponseEntity.badRequest().body("Username harus kurang dari 10 karakter");
        //     }
        //     else if(auth.getPassword() == null || auth.getPassword().isEmpty()){
        //         return ResponseEntity.badRequest().body("Password tidak boleh kosong");
        //     }
        //     auth.setPassword(passwordEncoder.encode(auth.getPassword()));
        //     authRepository.save(auth);
        //     return ResponseEntity.ok("Akun user telah dibuat");
        // }
        // catch (Exception e) {
        //     e.printStackTrace();
        //     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Gagal menambahkan user " + e.getMessage());
        // }