package com.main.online_clothing_store.services;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.online_clothing_store.models.ProductInventory;
import com.main.online_clothing_store.repositories.ProductInventoryRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductInventoryService {
    ProductInventoryRepository productInventoryRepository;

    @Autowired
    public ProductInventoryService(ProductInventoryRepository productInventoryRepository) {
        this.productInventoryRepository = productInventoryRepository;
    }

    public List<ProductInventory> getAllProductInventories() {
        return productInventoryRepository.findAll();
    }

    @Transactional
    public ProductInventory create(ProductInventory productInventory) throws Exception {
        Date currentTime = new Date();
        productInventory.setCreatedAt(currentTime);
        productInventory.setModifiedAt(currentTime);
        return productInventoryRepository.save(productInventory);
    }

    public Optional<ProductInventory> findById(Integer id) {
        return productInventoryRepository.findById(id);
    }

    @Transactional
    public ProductInventory update(ProductInventory productInventory) throws IllegalArgumentException, IOException {
        Optional<ProductInventory> productInventoryUpdateOptional = productInventoryRepository
                .findById(productInventory.getId());
        if (productInventoryUpdateOptional.isPresent()) {
            Date currentTime = new Date();
            productInventory.setCreatedAt(currentTime);
            productInventory.setModifiedAt(currentTime);
            return productInventoryRepository.save(productInventory);
        }
        return productInventory;
    }
}
