package com.main.online_clothing_store.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.online_clothing_store.models.OrderItem;
import com.main.online_clothing_store.models.Product;
import com.main.online_clothing_store.models.ProductInventory;
import com.main.online_clothing_store.repositories.OrderItemRepository;

@Service
public class OrderItemService {
    OrderItemRepository orderItemRepository;
    
    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> findByOrderDetailId(Integer id) {
        return orderItemRepository.findByIdOrderDetailId(id);
    }

    public List<OrderItem> findByCommentIsNotNullAndIdProductInventoryIdIn(Product product) {
        List<Integer> productInventoryIds = product.getProductInventories().parallelStream().map(ProductInventory::getId).collect(Collectors.toList());
        return orderItemRepository.findByCommentIsNotNullAndIdProductInventoryIdIn(productInventoryIds);
    }

}
