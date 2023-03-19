package com.main.online_clothing_store.controllers.user;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.online_clothing_store.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping({"/", "/my-account"})
    public String my_account(Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        return "user/my_account";
    }
}
