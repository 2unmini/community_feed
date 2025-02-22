package com.example.community_feed.commons.filter;

import com.example.community_feed.commons.constant.Role;
import com.example.community_feed.user.CustomUser;
import com.example.community_feed.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collection;
import java.util.Iterator;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String email = obtainUsername(request);
        String password = obtainPassword(request);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, password, null);
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        CustomUser customUser = (CustomUser) authResult.getPrincipal();
        String email = customUser.getUsername();
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority authority = iterator.next();

        String role = authority.getAuthority();
        Role userRole = Role.valueOf(role.replace("ROLE_", ""));
        String token = jwtUtil.generateToken(email, userRole);

        response.addHeader("Authorization", "Bearer " + token);

    }

    @Nullable
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter("email");
    }
}
