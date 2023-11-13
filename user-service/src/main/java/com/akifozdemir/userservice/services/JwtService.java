package com.akifozdemir.userservice.services;

import com.akifozdemir.userservice.dtos.LoginRequest;
import com.akifozdemir.userservice.dtos.UserPayload;
import com.akifozdemir.userservice.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {
    @Value("${jwt.secret}")
    private String SECRET;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }

    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        UserPayload userPayload = new UserPayload(user.getId(), user.getEmail(),
                user.getFirstName()+" "+user.getLastName());
        claims.put("user",userPayload);
        return createToken(claims);
    }

    private String createToken(Map<String, Object> claims) {
        long expirationTimeInMs = 1000 * 60 * 30;
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}