package com.main.online_clothing_store.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.online_clothing_store.models.ProductInventory;

@Repository
public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Integer> {
    @Query(value = "SELECT DISTINCT color FROM ProductInventories WHERE productId = :productId", nativeQuery = true)
    public List<String> findDistinctColorsByProductId(int productId);

    @Query(value = "SELECT DISTINCT size FROM ProductInventories WHERE productId = :productId", nativeQuery = true)
    public List<String> findDistinctSizesByProductId(int productId);

    public Optional<ProductInventory> findTop1ByProductIdAndSizeAndColor(Integer productId, String size, String color);

}
