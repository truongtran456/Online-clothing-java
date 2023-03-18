package com.main.online_clothing_store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.online_clothing_store.models.ProductInventory;

@Repository
public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Integer> {
    
}
