package com.platzi.pizzeria.persistence.DTO.customer;

import com.platzi.pizzeria.persistence.model.Customer;

public record CustomerEntity(
    String dni,
    String name,
    String address,
    String email,
    String phoneNumber
) 
{
    public CustomerEntity(Customer customer)
    {
        this(customer.getDni(), 
             customer.getName(), 
             customer.getAddress(), 
             customer.getEmail(),
             customer.getPhoneNumber());
    }
}
