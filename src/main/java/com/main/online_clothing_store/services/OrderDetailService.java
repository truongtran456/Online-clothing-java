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

import java.io.IOException;
import java.sql.SQLException;
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
    public Optional<Integer> sumTotalByMonthAndStatus(int status, int year, int month){
        Optional<Integer> optionalResult = orderDetailRepository.sumTotalByMonthAndStatus(status, year, month);
        return optionalResult;
    }
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }
    @Transactional
    public OrderDetail update(OrderDetail orderDetail) throws IllegalArgumentException, IOException {
        Optional<OrderDetail> orderDetailUpdateOptional = orderDetailRepository.findById(orderDetail.getId());
        if (orderDetailUpdateOptional.isPresent()) {
            Date currentTime = new Date();
            orderDetail.setCreatedAt(currentTime);
            orderDetail.setModifiedAt(currentTime);
            return orderDetailRepository.save(orderDetail);
        }
        return orderDetail;
    }

    public Long sumTotalByMonthAndStatusAndUserId(Integer status, Integer userId) throws SQLException{
        return orderDetailRepository.sumTotalByStatusAndUserId(status,userId);
    }
    public Long getTotalPurchasedByUserId(Integer userId) throws SQLException{
        return orderDetailRepository.countPurchasedByUserId(userId);
    }
    public Long getTotalInOrderByUserId(Integer userId) throws SQLException{
        return orderDetailRepository.countInOrderByUserId(userId);
    }
    public List<OrderDetail> getOrderByUserId(Integer userId) throws Exception{
        return orderDetailRepository.findByUserId(userId);
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
    public Optional<Integer> countOrderDetailByStatus(Integer status) {
        Optional<Integer> countOrderDetailByStatus = orderDetailRepository.countOrderDetailByStatus(status);
        return countOrderDetailByStatus;
    }
    @Transactional
    public void updateStatus(Integer id, Integer status) throws SQLException{
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(id);
        if (orderDetail.isPresent()) {
            if(status == 2){
                orderDetail.get().setStatus(2);
                orderDetailRepository.save(orderDetail.get());
            }
            else if(status == 0){
                orderDetail.get().setStatus(0);
                orderDetailRepository.save(orderDetail.get());
                List<OrderItem> orderItems = orderItemRepository.findAllByIdOrderDetailId(id);
                for (OrderItem item : orderItems) {
                    ProductInventory productInventory = productInventoryRepository.findById(item.getProductInventory().getId()).orElseThrow();;
                    productInventory.setQuantity(productInventory.getQuantity()+item.getQuantity());
                    productInventoryRepository.save(productInventory);
                }
            }
        }
    }

    public Boolean cancelOrderDetail(Integer id){
        try {
            Optional<OrderDetail> orderDetail = orderDetailRepository.findById(id);
            if(orderDetail.isPresent()){
                orderDetail.get().setStatus(0);
                orderDetailRepository.save(orderDetail.get());
                List<OrderItem> orderItems = orderItemRepository.findAllByIdOrderDetailId(orderDetail.get().getId());
                for (OrderItem orderItem : orderItems) {
                    ProductInventory productInventory = productInventoryRepository.findById(orderItem.getProductInventory().getId()).get();
                    productInventory.setQuantity(productInventory.getQuantity()+orderItem.getQuantity());
                    productInventoryRepository.save(productInventory);
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
