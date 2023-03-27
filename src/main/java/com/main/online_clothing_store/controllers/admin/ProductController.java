package com.main.online_clothing_store.controllers.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.online_clothing_store.models.Product;
import com.main.online_clothing_store.services.ProductService;

@Controller("adminProductController")
@RequestMapping("/admin/product")
public class ProductController {
    ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService; 
    }
    @GetMapping({"/","/list"})
    public String list(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/product/list";
    }

    @GetMapping("/add")
    public String add(){
        return "admin/product/add";
    }

    @PostMapping("/add")
    public void add(@RequestBody Product product){
        
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable ("id") Integer id, Model model){
        try {
            Optional<Product> product = productService.findById(id);
            model.addAttribute("product", product.get());
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "admin/product/edit";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id){
        return "admin/product/detail";
    }
}
