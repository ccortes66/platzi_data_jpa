package com.platzi.pizzeria.persistence.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OrderSummary{
    Long idOrder();
    String customerName();
    LocalDateTime orderDate();
    BigDecimal orderTotal();
    String pizzaNames();
}
