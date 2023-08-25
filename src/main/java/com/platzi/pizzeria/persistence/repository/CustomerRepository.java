package com.platzi.pizzeria.persistence.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.platzi.pizzeria.persistence.model.Customer;

public interface CustomerRepository extends ListCrudRepository<Customer,Long>
{   
    @Query(value = "SELECT C FROM Customer C WHERE C.phoneNumber = :phone")
    Optional<Customer> findByPhone(@Param("phone") String phone);
}
