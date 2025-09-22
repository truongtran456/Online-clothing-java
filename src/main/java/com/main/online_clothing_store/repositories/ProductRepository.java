package com.main.online_clothing_store.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.online_clothing_store.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Optional<Product> findByIdAndIsActived(Integer id, Boolean isActived);
    public List<Product> findTop4ByIsActivedOrderByCreatedAtDesc(Boolean isActived);
    public List<Product> findTop4ByIsActivedOrderByDiscountPercentDesc(Boolean isActived);
    public List<Product> findTop4ByIdNotAndProductCategoryIdAndGenderAndIsActived(Integer id, Integer productCategoryId, Boolean gender, Boolean isActived);
    public Page<Product> findByNameContainingIgnoreCaseAndIsActived(PageRequest pageRequest, String name, Boolean isActived);
    public Page<Product> findByDiscountPercentGreaterThanAndNameContainingIgnoreCaseAndIsActived(PageRequest pageRequest, Integer discountPercent, String name, Boolean isActived);
    public Page<Product> findByProductCategoryIdAndNameContainingIgnoreCaseAndIsActived(PageRequest pageRequest, Integer productCategoryId, String name, Boolean isActived);
}
