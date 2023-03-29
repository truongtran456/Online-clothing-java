package com.main.online_clothing_store.controllers.admin;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.main.online_clothing_store.models.AdminUser;
import com.main.online_clothing_store.models.Role;
import com.main.online_clothing_store.models.RoleAdminUser;
import com.main.online_clothing_store.services.AdminUserService;
import com.main.online_clothing_store.services.RoleAdminUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/role")
@SessionAttributes("roleAdminUser")
public class RoleAdminUserController {
    AdminUserService adminUserService;
    RoleAdminUserService roleAdminUserService;

    @Autowired
    public RoleAdminUserController( RoleAdminUserService roleAdminUserService,AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
        this.roleAdminUserService = roleAdminUserService;
    }
    @GetMapping({ "", "/" })
    public String role(Model model){
        List<RoleAdminUser> roleAdminUsers = roleAdminUserService.getAllAdminUsers();
        model.addAttribute("roleAdminUsers", roleAdminUsers);
        return "admin/role/list";
    }
    @GetMapping("/add")
    public String addRole(Principal principal, Model model) {
        if (principal == null) {
            return "authentication/login";
        }
        model.addAttribute("roleAdminUser", new RoleAdminUser());
        List<AdminUser> adminUsers = adminUserService.getAllAdminUsers();
        model.addAttribute("adminUsers",adminUsers);
        List<Role> roles = adminUserService.getAllRoles();
        model.addAttribute("roles",roles);   
        return "admin/role/add";
    }
    @PostMapping("/add")
    public String addRole(Principal principal,@RequestParam("adminUserId") Integer adminUserId,@RequestParam("roleId") Integer roleId, Model model ){
        if (principal == null) {
            return "authentication/login";
        }
        model.addAttribute("roleAdminUser", new RoleAdminUser());
        List<AdminUser> adminUsers = adminUserService.getAllAdminUsers();
        model.addAttribute("adminUsers",adminUsers);
        List<Role> roles = adminUserService.getAllRoles();
        model.addAttribute("roles",roles);   
        try {
            
            roleAdminUserService.create(adminUserId,roleId);
            return "redirect:/admin/role";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("messages", "Role admin has already existed");
            return "admin/role/add";
        }
    }
    @GetMapping("/delete")
    public String deleteRole(Principal principal,@RequestParam Integer adminUserId, @RequestParam Integer roleId , Model model ){
        // Integer adminUserId = Integer.parseInt(data.get("adminUserId").toString());
        // Integer roleId = Integer.parseInt(data.get("roleId").toString());
        System.out.println( "tesst"+adminUserId + ":" + roleId);
        if (principal == null) {
            return "authentication/login";
        }
        try {

            roleAdminUserService.delete(adminUserId,roleId);
            return "redirect:/admin/role";
        } catch (Exception e) {
            model.addAttribute("messages", "Can not delete role, some thing wrong.");
        }
        return "redirect:/admin/role";
    }
    
}
