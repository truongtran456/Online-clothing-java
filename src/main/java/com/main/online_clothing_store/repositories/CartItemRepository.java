package com.main.online_clothing_store.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.main.online_clothing_store.models.CartItem;
import com.main.online_clothing_store.models.composite_primary_keys.CartItemId;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemId>{
    // @Query(value = "INSERT INTO CartItems (userId, productInventoryId, quantity, createdAt, modifiedAt) VALUES (:userId, :productInventoryId, :quantity, :createdAt, :modifiedAt)", nativeQuery = true)
    // public void insertIntoCartItems(@Param("userId") Integer userId, @Param("productInventoryId") Integer productInventoryId, @Param("quantity") Integer quantity, @Param("createdAt") String createdAt, @Param("modifiedAt") String modifiedAt);
}
