package com.platzi.pizzeria.config;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class CustomJWTFilter extends OncePerRequestFilter
{
    
    private final JWTUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    
    @Autowired
    public CustomJWTFilter(JWTUtils jwtUtils, UserDetailsService userDetailsService) 
    {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain)
                                    throws ServletException, IOException 
    {   
        
        //1. Validar que el Header Auithorization sea valido
      
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(!request.getRequestURI().equals("/auth/login"))
        {
           if((authHeader.isEmpty() || authHeader == null || !authHeader.trim().startsWith("Bearer")))
             {
                filterChain.doFilter(request, response);
                return;
            }
      
        
         
        //validar el token  es  valido   
        String token = authHeader.split(" ")[1];  
        if(!jwtUtils.isValid(token))
        {
            filterChain.doFilter(request, response);
            return;
        }

        //validar el usuario en la base de datos
        String username = jwtUtils.getUsername(token);
        User user = (User) userDetailsService.loadUserByUsername(username);
 
        //cargar al usuario  el jwt
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
           user.getUsername(),user.getPassword(),user.getAuthorities()
         );
        
         SecurityContextHolder.getContext().setAuthentication(authenticationToken);
         filterChain.doFilter(request, response);
        }
    }   
    
}
