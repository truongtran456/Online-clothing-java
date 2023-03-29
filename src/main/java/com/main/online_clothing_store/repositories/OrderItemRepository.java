package com.main.online_clothing_store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.online_clothing_store.models.OrderItem;
import com.main.online_clothing_store.models.composite_primary_keys.OrderItemId;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
    public List<OrderItem> findByIdOrderDetailId(Integer orderDetailId);

    public List<OrderItem> findByCommentIsNotNullAndIdProductInventoryIdIn(List<Integer> productInventoryIds);

    public List<OrderItem> findTop5ByOrderByCreatedAtDesc();

    @Query(value = "SELECT * FROM OrderItems WHERE orderDetailId IN (SELECT id FROM OrderDetails WHERE userId = :userId) GROUP BY productInventoryId", nativeQuery = true )
    public List<OrderItem> findOrderItemByUserId(Integer userId);

    @Query(value = "SELECT * FROM OrderItems WHERE orderDetailId = :id", nativeQuery = true )
    public List<OrderItem> findAllByIdOrderDetailId(Integer id);
}
