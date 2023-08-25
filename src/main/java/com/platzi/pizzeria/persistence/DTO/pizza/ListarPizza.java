package com.platzi.pizzeria.persistence.DTO.pizza;

import java.math.BigDecimal;

import com.platzi.pizzeria.persistence.model.Pizza;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
 
public record ListarPizza( 
     @NotBlank
     String name,
     @NotBlank
     String description,
     @NotNull
     BigDecimal price,
     @NotNull
     Byte vegetarian,
     @NotNull
     Byte vegan) 
{  
    public ListarPizza(Pizza pizza)
    {
        this(pizza.getName(), 
             pizza.getDescription(), 
             pizza.getPrice(), 
             pizza.getVegetarian(),
             pizza.getVegan());
    }
}
