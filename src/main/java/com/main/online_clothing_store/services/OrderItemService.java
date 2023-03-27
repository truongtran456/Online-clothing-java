package com.main.online_clothing_store.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.online_clothing_store.models.OrderItem;
import com.main.online_clothing_store.repositories.OrderItemRepository;

@Service
public class OrderItemService {
    OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository){
        this.orderItemRepository = orderItemRepository;
    }
    public List<OrderItem> getOrderItemByOrderDetailId(Integer id) {
        return orderItemRepository.findByIdOrderDetailId(id);
    }

}
