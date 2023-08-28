package com.platzi.pizzeria.persistence.repository;

import com.platzi.pizzeria.persistence.model.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository  extends CrudRepository<Users,String>
{
}
