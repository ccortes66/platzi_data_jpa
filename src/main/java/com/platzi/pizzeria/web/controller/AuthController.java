package com.platzi.pizzeria.web.controller;

import com.platzi.pizzeria.persistence.DTO.auth.AuthLogin;
import com.platzi.pizzeria.service.JWTManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController
{
    private final AuthenticationManager manager;
    private final JWTManager jwtManager;
    @PostMapping("/login")
    public ResponseEntity<Void> loginUser(@RequestBody @Valid AuthLogin login)
    {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login.username(),login.password());
        Authentication authentication = manager.authenticate(authenticationToken);
        String token = jwtManager.createToken(login.username());
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,token).build();
    }
}
