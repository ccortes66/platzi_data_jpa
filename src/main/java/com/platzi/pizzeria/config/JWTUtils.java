package com.platzi.pizzeria.config;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;


@Component
public class JWTUtils 
{   
    private static Algorithm algorithm = Algorithm.HMAC256(System.getenv("secret"));

    public String createToken(String username)
    {
        return JWT.create()
                  .withSubject(username)
                  .withIssuer("platzi-pizza")
                  .withIssuedAt(new Date())
                  .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(10)))
                  .sign(algorithm);
        
    }

    public Boolean isValid(String token)  
    {   
        try
        {JWT.require(algorithm)
                  .build()
                  .verify(token);
                   return true;
        }catch (JWTVerificationException ex){ return false;}
          
    }

    public String getUsername(String token)
    {
        return JWT.require(algorithm)
                  .build()
                  .verify(token)
                  .getSubject();
    }
}
