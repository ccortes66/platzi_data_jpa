package com.platzi.pizzeria.persistence.model;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.platzi.pizzeria.persistence.Auditory.Auditory;
import com.platzi.pizzeria.persistence.Auditory.PizzaAudit;
import com.platzi.pizzeria.persistence.DTO.pizza.ListarPizza;
import com.platzi.pizzeria.persistence.DTO.pizza.UpdatePizza;


@Entity
@EntityListeners({AuditingEntityListener.class,PizzaAudit.class})
@Getter @Setter 
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Pizza extends Auditory implements Cloneable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private BigDecimal price;
    @NonNull
    private Byte vegetarian;
    @NonNull
    private Byte vegan;
    private Byte available = 1;
    

    public Pizza(ListarPizza pizza)
    {
        this(pizza.name(), 
             pizza.description(), 
             pizza.price(), 
             pizza.vegetarian(),
             pizza.vegan());
    }

    public Pizza updatePizza(UpdatePizza pizza)
    {
        pizza.name().ifPresent(data -> this.name = data);
        pizza.description().ifPresent(data -> this.description = data);
        pizza.price().ifPresent(data -> this.price = data);
        pizza.vegetarian().ifPresent(data -> this.vegetarian = data);
        pizza.vegan().ifPresent(data -> this.vegan = data);
        return this;     
    }

    @Override
    public Object clone() throws CloneNotSupportedException 
    {
        Pizza pizzaClone = (Pizza) super.clone();
        return pizzaClone;
    }



}
