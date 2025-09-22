package com.main.online_clothing_store.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.main.online_clothing_store.models.Product;
import com.main.online_clothing_store.services.ProductService;


@Controller
public class ApplicationController {
    @Autowired
    ProductService productService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        List<Product> newArrivalProducts = productService.getNewArrivalProducts();
        List<Product> saleProducts = productService.getSaleProducts();
        model.addAttribute("newArrivalProducts", newArrivalProducts);
        model.addAttribute("saleProducts", saleProducts);
        return "application/home";
    }
    @GetMapping("/products")
    public String products(@RequestParam(required = false) String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) String filterBy, @RequestParam(required = false) String sortBy, Model model){
        if(page == null || page < 1){
            page = 1;
        }
        if(filterBy == null || filterBy.isBlank()){
            filterBy = "all";
        }
        if(sortBy == null || sortBy.isBlank()){
            sortBy = "newest";
        }
        if(name == null || name.isBlank()){
            name = "";
        }
        else{
            name = name.replaceAll("%20", " ").trim();
        }
        Page<Product> products = productService.getAllProducts(name, 12, page, filterBy, sortBy);
        Integer totalPage = products.getTotalPages();
        if(page > totalPage){
            page = totalPage;
        }

        model.addAttribute("products", products.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("filterBy", filterBy);
        model.addAttribute("sortBy", sortBy);
        return "application/products";
    }
    @GetMapping("/order-tracking")
    public String orderTracking(){
        return "application/order_tracking";
    }
    @GetMapping("/about-us")
    public String about_us(){
        return "application/about_us";
    }
    @GetMapping("/contact")
    public String contact(){
        return "application/contact";
    }
}
