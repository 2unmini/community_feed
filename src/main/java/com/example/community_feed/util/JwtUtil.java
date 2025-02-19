package com.example.community_feed.util;

import com.example.community_feed.commons.constant.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
@Component
public class JwtUtil {
    @Value("${spring.jwt.secret}")
    private String key;


    // todo 만료시간 추가 할것
    public String generateToken(String email, Role role) {
        return Jwts.builder()
                .subject(email)
                .claim("role",role)
                .signWith(Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8)),Jwts.SIG.HS256)
                .compact();
    }
}
