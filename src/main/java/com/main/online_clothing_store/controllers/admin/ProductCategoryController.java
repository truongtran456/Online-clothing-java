package com.main.online_clothing_store.controllers.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.online_clothing_store.models.ProductCategory;
import com.main.online_clothing_store.services.ProductCategoryService;

@Controller
@RequestMapping("/admin/category")
public class ProductCategoryController {
    ProductCategoryService productCategoryService;

    @Autowired
    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService; 
    }
    @GetMapping({"/","/list"})
    public String list(Model model){
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategory();
        model.addAttribute("productCategories", productCategories);
        return "admin/category/list";
    }
    @GetMapping("/add")
    public String add(){
        return "admin/category/add";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable ("id") Integer id, Model model){
        try {
            Optional<ProductCategory> productCategory = productCategoryService.findById(id);
            model.addAttribute("productCategory", productCategory.get());
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "admin/category/edit";
    }
    @PostMapping("/save")
	public String saveUser(@ModelAttribute("ProductCategory") ProductCategory productCategory) {
		if (productCategory.getId() == 0) {
			
		} else {
			
		}
		return "redirect:/admin/category/";
	}

}
