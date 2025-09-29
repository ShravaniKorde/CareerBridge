package com.example.CareerBridge.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtils {

    private final SecretKey secretKey;
    private final long jwtExpirationMs;

    // Inject values from application.properties
    public JwtUtils(@Value("${jwt.secret}") String secret, @Value("${jwt.expirationMs}") long expirationMs) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes()); // ✅ secure HS256 key
        this.jwtExpirationMs = expirationMs;
    }

    // Generate token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(secretKey) // use secure key
                .compact();
    }

    // Extract username from token
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Get claims
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Validate token
    public boolean validateToken(String token, String username) {
        String tokenUsername = extractUsername(token);
        return tokenUsername.equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}
