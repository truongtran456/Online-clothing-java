package com.main.online_clothing_store.controllers.user;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.main.online_clothing_store.models.User;
import com.main.online_clothing_store.services.CartItemService;
import com.main.online_clothing_store.services.UserService;

@Controller
@RequestMapping("/user/cart")
public class CartController {
    @Autowired
    CartItemService cartItemService;

    @Autowired
    UserService userService;

    @PostMapping("/add-to-cart")
    public String addToCart(Principal principal, @RequestParam("id") Integer id, @RequestParam("size") String size, @RequestParam("color") String color , @RequestParam("quantity") Integer quantity, final RedirectAttributes redirectAttributes){
        if(principal == null){
            return "redirect:/login";
        }
        Optional<User> user = userService.findByEmail(principal.getName());
        try{
            if(cartItemService.addToCart(user.get().getId(), id, size, color, quantity)){
                redirectAttributes.addFlashAttribute("message", "Added to cart");
            }
            else{
                redirectAttributes.addFlashAttribute("message", "Quantity must be greater than 1");
            }
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/products/single-product/" + id;
    }
}
