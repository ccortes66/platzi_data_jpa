package com.platzi.pizzeria.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platzi.pizzeria.persistence.DTO.pizza_order.PizzaOrderEntity;
import com.platzi.pizzeria.persistence.DTO.pizza_order.RandomOrdenDTO;
import com.platzi.pizzeria.persistence.projection.ResultOrderSummary;
import com.platzi.pizzeria.service.PizzaOrderService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
public class PizzaOrderController 
{   
    private final PizzaOrderService service;

    @Autowired
    public PizzaOrderController(PizzaOrderService service) 
    {
        this.service = service;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PizzaOrderEntity>> getAll()
    {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/today")
    public ResponseEntity<List<PizzaOrderEntity>> getAllByDate()
    {
        return ResponseEntity.ok(service.getByDate());
    }

    @GetMapping("/outside")
    public ResponseEntity<List<PizzaOrderEntity>> getAllByMethods()
    {
        return ResponseEntity.ok(service.getByOuterOrder());
    }
    
    @Secured("ROLE_ADMIN")
    @GetMapping("/customer/{dni}")
    public ResponseEntity<List<PizzaOrderEntity>> getAllByMethods(@PathVariable String dni)
    {
        return ResponseEntity.ok(service.getByCustomerOrder(dni));
    }
    
    @GetMapping("/summary/customer/{id}")
    public ResponseEntity<ResultOrderSummary> getAllSummary(@PathVariable Long id)
    {   
        System.out.println(service.getInfoSumarry(id));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/random/order")
    @Transactional
    public ResponseEntity<Boolean> saveRandomOrder(@RequestBody @Valid RandomOrdenDTO dto)
    {   
        return ResponseEntity.ok(service.saveRandonOrder(dto));
    }
}
