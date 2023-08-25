package com.platzi.pizzeria.persistence.repository;

import java.util.Optional;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.platzi.pizzeria.persistence.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza,Long>
{

    Optional<Pizza> findByName(String name);
    Optional<Pizza> findFirstByAvailableTrueAndNameIgnoreCase(String name);

    List<Pizza> findByAvailableTrue();
    List<Pizza> findByAvailableTrueOrderByPrice();
    List<Pizza> findAllByAvailableTrueAndNameContainingIgnoreCase(String name);
    List<Pizza> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPrice(BigDecimal price);
    Integer countByVeganTrue();
    
}
