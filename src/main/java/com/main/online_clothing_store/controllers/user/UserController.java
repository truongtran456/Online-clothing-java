package com.main.online_clothing_store.controllers.user;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.main.online_clothing_store.models.OrderDetail;
import com.main.online_clothing_store.models.OrderItem;
import com.main.online_clothing_store.models.User;
import com.main.online_clothing_store.services.OrderDetailService;
import com.main.online_clothing_store.services.OrderItemService;
import com.main.online_clothing_store.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {
    UserService userService;
    OrderDetailService orderDetailService;
    OrderItemService orderItemService;

    @Autowired
    public UserController(UserService userService, OrderDetailService orderDetailService, OrderItemService orderItemService) {
        this.userService = userService;
        this.orderDetailService = orderDetailService;
        this.orderItemService = orderItemService;
    }

    @GetMapping({"/", "/my-account"})
    public String myAccount(Principal principal, Model model){
        if(principal == null){
            return "redirect:/login";
        }
        Optional<User> user = userService.findByEmail(principal.getName());
        List<OrderDetail> orders = orderDetailService.findByUserId(user.get().getId());
        model.addAttribute("user", user.get());
        model.addAttribute("avatar", user.get().getAvatar());
        model.addAttribute("message", null);
        model.addAttribute("orders", orders);
        return "user/my_account";
    }
    @PostMapping("/update-account")
    public String updateAccount(Principal principal, @ModelAttribute("user") @Valid User user, BindingResult bindingResult, HttpServletRequest request, Model model, Errors errors){
        if(principal == null){
            return "redirect:/login";
        }
        if(!user.getNewPassword().isBlank() && !userService.isValidPassword(user.getNewPassword())){
            bindingResult.rejectValue("newPassword", "error.user", "Password must be at least 8 and up to 10 characters, one uppercase letter, one lowercase letter, one number and one special character");
        }
        if(!bindingResult.hasErrors()){
            try {
                userService.update(user);
                return "redirect:/user/my-account";
            }
            catch(ObjectNotFoundException | IllegalArgumentException e){
                model.addAttribute("message", e.getMessage());
            }
            catch(Exception e){
                model.addAttribute("message", "Can not update your account");
            }
        }
        model.addAttribute("user", user);
        model.addAttribute("avatar", user.getAvatar());
        return "user/my_account";
    }
    @GetMapping("/update-account")
    public String updateAccount(Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        return "redirect:/user/my-account";
    }
    @GetMapping("/order-detail/{id}")
    public String orderDetail(Principal principal, @PathVariable (required = true) Integer id, Model model){
        if(principal == null){
            return "redirect:/login";
        }
        Optional<OrderDetail> orderDetail = orderDetailService.findById(id);
        if(orderDetail.isPresent()){
            List<OrderItem> orderItems = orderItemService.findByOrderDetailId(id);
            model.addAttribute("orderItems", orderItems);
            model.addAttribute("order", orderDetail.get());
            return "user/order_detail";
        }
        return "redirect:/404";
    }

    @GetMapping("/order-detail/{id}/cancel")
    public String cancelOrderDetail(Principal principal, @PathVariable (required = true) Integer id){
        if(principal == null){
            return "redirect:/login";
        }
        if(orderDetailService.cancelOrderDetail(id)){
            return "redirect:/user/order-detail/" + id;
        }
        return "redirect:/404";
    }
}
