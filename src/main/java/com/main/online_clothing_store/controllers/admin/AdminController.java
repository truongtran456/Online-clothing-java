package com.main.online_clothing_store.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.online_clothing_store.models.AdminUser;
import com.main.online_clothing_store.services.AdminUserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    AdminUserService adminUserService;

    @Autowired
    public AdminController(AdminUserService adminUserService){
        this.adminUserService = adminUserService;
    }

    @RequestMapping("/list")
    public List<AdminUser> getAllAdminUsers(){
        return adminUserService.getAllAdminUsers();
    } 
}
