package com.platzi.pizzeria.persistence.DTO.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthLogin(
        @NotBlank
        String username,
        @NotBlank
        String password)
{
}
