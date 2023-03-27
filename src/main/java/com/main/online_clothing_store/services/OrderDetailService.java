package com.main.online_clothing_store.services;

import com.main.online_clothing_store.models.OrderDetail;
import com.main.online_clothing_store.repositories.OrderDetailRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService {
    OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
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
}
