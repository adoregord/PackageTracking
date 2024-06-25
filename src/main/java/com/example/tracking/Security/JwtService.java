package com.example.tracking.Security;

import java.security.Key;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.SignatureAlgorithm;
import java.util.HashMap;

@Service
@SuppressWarnings("deprecation")
public class JwtService {

    private static final String SECRET_KEY = "a2f13e9d8787a9fad4bd0didic5242ee31ce924b781b64d5263952d75a2bDidi";

    public String extractUsername(String tokenJwt) {
        return extractClaim(tokenJwt, Claims::getSubject);
    }

    public <T> T extractClaim(String tokenJwt, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(tokenJwt);
        return claimResolver.apply(claims);
    }  
    
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
        Map<String, Object> extraClaims,
        UserDetails userDetails
    ){
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
            .signWith(getSignInkey(), SignatureAlgorithm.HS256)
            .compact();
    }

    //method to validate token
    public boolean isTokenValid(String tokenJwt, UserDetails userDetails){
        final String username = extractUsername(tokenJwt);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(tokenJwt);
    }

    private boolean isTokenExpired(String tokenJwt) {
        return extractExpiration(tokenJwt).before(new Date());
    }

    private Date extractExpiration(String tokenJwt) {
        return extractClaim(tokenJwt, Claims::getExpiration);
    }

    private Claims extractAllClaims(String tokenJwt){
        return Jwts
            .parser()
            .setSigningKey(getSignInkey())
            .build()
            .parseClaimsJws(tokenJwt)
            .getBody();
    }

    private Key getSignInkey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
