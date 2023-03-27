package com.main.online_clothing_store.services;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.online_clothing_store.models.Product;
import com.main.online_clothing_store.repositories.ProductRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

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
    @Transactional
    public Product create(Product product)  throws Exception{
        if(product.getUploadImage().isEmpty() || product.getUploadImageHover().isEmpty() ){
            throw new IllegalArgumentException("Image is mandatory");
        }
        Date currentTime = new Date();
        
        product.setCreatedAt(currentTime);
        product.setModifiedAt(currentTime);
        product.setImage(Base64.getEncoder().encodeToString(product.getUploadImage().getBytes()));
        product.setImageHover(Base64.getEncoder().encodeToString(product.getUploadImageHover().getBytes()));
        return productRepository.save(product);
    }
    @Transactional
    public Product update(Product product) throws IllegalArgumentException, IOException {

        Optional<Product> productUpdateOptional = productRepository.findById(product.getId());
        if(productUpdateOptional.isPresent()){
            if(!product.getUploadImage().isEmpty() ){
                product.setImage(Base64.getEncoder().encodeToString(product.getUploadImage().getBytes()));
            }
            if(!product.getUploadImageHover().isEmpty() ){
                product.setImageHover(Base64.getEncoder().encodeToString(product.getUploadImageHover().getBytes()));
            }
            Date currentTime = new Date();
            product.setCreatedAt(currentTime);
            product.setModifiedAt(currentTime);
            return productRepository.save(product);
        }
        return product;
    }
}
