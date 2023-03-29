package com.main.online_clothing_store.services;

import com.main.online_clothing_store.models.OrderDetail;
import com.main.online_clothing_store.models.OrderItem;
import com.main.online_clothing_store.models.composite_primary_keys.OrderItemId;
import com.main.online_clothing_store.models.CartItem;
import com.main.online_clothing_store.models.ProductInventory;
import com.main.online_clothing_store.repositories.CartItemRepository;
import com.main.online_clothing_store.repositories.OrderDetailRepository;
import com.main.online_clothing_store.repositories.OrderItemRepository;
import com.main.online_clothing_store.repositories.ProductInventoryRepository;

import jakarta.transaction.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService {
    OrderDetailRepository orderDetailRepository;
    CartItemRepository cartItemRepository;
    OrderItemRepository orderItemRepository;
    ProductInventoryRepository productInventoryRepository;

    @Autowired
    public OrderDetailService(OrderDetailRepository orderDetailRepository, CartItemRepository cartItemRepository, OrderItemRepository orderItemRepository, ProductInventoryRepository productInventoryRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderItemRepository = orderItemRepository;
        this.productInventoryRepository = productInventoryRepository;
    }

    public Long getTotalOrderDetail() {
        return orderDetailRepository.count();
    }

    public Long sumTotalByStatusOrderDetail(Integer integer) {
        return orderDetailRepository.sumTotalByStatus(integer);
    }

    public List<Object[]> sumTotalByMonthAndStatus(Integer integer) {
        return orderDetailRepository.sumTotalByMonthAndStatus(integer);
    }

    public List<OrderDetail> findByUserId(Integer id) {
        return orderDetailRepository.findByUserId(id);
    }

    public Optional<OrderDetail> findById(Integer id){
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(id);
        if(orderDetail.isPresent()){
            String shipAddress = orderDetail.get().getApartmentNumber() + ", " + orderDetail.get().getStreet() + ", " + orderDetail.get().getWard() + ", " + orderDetail.get().getDistrict() + ", " + orderDetail.get().getCity();
            orderDetail.get().setShipAddress(shipAddress);
        }
        return orderDetail;
    }

    @Transactional
    public Boolean checkout(OrderDetail orderDetail){
        try{
            orderDetail = orderDetailRepository.saveAndFlush(orderDetail);
            List<CartItem> cartItems = cartItemRepository.findByIdUserId(orderDetail.getUser().getId());
            for (CartItem cartItem : cartItems) {
                Date currentDate = new Date();
                OrderItem orderItem = new OrderItem();
                orderItem.setId(new OrderItemId(cartItem.getProductInventory().getId(), orderDetail.getId()));
                orderItem.setPrice(cartItem.getProductInventory().getProduct().getSellPrice());
                orderItem.setDiscountPercent(cartItem.getProductInventory().getProduct().getDiscountPercent());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setCreatedAt(currentDate);
                orderItem.setModifiedAt(currentDate);
                orderItem.setProductInventory(cartItem.getProductInventory());
                orderItem.setOrderDetail(orderDetail);
                orderItemRepository.save(orderItem);
                Optional<ProductInventory> productInventory = productInventoryRepository.findById(cartItem.getProductInventory().getId());
                productInventory.get().setQuantity(productInventory.get().getQuantity() - cartItem.getQuantity());
                productInventoryRepository.save(productInventory.get());
            }
            cartItemRepository.deleteAll(cartItems);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
