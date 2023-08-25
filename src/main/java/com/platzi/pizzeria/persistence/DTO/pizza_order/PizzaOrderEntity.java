package com.platzi.pizzeria.persistence.DTO.pizza_order;

import com.platzi.pizzeria.persistence.model.PizzaOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record PizzaOrderEntity(
    String customerId,
    LocalDateTime date,
    BigDecimal total,
    PizzaOrder.Method method,
    String additionalNotes
)
{
    public PizzaOrderEntity(PizzaOrder order)
    {
        this(order.getCustomer().getDni(),
             order.getDate(),
             order.getTotal(), 
             order.getMethod(), 
             order.getAdditionalNotes());
    }
}
