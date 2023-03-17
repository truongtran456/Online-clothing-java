package com.main.online_clothing_store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {
    @GetMapping({"/", "/home"})
    public String home(){
        return "application/home";
    }
    
}
