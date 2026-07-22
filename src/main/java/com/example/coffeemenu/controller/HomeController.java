package com.example.coffeemenu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirectToCoffees() {
        return "redirect:/coffees";
    }
}
