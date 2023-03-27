package com.main.online_clothing_store.controllers.admin;

import java.sql.Array;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.online_clothing_store.models.AdminUser;
import com.main.online_clothing_store.services.AdminUserService;
import com.main.online_clothing_store.services.OrderDetailService;
import com.main.online_clothing_store.services.ProductService;
import com.main.online_clothing_store.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    AdminUserService adminUserService;
    UserService userService;
    ProductService productService;
    OrderDetailService orderDetailService;

    @Autowired
    public AdminController(AdminUserService adminUserService, UserService userService, ProductService productService, OrderDetailService orderDetailService) {
        this.adminUserService = adminUserService;
        this.userService = userService;
        this.productService = productService;
        this.orderDetailService = orderDetailService;
    }

    @RequestMapping("/list")
    public List<AdminUser> getAllAdminUsers(){
        return adminUserService.getAllAdminUsers();
    }
    
    @GetMapping({"", "/", "/dashboard"})
    public String dashboard(Model model){
        Long totalUsers = userService.getTotalUser();
        model.addAttribute("totalUsers", totalUsers);
        Long totalProduct = productService.getTotalProduct();
        model.addAttribute("totalProduct", totalProduct);
        Long totalOrderDetail = orderDetailService.getTotalOrderDetail();
        model.addAttribute("totalOrderDetail", totalOrderDetail);
        Long totalMoney = orderDetailService.sumTotalByStatusOrderDetail(2);
        model.addAttribute("totalMoney", totalMoney);
        int currentYear = Year.now().getValue();
        int[] listSumByMonth = new int[12];
        for(int i = 1; i <= 12; i++){
            listSumByMonth[i-1] = orderDetailService.sumTotalByMonthAndStatus(2, currentYear, i).orElse(0);
        }
        model.addAttribute("listSumByMonth", listSumByMonth);
        return "admin/dashboard/dashboard";
    }
   
    @GetMapping("/users")
    public String user(){
        return "admin/user/list";
    }
    @GetMapping("/user-profile")
    public String userProfile(){
        return "admin/user/profile";
    }
}
