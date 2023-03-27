package com.main.online_clothing_store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.online_clothing_store.models.OrderItem;
import com.main.online_clothing_store.models.composite_primary_keys.OrderItemId;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
    public List<OrderItem> findByIdOrderDetailId(Integer orderDetailId);

    public List<OrderItem> findByCommentIsNotNullAndIdProductInventoryIdIn(List<Integer> productInventoryIds);
}
