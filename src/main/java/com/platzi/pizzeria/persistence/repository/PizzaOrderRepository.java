package com.platzi.pizzeria.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.platzi.pizzeria.persistence.model.PizzaOrder;
import com.platzi.pizzeria.persistence.projection.ResultOrderSummary;

public interface PizzaOrderRepository extends JpaRepository<PizzaOrder,Long>
{   
   
    List<PizzaOrder> findAllByDateAfter(LocalDateTime date);
    List<PizzaOrder> findAllByMethodIn(List<PizzaOrder.Method> methods);
    
    @Query(value = "SELECT po.* FROM pizza_order po " +
               "LEFT JOIN customer cu " +
               "ON po.customer_id = cu.id " +
               "WHERE cu.dni = :dni", nativeQuery = true)
    List<PizzaOrder> findByCustomerDni(@Param("dni") String dni);

    @Query(value = "SELECT po.id AS idOrder , c.name AS customerName, po.date AS orderDate , po.total AS orderTotal, p.name AS pizzaNames " +
                   "FROM pizza_order po " +
                   "INNER JOIN customer c ON po.customer_id = c.id " +
                   "INNER JOIN order_item oi ON oi.order_id = po.id " +
                   "INNER JOIN pizza p ON oi.pizza_id = p.id " +
                   "GROUP BY po.id HAVING po.id = :id" , nativeQuery = true)
    ResultOrderSummary findSummary(@Param("id") Long id );
    
    @Procedure(value = "take_random_pizza_order", outputParameterName = "order_taken")
     Boolean saveRamdomOrder(@Param("dni_value") String dni, @Param("method_value") String option);





    
    
    
    



   
}



 

