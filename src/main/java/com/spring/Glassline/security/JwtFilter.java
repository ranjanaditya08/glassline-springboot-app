package com.spring.Glassline.security;

import com.spring.Glassline.service.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtTokenService.extractUserName(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
          //  System.out.println(userDetails);
          //  System.out.println("DEBUG FILTER: "+ username);
            if (jwtTokenService.validateToken(token, userDetails)) {
              //  System.out.println("After validate token");
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,  userDetails.getAuthorities());
              //  System.out.println("After authToken");
                System.out.println(authToken);
                authToken.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request));
               // System.out.println("After WebAuthentication");
                SecurityContextHolder.getContext().setAuthentication(authToken);
              //  System.out.println("After SetContext");
            }else {
                System.out.println("Invalid Toekn");
            }
        }

        filterChain.doFilter(request, response);
    }
}
