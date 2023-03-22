package com.main.online_clothing_store.services;

import com.main.online_clothing_store.repositories.OrderDetailRepository;

import java.util.List;

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
    public List<Object[]> sumTotalByMonthAndStatus(Integer integer){
        return orderDetailRepository.sumTotalByMonthAndStatus(integer);
    }
}
