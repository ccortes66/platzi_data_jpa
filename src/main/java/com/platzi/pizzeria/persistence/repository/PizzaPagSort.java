package com.platzi.pizzeria.persistence.repository;

import org.springframework.data.repository.ListPagingAndSortingRepository;

import com.platzi.pizzeria.persistence.model.Pizza;

public interface PizzaPagSort extends ListPagingAndSortingRepository<Pizza,Long>{
    
}
