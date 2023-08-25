package com.platzi.pizzeria.persistence.DTO.pizza;

import java.math.BigDecimal;
import java.util.Optional;


import jakarta.validation.constraints.NotNull;

public record UpdatePizza(
     @NotNull
     Long id,
     Optional<String> name,
     Optional<String> description,
     Optional<BigDecimal> price,
     Optional<Byte> vegetarian,
     Optional<Byte> vegan) 
 {}
