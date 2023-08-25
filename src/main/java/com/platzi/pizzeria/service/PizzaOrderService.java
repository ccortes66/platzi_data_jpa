package com.platzi.pizzeria.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platzi.pizzeria.persistence.DTO.pizza_order.PizzaOrderEntity;
import com.platzi.pizzeria.persistence.DTO.pizza_order.RandomOrdenDTO;
import com.platzi.pizzeria.persistence.model.PizzaOrder;
import com.platzi.pizzeria.persistence.projection.ResultOrderSummary;
import com.platzi.pizzeria.persistence.repository.PizzaOrderRepository;

@Service
public class PizzaOrderService 
{
    private final PizzaOrderRepository repository;
    
    @Autowired
    public PizzaOrderService(PizzaOrderRepository repository)
    {
        this.repository = repository;
    }

    public List<PizzaOrderEntity> getAll()
    {
        return repository.findAll().stream()
                                          .map(PizzaOrderEntity::new)
                                          .toList();
    }

    public List<PizzaOrderEntity> getByDate()
    {   
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return repository.findAllByDateAfter(today).stream()
                                                   .map(PizzaOrderEntity::new)
                                                   .toList();
    }

    public List<PizzaOrderEntity> getByOuterOrder()
    {   
        List<PizzaOrder.Method> methods = Arrays.asList(PizzaOrder.Method.C,
                                                        PizzaOrder.Method.D);
                                                        
        return repository.findAllByMethodIn(methods).stream()
                                                    .map(PizzaOrderEntity::new)
                                                    .toList();
    }

    public List<PizzaOrderEntity> getByCustomerOrder(String dni)
    {   
    
        return repository.findByCustomerDni(dni).stream()
                                                 .map(PizzaOrderEntity::new)
                                                 .toList();
    }

    public ResultOrderSummary getInfoSumarry(Long id)
    {   
        return repository.findSummary(id);
        
    }

    public Boolean saveRandonOrder(RandomOrdenDTO dto)
    {
        return repository.saveRamdomOrder(dto.dni(), dto.option());
    }

    


}
