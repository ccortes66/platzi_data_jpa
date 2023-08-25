package com.platzi.pizzeria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platzi.pizzeria.persistence.DTO.customer.CustomerEntity;
import com.platzi.pizzeria.persistence.repository.CustomerRepository;

@Service
public class CustomerService 
{
    private final CustomerRepository repository;
     
    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }


    public CustomerEntity findByPhone(String phone)
    {   
        
        Optional<CustomerEntity> entity = Optional.ofNullable(new CustomerEntity(repository.findByPhone(phone).get()));
        return entity.get();
    }



    
    
}
