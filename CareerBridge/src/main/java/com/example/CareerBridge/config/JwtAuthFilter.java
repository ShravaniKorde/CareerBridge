package com.example.CareerBridge.config;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.CareerBridge.repo.UserRepo;
import com.example.CareerBridge.service.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserRepo userRepo;

   @Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain) throws ServletException, IOException {

    // 1️⃣ Skip JWT check for /auth endpoints
    String path = request.getServletPath();
    if (path.startsWith("/auth/")) {
        filterChain.doFilter(request, response);
        return;  // skip further JWT processing
    }

    // 2️⃣ Existing JWT validation logic
    String authHeader = request.getHeader("Authorization");
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
        String token = authHeader.substring(7);
        String username = jwtUtils.extractUsername(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // validate token & set authentication in context
        }
    }

    filterChain.doFilter(request, response); // continue with other filters
}
 @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return path.startsWith("/auth") 
               || path.startsWith("/swagger-ui") 
               || path.startsWith("/v3/api-docs");
    }
}
