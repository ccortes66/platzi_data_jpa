package com.platzi.pizzeria.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platzi.pizzeria.persistence.DTO.customer.CustomerEntity;
import com.platzi.pizzeria.service.CustomerService;


@RestController
@RequestMapping("/customer")
public class CustomerController 
{
    private final CustomerService service;
   
    @Autowired
    public CustomerController(CustomerService service) 
    {
        this.service = service;
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<CustomerEntity> getByPhone(@PathVariable String phone)
    {
        return ResponseEntity.ok(service.findByPhone(phone));
    }

    
}
