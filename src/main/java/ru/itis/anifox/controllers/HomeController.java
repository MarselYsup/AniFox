package ru.itis.anifox.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/homepage")
    public String getHomePage() {
        return "home";
    }

    @GetMapping("/")
    public String redirectHomePage() {
        return "redirect:/homepage";
    }

}
