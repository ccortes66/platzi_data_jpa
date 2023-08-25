package com.platzi.pizzeria.service;

import com.platzi.pizzeria.persistence.DTO.pizza.DeletePizza;
import com.platzi.pizzeria.persistence.DTO.pizza.ListarPizza;
import com.platzi.pizzeria.persistence.DTO.pizza.UpdatePizza;
import com.platzi.pizzeria.persistence.model.Pizza;
import com.platzi.pizzeria.persistence.repository.PizzaPagSort;
import com.platzi.pizzeria.persistence.repository.PizzaRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaService
{
    private final JdbcTemplate jdbcTemplate;
    private final PizzaRepository repository;
    private final PizzaPagSort pizzaPagSort;

    @Autowired
    public PizzaService(JdbcTemplate jdbcTemplate, 
                        PizzaRepository repository,
                        PizzaPagSort pizzaPagSort) 
    {
        this.jdbcTemplate = jdbcTemplate;
        this.repository = repository;
        this.pizzaPagSort = pizzaPagSort; 
    }

    public List<Pizza> getAll()
    {   
        
        return this.jdbcTemplate.query("SELECT * FROM pizza",new BeanPropertyRowMapper<>(Pizza.class));
    }

    public Page<ListarPizza> geListAll(int page)
    {    
        Pageable pageRequest = PageRequest.of(page,20);
        return pizzaPagSort.findAll(pageRequest).map(ListarPizza::new);
    }                                           


    public List<ListarPizza> geListAllByName(String name)
    {    
        
        return repository.findAllByAvailableTrueAndNameContainingIgnoreCase(name).stream()
                                                                                 .map(ListarPizza::new)
                                                                                 .toList();
    }

    public List<ListarPizza> getPizzaByBestPrice(BigDecimal value)
    {    
        
        return repository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPrice(value).stream()
                                                                                 .map(ListarPizza::new)
                                                                                 .toList();
    }

    public ListarPizza getPizza(String name)
    {   
        Optional<ListarPizza> lOptional = Optional.ofNullable(new ListarPizza(repository.findFirstByAvailableTrueAndNameIgnoreCase(name).get()));
        return lOptional.get();
    }

    public ListarPizza getPizzaById(Long id)
    {   
        Optional<ListarPizza> lOptional = Optional.ofNullable(new ListarPizza(repository.findById(id).get()));
        return lOptional.get();
    }
    

    public Pizza savePizza(ListarPizza pizza)
    {
        return repository.save(new Pizza(pizza));
    }

    public ListarPizza updatePizza(UpdatePizza pizza)
    {
        Optional<Pizza> myPizza = repository.findById(pizza.id());
        myPizza.ifPresent(data -> data.updatePizza(pizza));
        return new ListarPizza(myPizza.get());    
    }

    public void removeLogicPizza(DeletePizza pizza)
    {   
        if(pizza.id().isPresent() && pizza.name().isPresent())
          {throw new IllegalArgumentException("Illegal Arguments");}

        pizza.id().ifPresent(
             (data) -> {
                repository.findById(data).ifPresent((dataPizza) -> {
                    dataPizza.setAvailable((byte) 0);
                });
        });

        pizza.name().ifPresent(
             (data) -> {
                repository.findByName(data).ifPresent((dataPizza) -> {
                    dataPizza.setAvailable((byte) 0);
                });
        });
    }

    public Integer countPizzaVegan()
    {
        return repository.countByVeganTrue();
    }
    



}
