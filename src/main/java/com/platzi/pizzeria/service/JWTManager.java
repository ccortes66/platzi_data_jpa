package com.platzi.pizzeria.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class JWTManager
{
    private static Algorithm algorithm = Algorithm.HMAC256("dsdssadsasaddsaasdadddsa");

    public String createToken(String username)
    {
        return JWT.create()
                .withSubject(username)
                .withIssuer("platzi-pizza")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(3)))
                .sign(algorithm);
    }

    public  Boolean isValid(String token)
    {
        try
        {
            JWT.require(algorithm)
                    .build()
                    .verify(token);
                    return true;
        }catch (JWTVerificationException ex)
        {return false;}
    }

    public String getUsername(String token)
    {
        return JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();
    }


}
