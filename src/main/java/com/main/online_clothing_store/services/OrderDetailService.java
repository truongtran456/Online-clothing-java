package com.main.online_clothing_store.services;

import com.main.online_clothing_store.models.OrderDetail;
import com.main.online_clothing_store.models.OrderItem;
import com.main.online_clothing_store.repositories.OrderDetailRepository;

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

    @Autowired
    public OrderDetailService(OrderDetailRepository orderDetailRepository){
        this.orderDetailRepository = orderDetailRepository;
    }
    public Long getTotalOrderDetail(){
        return orderDetailRepository.count();
    }
    public Long sumTotalByStatusOrderDetail(Integer integer){
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
    public Optional<OrderDetail> findById(Integer id) {
        return orderDetailRepository.findById(id);
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
}
