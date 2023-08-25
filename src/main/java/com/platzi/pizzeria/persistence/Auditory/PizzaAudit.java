package com.platzi.pizzeria.persistence.Auditory;

import com.platzi.pizzeria.persistence.model.Pizza;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;


public class PizzaAudit
{  
    private Pizza pizzaResult;
    
    @PostLoad
    public void postLoad(Pizza pizza) throws CloneNotSupportedException
    {
        System.out.println("Post Load");
        this.pizzaResult = (Pizza) pizza.clone();
    }

    @PostPersist
    @PostUpdate
    public void onPostPersistAndUpdate(Pizza pizza)
    {  
       System.out.printf("POST PERSIS ON UPDATE %n OLD = %s%n NEW %s%n"
                                                                     ,pizzaResult
                                                                     ,pizza); 
    } 

    @PreRemove
    public void onPreDelete(Pizza pizza)
    {
       System.out.println(pizza);
    }
}