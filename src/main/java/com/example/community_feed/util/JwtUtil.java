package com.example.community_feed.util;

import com.example.community_feed.commons.constant.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${spring.jwt.secret}")
    private String key;

    @Value("${spring.jwt.expiry-mills}")
    private Long expiryMill;


    public String generateToken(String email, Role role) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expiryMill);
        return Jwts.builder()
                .subject(email)
                .claim("role", role.getName())
                .expiration(expiration)
                .signWith(Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8)), Jwts.SIG.HS256)
                .compact();
    }

    public boolean tokenExpired(String token) {
        System.out.println(Jwts.parser().verifyWith(Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8))).build().parseSignedClaims(token)
                .getPayload().getExpiration().toString());
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8))).build().parseSignedClaims(token)
                .getPayload().getExpiration().before(new Date());

    }

    public String getEmail(String token) {
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8))).build().parseSignedClaims(token)
                .getPayload().getSubject();
    }

    public String getRole(String token) {
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8))).build().parseSignedClaims(token)
                .getPayload().get("role", String.class);
    }


}
