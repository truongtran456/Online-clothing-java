package com.main.online_clothing_store.controllers.admin;

import java.io.Console;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.main.online_clothing_store.models.Product;
import com.main.online_clothing_store.models.ProductCategory;
import com.main.online_clothing_store.services.ProductCategoryService;
import com.main.online_clothing_store.services.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/product")
@SessionAttributes("product")
public class ProductController {
    ProductService productService;
    ProductCategoryService productCategoryService;

    @Autowired
    public ProductController(ProductService productService, ProductCategoryService productCategoryService) {
        this.productService = productService;
        this.productCategoryService = productCategoryService;
    }

    @GetMapping({ "", "/", "/list" })
    public String list(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/product/list";
    }

    @GetMapping("/add")
    public String add(Principal principal, Model model) {
        if (principal == null) {
            return "authentication/login";
        }
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategory();
        model.addAttribute("productCategories", productCategories);
        model.addAttribute("product", new Product());
        return "admin/product/add";
    }

    @PostMapping("/add")
    public String add(Principal principal, @ModelAttribute("product") @Valid Product product,
            BindingResult bindingResult, HttpServletRequest request, Model model, Errors errors) {
        if (principal == null) {
            return "redirect:/login";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("message", "Can not create product");
        } else {
            try {
                productService.create(product);
                return "redirect:/admin/product";
            } catch (ObjectNotFoundException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
                model.addAttribute("prodcut", product);
                model.addAttribute("message", e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                model.addAttribute("product", product);
                model.addAttribute("message", "Can not create product");
            }
        }
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategory();
        model.addAttribute("productCategories", productCategories);
        return "admin/product/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(Principal principal, @PathVariable("id") Integer id, Model model) {
        if (principal == null) {
            return "redirect:/login";
        } else {
            try {
                Optional<Product> product = productService.findById(id);
                model.addAttribute("product", product.get());
                List<ProductCategory> productCategories = productCategoryService.getAllProductCategory();
                model.addAttribute("productCategories", productCategories);
                return "admin/product/edit";
            } catch (Exception e) {
                return "admin/product/";
            }
        }
    }

    @PostMapping("/edit")
    public String edit(Principal principal, @ModelAttribute("product") @Valid Product product,
            BindingResult bindingResult, HttpServletRequest request, Model model, Errors errors) {
        if (principal == null) {
            return "redirect:/login";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("message", "Can not update product:" + bindingResult.getAllErrors());
        } else {
            try {
                productService.update(product);
                return "redirect:/admin/product/";
            } catch (ObjectNotFoundException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
                model.addAttribute("product", product);
                model.addAttribute("message", e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                model.addAttribute("product", product);
                model.addAttribute("message", "Can not update product: " + e.getMessage());
            }
        }
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategory();
        model.addAttribute("productCategories", productCategories);
        return "admin/product/edit/" + product.getId();
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id) {
        return "admin/product/detail";
    }
}
