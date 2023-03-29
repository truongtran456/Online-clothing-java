package com.main.online_clothing_store.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.main.online_clothing_store.models.OrderDetail;
import com.main.online_clothing_store.models.OrderItem;
import com.main.online_clothing_store.models.User;
import com.main.online_clothing_store.services.OrderDetailService;
import com.main.online_clothing_store.services.OrderItemService;
import com.main.online_clothing_store.services.UserService;

@Controller
@RequestMapping("/order-tracking")
public class OrderController {
    UserService userService;
    OrderDetailService orderDetailService;
    OrderItemService orderItemService;

    @Autowired
    public OrderController(UserService userService, OrderDetailService orderDetailService, OrderItemService orderItemService) {
        this.userService = userService;
        this.orderDetailService = orderDetailService;
        this.orderItemService = orderItemService;
    }

    @GetMapping("/order-detail")
    public String orderDetail(Integer orderId, String email, Model model){
        if(orderId != null && email != null && !email.isBlank()){
            Optional<OrderDetail> orderDetail = orderDetailService.findById(orderId);
            if(orderDetail.isPresent()){
                if(orderDetail.get().getUser().getEmail().equals(email)){
                    List<OrderItem> orderItems = orderItemService.findByOrderDetailId(orderId);
                    model.addAttribute("orderItems", orderItems);
                    model.addAttribute("order", orderDetail.get());
                    model.addAttribute("message", null);
                    return "application/order_detail";
                }
            }
            model.addAttribute("orderItems", null);
            model.addAttribute("order", null);
            model.addAttribute("message", "Order not found!");
            return "application/order_detail";
        }
        model.addAttribute("orderItems", null);
        model.addAttribute("order", null);
        model.addAttribute("message", "Please full fill information!");
        return "application/order_detail";
    }
}
