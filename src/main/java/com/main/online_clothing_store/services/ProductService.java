package com.main.online_clothing_store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.online_clothing_store.models.Product;
import com.main.online_clothing_store.repositories.ProductRepository;

@Service
public class ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getNewArrivalProducts() {
        return productRepository.findTop4ByOrderByCreatedAtDesc();
    }

    public List<Product> getTrendingProducts() {
        return productRepository.findTop4ByOrderByCreatedAtDesc();
    }
    public Long getTotalProduct(){
        return productRepository.count();
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }
}
