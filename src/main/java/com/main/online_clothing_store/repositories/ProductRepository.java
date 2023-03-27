package com.main.online_clothing_store.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.online_clothing_store.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public List<Product> findTop4ByIsActivedOrderByCreatedAtDesc(Boolean isActived);
    public List<Product> findTop4ByIsActivedOrderByDiscountPercentDesc(Boolean isActived);
    public Page<Product> findByDiscountPercentGreaterThanAndIsActived( PageRequest pageRequest, Integer discountPercent, Boolean isActived);
    public List<Product> findTop4ByIdNotAndProductCategoryIdAndGenderAndIsActived(Integer id, Integer productCategoryId, Boolean gender, Boolean isActived);
}
