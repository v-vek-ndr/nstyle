package com.nstyleintl.nstyle.security.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nstyleintl.nstyle.security.service.JwtTokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class TokenGenerationFilter extends OncePerRequestFilter {
    private final JwtTokenService jwtTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().contains("create-token")) {
            String secretExchange = request.getParameter("secret-exchange");

            if (StringUtils.hasLength(secretExchange) && secretExchange.equals("nstyle-secret")) {
                String token = jwtTokenService.generateToken(secretExchange);
                response.setContentType("application/json");
                response.getWriter().write("{\"token\": \"" + token + "\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"Username is required\"}");
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}

