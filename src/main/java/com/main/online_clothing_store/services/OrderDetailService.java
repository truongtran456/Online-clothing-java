package com.main.online_clothing_store.services;

import com.main.online_clothing_store.models.OrderDetail;
import com.main.online_clothing_store.models.OrderItem;
import com.main.online_clothing_store.models.ProductInventory;
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
    ProductInventoryRepository productInventoryRepository;
    OrderItemRepository orderItemRepository;
    @Autowired
    public OrderDetailService(OrderDetailRepository orderDetailRepository, ProductInventoryRepository productInventoryRepository, OrderItemRepository orderItemRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.productInventoryRepository = productInventoryRepository;
        this.orderItemRepository = orderItemRepository;
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
}
