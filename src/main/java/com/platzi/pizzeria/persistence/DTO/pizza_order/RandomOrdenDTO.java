package com.platzi.pizzeria.persistence.DTO.pizza_order;

import jakarta.validation.constraints.NotBlank;


public record RandomOrdenDTO(
    @NotBlank
    String dni, 
    @NotBlank
    String option) 
{}
