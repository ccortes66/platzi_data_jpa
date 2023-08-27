package com.platzi.pizzeria.persistence.DTO.user;

import com.platzi.pizzeria.persistence.model.Users;

import jakarta.validation.constraints.NotBlank;

public record UserEntity(
    @NotBlank
    String username,
    @NotBlank
    String password

) 
{
    public UserEntity(Users users)
    {
        this(users.getUsername(), 
             users.getPassword());
    }
}
