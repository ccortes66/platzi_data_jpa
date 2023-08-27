package com.platzi.pizzeria.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.platzi.pizzeria.persistence.model.Users;

public interface UserRepository extends CrudRepository<Users,String>{
    
}
