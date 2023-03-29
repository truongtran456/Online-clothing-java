package com.main.online_clothing_store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.main.online_clothing_store.models.Product;
import com.main.online_clothing_store.repositories.ProductRepository;

@Service
public class ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getNewArrivalProducts() {
        return productRepository.findTop4ByIsActivedOrderByCreatedAtDesc(true);
    }

    public List<Product> getSaleProducts() {
        return productRepository.findTop4ByIsActivedOrderByDiscountPercentDesc(true);
    }

    public Long getTotalProduct(){
        return productRepository.count();
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    public Optional<Product> findByIdAndIsActived(int id) {
        return productRepository.findByIdAndIsActived(id, true);
    }
    
    public Page<Product> getAllProducts(String name, Integer pageSize, Integer offset, String filterBy, String sortBy) {
        Page<Product> products = null;
        Sort canSort = null;
        switch(sortBy) {
            case "priceLow":{
                canSort = Sort.by(Sort.Direction.DESC, "discountPrice");
                break;
            }
            case "priceHigh":{
                canSort = Sort.by(Sort.Direction.ASC, "discountPrice");
                break;
            }
            default: canSort = Sort.by(Sort.Direction.DESC, "createdAt");
        }
        switch(filterBy){
            case "newArrival":{
                products = productRepository.findByNameContainingIgnoreCaseAndIsActived(PageRequest.of(offset - 1, pageSize, canSort), name, true);
                break;
            }
            case "onSale":{
                products = productRepository.findByDiscountPercentGreaterThanAndNameContainingIgnoreCaseAndIsActived(PageRequest.of(offset - 1, pageSize, canSort), 0, name, true);
                break;
            }
            default: products = productRepository.findByNameContainingIgnoreCaseAndIsActived(PageRequest.of(offset - 1, pageSize, canSort), name, true);
        }
        return products;
    }
    
    public List<Product> findTop4ByIdNotAndProductCategoryIdAndGender(Integer id, Integer productCategoryId, Boolean gender){
        return productRepository.findTop4ByIdNotAndProductCategoryIdAndGenderAndIsActived(id, productCategoryId, gender, true);
    }
}
