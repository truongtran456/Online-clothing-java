package com.main.online_clothing_store.controllers.admin;

import java.security.Principal;
import java.sql.Array;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
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

import com.main.online_clothing_store.models.AdminUser;
import com.main.online_clothing_store.models.OrderItem;
import com.main.online_clothing_store.models.Role;
import com.main.online_clothing_store.models.RoleAdminUser;
import com.main.online_clothing_store.services.AdminUserService;
import com.main.online_clothing_store.services.OrderDetailService;
import com.main.online_clothing_store.services.OrderItemService;
import com.main.online_clothing_store.services.ProductService;
import com.main.online_clothing_store.services.RoleAdminUserService;
import com.main.online_clothing_store.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
@SessionAttributes("adminUser")
public class AdminController {
    AdminUserService adminUserService;
    UserService userService;
    ProductService productService;
    OrderDetailService orderDetailService;
    OrderItemService orderItemService;
    RoleAdminUserService roleAdminUserService;

    @Autowired
    public AdminController(AdminUserService adminUserService, UserService userService, ProductService productService, OrderDetailService orderDetailService, OrderItemService orderItemService,
    RoleAdminUserService roleAdminUserService) {
        this.adminUserService = adminUserService;
        this.userService = userService;
        this.productService = productService;
        this.orderDetailService = orderDetailService;
        this.orderItemService = orderItemService;
        this.roleAdminUserService = roleAdminUserService;
    }

    @RequestMapping("/list")
    public List<AdminUser> getAllAdminUsers() {
        return adminUserService.getAllAdminUsers();
    }

    @GetMapping({ "", "/", "/dashboard" })
    public String dashboard(Model model) {
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
        for (int i = 1; i <= 12; i++) {
            listSumByMonth[i - 1] = orderDetailService.sumTotalByMonthAndStatus(2, currentYear, i).orElse(0);
        }
        model.addAttribute("listSumByMonth", listSumByMonth);
        int[] listCountOrderByStatus = new int[3];
        for (int i = 0; i < listCountOrderByStatus.length; i++) {
            listCountOrderByStatus[i] = orderDetailService.countOrderDetailByStatus(i).orElse(0);
        }
        model.addAttribute("listCountOrderByStatus", listCountOrderByStatus);
        List<OrderItem> listTop5OrderItems = orderItemService.getTop5OrderItems();
        model.addAttribute("listTop5OrderItems", listTop5OrderItems);
        return "admin/dashboard/dashboard";
    }

    @GetMapping({ "/admin-user", "/admin-user/" })
    public String adminUser(Model model){
        List<AdminUser> adminUsers = adminUserService.getAllAdminUsers();
        model.addAttribute("adminUsers", adminUsers);
        return "admin/adminuser/list";
    }
    @GetMapping("/admin-user/add")
    public String addAdmin(Principal principal, Model model) {
        if (principal == null) {
            return "authentication/login";
        }
        model.addAttribute("adminUser", new AdminUser());
        return "admin/adminuser/add";
    }
    @PostMapping("/admin-user/add")
    public String addAdmin(Principal principal, @ModelAttribute("adminUser") @Valid AdminUser adminUser, BindingResult bindingResult, HttpServletRequest request, Model model, Errors errors){
        if(principal == null){
            return "authentication/login";
        }
        if(!adminUserService.isValidRetypePassword(adminUser.getPassword(), adminUser.getRetypePassword())){
            bindingResult.rejectValue("retypePassword", "error.adminUser", "Passwords do not match");
        }
        if(!userService.isValidPassword(adminUser.getPassword())){
            bindingResult.rejectValue("password", "error.adminUser", "Password must be at least 8 and up to 10 characters, one uppercase letter, one lowercase letter, one number and one special character");
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("adminUser", adminUser);
            return "admin/adminuser/add";
        }
        try {
            adminUser = adminUserService.create(adminUser);
            List<AdminUser> adminUsers = adminUserService.getAllAdminUsers();
            model.addAttribute("adminUsers", adminUsers);
            return "admin/adminuser/list";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("adminUser", adminUser);
            model.addAttribute("messages", "Username or telephone has already been taken");
            return "admin/adminuser/add";
        }
    }
    @GetMapping("/admin-user/{id}")
    public String editAdmin(Principal principal, @PathVariable("id") Integer id, Model model) {
        if (principal == null) {
            return "redirect:/login";
        } else {
            try {
                Optional<AdminUser> adminUser = adminUserService.findById(id);
                model.addAttribute("adminUser", adminUser.get());
                return "admin/adminuser/edit";
            } catch (Exception e) {
                return "admin/adminuser/list";
            }
        }
    }
    @PostMapping("/admin-user/edit")
    public String editAdmin(Principal principal, @ModelAttribute("adminUser") @Valid AdminUser adminUser, BindingResult bindingResult, HttpServletRequest request, Model model, Errors errors) {
        if (principal == null) {
            return "redirect:/login";
        }
        if(!adminUser.getNewPassword().isBlank() && !adminUserService.isValidPassword(adminUser.getNewPassword())){
            bindingResult.rejectValue("newPassword", "error.user", "Password must be at least 8 and up to 10 characters, one uppercase letter, one lowercase letter, one number and one special character");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("adminUser", adminUser);
            model.addAttribute("message", "Can not update admin:" + bindingResult.getAllErrors());
        } else {
            try {
                adminUserService.update(adminUser);
                return "redirect:/admin/admin-user/";
            } catch (ObjectNotFoundException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
                model.addAttribute("adminUser", adminUser);
                model.addAttribute("message", e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                model.addAttribute("adminUser", adminUser);
                model.addAttribute("message", "Can not update admin: " + e.getMessage());
            }
        }
        model.addAttribute("adminUser", adminUser);
        return "admin/adminuser/edit";
    }
    
    @GetMapping("/my-account")
    public String profile(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        } else {
            try {
                Optional<AdminUser> adminUser = adminUserService.findByEmail(principal.getName());
                model.addAttribute("adminUser", adminUser.get());
                return "admin/adminuser/profile";
            } catch (Exception e) {
                return "redirect:/admin";
            }
        }
    }
}
