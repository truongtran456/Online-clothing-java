package com.main.online_clothing_store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {
    @GetMapping({"/", "/home"})
    public String home(){
        return "application/home";
    }
    @GetMapping("/shop")
    public String shop(){
        
        return "application/shop";
    }
    @GetMapping("/about-us")
    public String about_us(){
        return "application/about_us";
    }
    @GetMapping("/contact")
    public String contact(){
        return "application/contact";
    }
}
