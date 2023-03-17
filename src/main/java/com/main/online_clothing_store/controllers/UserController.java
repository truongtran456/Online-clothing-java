package com.main.online_clothing_store.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.online_clothing_store.models.User;
import com.main.online_clothing_store.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @RequestMapping()
    public String index(){
        return "layout";
    } 

    @RequestMapping("/list")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
