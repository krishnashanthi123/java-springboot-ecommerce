package com.ecommerce.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerce.entity.Seller;
import com.ecommerce.repository.SellerRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Collections;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)

            throws ServletException, IOException {

        String authHeader =
                request.getHeader("Authorization");
        System.out.println("Authorization Header = " + authHeader);

        String token = null;

        String username = null;
        System.out.println("Username = " + username);

        if (authHeader != null &&
                authHeader.startsWith("Bearer ")) {

            token = authHeader.substring(7);

            username =
                    jwtUtil.extractUsername(token);
        }

        if (username != null &&
                SecurityContextHolder
                        .getContext()
                        .getAuthentication() == null) {

            Optional<Seller> seller =
                    sellerRepository
                            .findBySellerUsername(
                                    username);

            if (seller.isPresent() &&
                    jwtUtil.validateToken(
                            token,
                            username)) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                Collections.singletonList(
                                        new SimpleGrantedAuthority(
                                                "ROLE_USER")));

                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request));

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}