package com.example.community_feed.commons.filter;

import com.example.community_feed.commons.constant.Role;
import com.example.community_feed.user.CustomUser;
import com.example.community_feed.user.User;
import com.example.community_feed.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        if (authorization == null || authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
        }

        String token = authorization.substring(7).trim();
        if (jwtUtil.tokenExpired(token)) {
            filterChain.doFilter(request, response);

        }
        String role = jwtUtil.getRole(token);
        String email = jwtUtil.getEmail(token);
        System.out.println(role);
        System.out.println(email);
        User user = User.builder()
                .email(email)
                .password("TEST_PASSWORD")
                .role(Role.of(role)).build();

        CustomUser customUser = new CustomUser(user);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(customUser, null, customUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }


}
