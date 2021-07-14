package ru.gb.market.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    // GET http://localhost:4444/market/nav
    @GetMapping("/nav")
    public String showNavigation() {
        return "nav";
    }
}
