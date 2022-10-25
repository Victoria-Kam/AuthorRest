package com.example.hibernatetest.config.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

// на 1:20:32

@Component
public class JWTProvider {

    @Value("$(jwt.secret)")
    private String jwtSecret;

    public String generateAccessToken(String nickName){
        return generateToken(nickName, TimeUnit.MINUTES.toMinutes(5));
    }

    public String generateRefreshToken(String nickName){
        return generateToken(nickName, TimeUnit.DAYS.toMinutes(1));
    }
    private String generateToken(String nickName, Long expirationTime){
        return Jwts.builder()
                .setSubject(nickName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validate(String token){
        try {
            parseToken(token);
            return true;
        }catch (Exception e){
            System.out.println("Invalid token");
        }
        return false;
    }


    public String getNickNameFromToken(String token){
        return parseToken(token).getBody().getSubject();
    }

    private Jws<Claims> parseToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
    }
}
