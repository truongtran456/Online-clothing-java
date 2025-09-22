package com.main.online_clothing_store.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.main.online_clothing_store.models.CartItem;
import com.main.online_clothing_store.models.composite_primary_keys.CartItemId;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemId>{
    public List<CartItem> findByIdUserId(Integer userId);

    public Optional<CartItem> findByIdUserIdAndIdProductInventoryId(Integer userId, Integer productInventoryId);
}
