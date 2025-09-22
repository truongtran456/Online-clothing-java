package com.main.online_clothing_store.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.online_clothing_store.models.OrderItem;
import com.main.online_clothing_store.models.Product;
import com.main.online_clothing_store.services.OrderItemService;
import com.main.online_clothing_store.services.ProductInventoryService;
import com.main.online_clothing_store.services.ProductService;
import com.main.online_clothing_store.services.UserService;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductInventoryService productInventoryService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    UserService userService;

    @GetMapping("/single-product/{id}")
    public String singleProduct(Principal principal, @PathVariable(required = true) Integer id, Model model){
        Optional<Product> product = productService.findByIdAndIsActived(id);
        if(product.isPresent()){
            List<String> colors = productInventoryService.findDistinctColorByProductId(product.get().getId());
            List<String> sizes = productInventoryService.findDistinctSizeByProductId(product.get().getId());
            List<OrderItem> orderItems = orderItemService.findByCommentIsNotNullAndIdProductInventoryIdIn(product.get());
            List<Product> relatedProducts = productService.findTop4ByIdNotAndProductCategoryIdAndGender(product.get().getId(), product.get().getProductCategory().getId(), product.get().getGender());
            Integer maxQuantity = product.get().getProductInventories().stream().mapToInt(p -> p.getQuantity()).sum();
            model.addAttribute("colors", colors);
            model.addAttribute("sizes", sizes);
            model.addAttribute("product", product.get());
            model.addAttribute("maxQuantity", maxQuantity);
            model.addAttribute("orderItems", orderItems);
            model.addAttribute("relatedProducts", relatedProducts);
            return "application/single_product";
        }
        return "redirect:/404";
    }
}
