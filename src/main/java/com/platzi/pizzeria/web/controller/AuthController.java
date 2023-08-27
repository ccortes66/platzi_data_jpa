package com.platzi.pizzeria.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platzi.pizzeria.config.JWTUtils;
import com.platzi.pizzeria.persistence.DTO.user.UserEntity;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/users")
public class AuthController 
{   

    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;

    
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JWTUtils jwtUtils) 
    {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }



    @PostMapping("/login")
    @Transactional
    public ResponseEntity<Void> login(@RequestBody @Valid UserEntity entity)
    {   
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(entity.username(),entity.password());
        Authentication authentication = authenticationManager.authenticate(token);
        System.out.println(authentication.isAuthenticated());
        System.out.println(authentication.getPrincipal());
        String mytoken = jwtUtils.createToken(entity.username());
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,mytoken).build();
    }
}
