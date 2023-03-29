package com.main.online_clothing_store.services;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.main.online_clothing_store.models.Product;
import com.main.online_clothing_store.repositories.ProductRepository;

import jakarta.transaction.Transactional;

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

    public Long getTotalProduct() {
        return productRepository.count();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product create(Product product) throws Exception {
        if (product.getUploadImage().isEmpty() || product.getUploadImageHover().isEmpty()) {
            throw new IllegalArgumentException("Image is mandatory");
        }
        Date currentTime = new Date();

        product.setCreatedAt(currentTime);
        product.setModifiedAt(currentTime);
        product.setImage(Base64.getEncoder().encodeToString(product.getUploadImage().getBytes()));
        product.setImageHover(Base64.getEncoder().encodeToString(product.getUploadImageHover().getBytes()));
        return productRepository.save(product);
    }

    @Transactional
    public Product update(Product product) throws IllegalArgumentException, IOException {

        Optional<Product> productUpdateOptional = productRepository.findById(product.getId());
        if (productUpdateOptional.isPresent()) {
            if (!product.getUploadImage().isEmpty()) {
                product.setImage(Base64.getEncoder().encodeToString(product.getUploadImage().getBytes()));
            }
            if (!product.getUploadImageHover().isEmpty()) {
                product.setImageHover(Base64.getEncoder().encodeToString(product.getUploadImageHover().getBytes()));
            }
            Date currentTime = new Date();
            product.setCreatedAt(currentTime);
            product.setModifiedAt(currentTime);
            return productRepository.save(product);
        }
        return product;
    }

    public Page<Product> getAllProducts(Integer pageSize, Integer offset, String filterBy, String sortBy) {
        Page<Product> products = null;
        Sort canSort = null;
        switch (sortBy) {
            case "priceLow": {
                canSort = Sort.by(Sort.Direction.DESC, "discountPrice");
                break;
            }
            case "priceHigh": {
                canSort = Sort.by(Sort.Direction.ASC, "discountPrice");
                break;
            }
            default:
                canSort = Sort.by(Sort.Direction.DESC, "createdAt");
        }
        switch (filterBy) {
            case "newArrival": {
                products = productRepository.findAll(PageRequest.of(offset - 1, pageSize, canSort));
                break;
            }
            case "onSale": {
                products = productRepository.findByDiscountPercentGreaterThanAndIsActived(
                        PageRequest.of(offset - 1, pageSize, canSort), 0, true);
                break;
            }
            default:
                products = productRepository.findAll(PageRequest.of(offset - 1, pageSize, canSort));
        }
        return products;
    }

    public List<Product> findTop4ByIdNotAndProductCategoryIdAndGender(Integer id, Integer productCategoryId,
            Boolean gender) {
        return productRepository.findTop4ByIdNotAndProductCategoryIdAndGenderAndIsActived(id, productCategoryId, gender,
                true);
    }
}
