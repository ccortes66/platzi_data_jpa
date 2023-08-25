package com.platzi.pizzeria.server;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HelloController 
{
    @GetMapping("/index")
    public String index(Model model, HttpServletResponse response)
    {
        return "index";
    }

}
