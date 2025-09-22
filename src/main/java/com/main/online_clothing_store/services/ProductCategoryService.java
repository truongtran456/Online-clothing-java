package com.main.online_clothing_store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.online_clothing_store.models.ProductCategory;
import com.main.online_clothing_store.repositories.ProductCategoryRepository;
@Service
public class ProductCategoryService {
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }
    public List<ProductCategory> getAllProductCategory() {
        return productCategoryRepository.findAll();
    }
    public Optional<ProductCategory> findById(int id) {
        return productCategoryRepository.findById(id);
    }
}
