package com.platzi.pizzeria.web.controller;

import com.platzi.pizzeria.persistence.DTO.pizza.DeletePizza;
import com.platzi.pizzeria.persistence.DTO.pizza.ListarPizza;
import com.platzi.pizzeria.persistence.DTO.pizza.UpdatePizza;
import com.platzi.pizzeria.persistence.model.Pizza;
import com.platzi.pizzeria.service.PizzaService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pizza")
public class PizzaController
{
    private final PizzaService service;

    @Autowired
    public PizzaController(PizzaService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<ListarPizza>> listAllPizza(@RequestParam(defaultValue = "0") int pagina)
    {  
       return ResponseEntity.ok(service.geListAll(pagina));
    }

    @GetMapping("/top/{price}")
    public ResponseEntity<List<ListarPizza>> topThreePizza(@PathVariable BigDecimal price)
    {  
       return ResponseEntity.ok(service.getPizzaByBestPrice(price));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ListarPizza> getPizzaByName(@PathVariable Long id)
    {
        return ResponseEntity.ok(service.getPizzaById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ListarPizza> getPizzaByName(@PathVariable String name)
    {
        return ResponseEntity.ok(service.getPizza(name));
    }

    @GetMapping("/name/contain/{name}")
    public ResponseEntity<List<ListarPizza>> getPizzaByContaingName(@PathVariable String name)
    {   System.out.println(service.countPizzaVegan());
        return ResponseEntity.ok(service.geListAllByName(name));
    }

    @PostMapping("/guardar")
    @Transactional
    public ResponseEntity<ListarPizza> savePizza(@RequestBody @Valid ListarPizza pizza,
                                                 UriComponentsBuilder uriComponentsBuilder)
    {  
       Pizza myPizza = service.savePizza(pizza);  
       URI uri = uriComponentsBuilder.path("pizza/id/{id}")
                                           .buildAndExpand(myPizza.getId())
                                           .toUri();

       return ResponseEntity.created(uri).body(pizza);
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<ListarPizza> updatePizza(@RequestBody @Valid UpdatePizza pizza)
    {
        return ResponseEntity.ok(service.updatePizza(pizza));
    }

    @PutMapping("/delete")
    @Transactional
    public ResponseEntity<ListarPizza> deletePizza(@RequestBody DeletePizza pizza)
    {   
        service.removeLogicPizza(pizza);
        return ResponseEntity.noContent().build();
    }
}
