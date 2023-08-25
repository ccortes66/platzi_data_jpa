package com.platzi.pizzeria.persistence.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ResultOrderSummary(
    Long idOrder,
    String customerName,
    LocalDateTime orderDate,
    BigDecimal orderTotal,
    String pizzaNames
) 
{
   public ResultOrderSummary(OrderSummary summary)
   {
      this(summary.idOrder(), 
           summary.customerName(), 
           summary.orderDate(), 
           summary.orderTotal(),
           summary.pizzaNames());
   }
}
