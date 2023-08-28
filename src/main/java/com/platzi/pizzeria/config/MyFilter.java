package com.platzi.pizzeria.config;

import com.platzi.pizzeria.service.JWTManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Security;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Component
@RequiredArgsConstructor
public class MyFilter extends OncePerRequestFilter
{
    private final JWTManager manager;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {

        if (!request.getRequestURI().startsWith("/auth/"))
        {
            Optional<String> header = Optional.of(request.getHeader(HttpHeaders.AUTHORIZATION));
            header.ifPresent(data -> {
                String token = this.verifyHeaderRequest(data);
                if (!token.startsWith("INVALID") && manager.isValid(token))
                {
                    String username = manager.getUsername(token);
                    User user = (User) userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            });

        }

        filterChain.doFilter(request,response);
    }

    private String verifyHeaderRequest(String header)
    {
        return header.startsWith("Bearer")
                     ? header.replace("Bearer ","")
                     : String.format("INVALID %s",header.substring(6));
    }
}
