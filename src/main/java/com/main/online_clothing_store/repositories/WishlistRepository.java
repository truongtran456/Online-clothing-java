package com.main.online_clothing_store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.online_clothing_store.models.Wishlist;
import com.main.online_clothing_store.models.composite_primary_keys.WishlistId;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, WishlistId> {
    
}
