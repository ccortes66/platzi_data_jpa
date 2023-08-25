package com.platzi.pizzeria.persistence.DTO.pizza;

import java.util.Optional;

public record DeletePizza(
    Optional<Long> id,
    Optional<String> name
) 
{}
