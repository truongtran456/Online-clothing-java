package com.main.online_clothing_store.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.online_clothing_store.repositories.ProductInventoryRepository;

@Service
public class ProductInventoryService {
    @Autowired
    ProductInventoryRepository productInventoryRepository;
    
    public List<String> findDistinctColorByProductId(Integer id) {
        return productInventoryRepository.findDistinctColorsByProductId(id);
    }

    public List<String> findDistinctSizeByProductId(Integer id) {
        return productInventoryRepository.findDistinctSizesByProductId(id);
    }
}
