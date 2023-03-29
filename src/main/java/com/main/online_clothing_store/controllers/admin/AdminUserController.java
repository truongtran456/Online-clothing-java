package com.main.online_clothing_store.controllers.admin;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
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

import com.main.online_clothing_store.models.Coupon;
import com.main.online_clothing_store.models.OrderDetail;
import com.main.online_clothing_store.models.OrderItem;
import com.main.online_clothing_store.models.Product;
import com.main.online_clothing_store.models.ProductInventory;
import com.main.online_clothing_store.models.User;
import com.main.online_clothing_store.services.CouponService;
import com.main.online_clothing_store.services.OrderDetailService;
import com.main.online_clothing_store.services.OrderItemService;
import com.main.online_clothing_store.services.ProductInventoryService;
import com.main.online_clothing_store.services.ProductService;
import com.main.online_clothing_store.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/user")
@SessionAttributes("user")
public class AdminUserController {
    UserService userService;
    OrderDetailService orderDetailService;
    OrderItemService orderItemService;
    @Autowired
    public AdminUserController(UserService userService, OrderDetailService orderDetailService, OrderItemService orderItemService ) {
        this.userService = userService;
        this.orderDetailService = orderDetailService;
        this.orderItemService = orderItemService;
    }
    @GetMapping({ "", "/", "/list" })
    public String list(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/list";
    }
    @GetMapping("/info/{id}")
    public String edit(Principal principal, @PathVariable("id") Integer id, Model model) {
        if (principal == null) {
            return "redirect:/login";
        } else {
            try {
                Optional<User> user = userService.findById(id);
                model.addAttribute("user", user.get());
                Long totalMoney = orderDetailService.sumTotalByMonthAndStatusAndUserId(2,id);
                model.addAttribute("totalMoney", totalMoney);
                Long totalPurchased = orderDetailService.getTotalPurchasedByUserId(id);
                model.addAttribute("totalPurchased", totalPurchased);
                Long totalInOrder = orderDetailService.getTotalInOrderByUserId(id);
                model.addAttribute("totalInOrder", totalInOrder);
                List<OrderDetail> orderDetails = orderDetailService.getOrderByUserId(id);
                model.addAttribute("orderDetails", orderDetails);
                List<OrderItem> orderItems = orderItemService.getListPurchased(id);
                model.addAttribute("orderItems", orderItems);
                return "admin/user/profile";
            } catch (Exception e) {
                return "redirect:/admin/";
            }
        }
    }
}
