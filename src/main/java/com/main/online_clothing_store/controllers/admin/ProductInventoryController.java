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

import com.main.online_clothing_store.models.Product;
import com.main.online_clothing_store.models.ProductInventory;
import com.main.online_clothing_store.services.ProductInventoryService;
import com.main.online_clothing_store.services.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/product/inventory")
@SessionAttributes("productInventory")
public class ProductInventoryController {
    ProductService productService;
    ProductInventoryService productInventoryService;

    @Autowired
    public ProductInventoryController(ProductInventoryService productInventoryService, ProductService productService) {
        this.productService = productService;
        this.productInventoryService = productInventoryService;
    }

    @GetMapping({ "", "/", "/list" })
    public String list(Model model) {
        List<ProductInventory> productInventories = productInventoryService.getAllProductInventories();
        model.addAttribute("productInventories", productInventories);
        return "admin/inventory/list";
    }

    @GetMapping("/add")
    public String add(Principal principal, Model model) {
        if (principal == null) {
            return "authentication/login";
        }
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("productInventory", new ProductInventory());
        return "admin/inventory/add";
    }

    @PostMapping("/add")
    public String add(Principal principal, @ModelAttribute("productInventory") @Valid ProductInventory productInventory,
            BindingResult bindingResult, HttpServletRequest request, Model model, Errors errors) {
        if (principal == null) {
            return "redirect:/login";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("productInventory", productInventory);
            model.addAttribute("message", "Can not create product inventory");
        } else {
            try {
                productInventoryService.create(productInventory);
                return "redirect:/admin/product/inventory";
            } catch (ObjectNotFoundException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
                model.addAttribute("productInventory", productInventory);
                model.addAttribute("message", e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                model.addAttribute("productInventory", productInventory);
                model.addAttribute("message", "Can not create product inventory");
            }
        }
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/inventory/add";
    }
    @GetMapping("/edit/{id}")
    public String edit(Principal principal, @PathVariable("id") Integer id, Model model) {
        if (principal == null) {
            return "redirect:/login";
        } else {
            try {
                Optional<ProductInventory> productInventory = productInventoryService.findById(id);
                model.addAttribute("productInventory", productInventory.get());
                List<Product> products = productService.getAllProducts();
                model.addAttribute("products", products);
                return "admin/inventory/edit";
            } catch (Exception e) {
                return "redirect:/admin/product/inventory";
            }
        }
    }
    @PostMapping("/edit")
    public String edit(Principal principal, @ModelAttribute("productInventory") @Valid ProductInventory productInventory,
            BindingResult bindingResult, HttpServletRequest request, Model model, Errors errors) {
        if (principal == null) {
            return "redirect:/login";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("productInventory", productInventory);
            model.addAttribute("message", "Can not update product inventory:" + bindingResult.getAllErrors());
        } else {
            try {
                productInventoryService.update(productInventory);
                return "redirect:/admin/product/inventory";
            } catch (ObjectNotFoundException | IllegalArgumentException e) {
                model.addAttribute("productInventory", productInventory);
                model.addAttribute("message", e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                model.addAttribute("productInventory", productInventory);
                model.addAttribute("message", "Can not update product inventory: " + e.getMessage());
            }
        }
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/product/inventory/edit/" + productInventory.getId();
    }
}
