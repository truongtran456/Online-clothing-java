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

import com.main.online_clothing_store.models.OrderDetail;
import com.main.online_clothing_store.models.OrderItem;
import com.main.online_clothing_store.models.Product;
import com.main.online_clothing_store.models.ProductInventory;
import com.main.online_clothing_store.services.OrderDetailService;
import com.main.online_clothing_store.services.OrderItemService;
import com.main.online_clothing_store.services.ProductInventoryService;
import com.main.online_clothing_store.services.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/order-detail")
@SessionAttributes("orderDetail")
public class OrderDetailController {
    OrderDetailService orderDetailService;
    OrderItemService orderItemService;
    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService, OrderItemService orderItemService) {
        this.orderDetailService = orderDetailService;
        this.orderItemService = orderItemService;
    }
    @GetMapping({ "", "/", "/list" })
    public String list(Model model) {
        List<OrderDetail> orderDetails = orderDetailService.getAllOrderDetails();
        model.addAttribute("orderDetails", orderDetails);
        return "admin/orderdetail/list";
    }
    @GetMapping("/info/{id}")
    public String edit(Principal principal, @PathVariable("id") Integer id, Model model) {
        if (principal == null) {
            return "redirect:/login";
        } else {
            try {
                Optional<OrderDetail> orderDetail = orderDetailService.findById(id);
                model.addAttribute("orderDetail", orderDetail.get());
                List<OrderItem> orderItems = orderItemService.getOrderItemByOrderDetailId(id);
                model.addAttribute("orderItems", orderItems);
                return "admin/orderdetail/info";
            } catch (Exception e) {
                return "admin/orderdetail/list";
            }
        }
    }
    


}
