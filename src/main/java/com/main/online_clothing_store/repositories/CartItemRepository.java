package com.main.online_clothing_store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.online_clothing_store.models.CartItem;
import com.main.online_clothing_store.models.composite_primary_keys.CartItemId;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemId>{
    
}
