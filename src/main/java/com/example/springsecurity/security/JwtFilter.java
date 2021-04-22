package com.example.springsecurity.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;


public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String authorization = httpServletRequest.getHeader("Authorization");

        UsernamePasswordAuthenticationToken authenticationToken = getUsernamePasswordAuthenticationToken(authorization);

        //Autoryzacja urządzenia
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String authorization) {
        //parsuje token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC512("**")).build();
        //sprawdzam czy algorytm dziala z kluczem
        DecodedJWT verify = jwtVerifier.verify(authorization.substring(7));
        String name = verify.getClaim("name").asString();
        boolean isValid = verify.getClaim("admin").asBoolean();
        String role = "ROLE_USER";
        if(isValid)
            role = "ROLE_ADMIN";
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(name, null, Collections.singleton(simpleGrantedAuthority));
        return token;
    }
}
