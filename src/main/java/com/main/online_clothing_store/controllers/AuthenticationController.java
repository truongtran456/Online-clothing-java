package com.main.online_clothing_store.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.main.online_clothing_store.models.User;
import com.main.online_clothing_store.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class AuthenticationController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(Principal principal) {
        return principal == null ? "authentication/login" : "redirect:/";
    }

    @GetMapping("/register")
    public String register(Principal principal, Model model) {
        if (principal == null) {
            model.addAttribute("user", new User());
            return "authentication/register";
        }
        return "redirect:/";
    }

    @PostMapping("/register")
    public String register(Principal principal, @ModelAttribute("user") @Valid User user, BindingResult bindingResult, HttpServletRequest request, Model model, Errors errors){
        if(principal == null){
            if(!userService.isValidRetypePassword(user.getPassword(), user.getRetypePassword())){
                bindingResult.rejectValue("retypePassword", "error.user", "Passwords do not match");
            }
            if(!userService.isValidPassword(user.getPassword())){
                bindingResult.rejectValue("password", "error.user", "Password must be at least 8 and up to 10 characters, one uppercase letter, one lowercase letter, one number and one special character");
            }
            if(bindingResult.hasErrors()){
                model.addAttribute("user", user);
                return "authentication/register";
            }
            try {
                user = userService.create(user);
                return "redirect:/login";
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                model.addAttribute("user", user);
                model.addAttribute("errorMessage", "Username or telephone has already been taken");
                return "authentication/register";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Principal principal) {
        return principal == null ? "authentication/forgot_password" : "redirect:/";
    }

    @PostMapping("/send-new-password")
    public String sendNewPassword(Principal principal, String email, Model model) {
        if(principal == null){
            try {
                if(userService.sendNewPassword(email)){
                    model.addAttribute("message", "We have sent you a new password to your email address. Please check your email.");
                }
                else{
                    model.addAttribute("message", "Error!");
                }
            }
            catch(Exception e){
                model.addAttribute("message", "Email does not exist.");
            }
            return "redirect:/forgot-password";
        }
        return "redirect:/";
    }
}
