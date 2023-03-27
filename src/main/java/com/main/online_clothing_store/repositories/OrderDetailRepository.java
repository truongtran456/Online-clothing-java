package com.main.online_clothing_store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.online_clothing_store.models.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query(value = "SELECT  COALESCE(SUM(c.total), 0) FROM OrderDetails c WHERE c.status = :status", nativeQuery = true)
    public Long sumTotalByStatus(Integer status);

    @Query(value =  "SELECT MONTH(c.createdAt) AS month, SUM(c.total) AS sumTotal " +
           "FROM OrderDetails c WHERE c.status = :status " +
           "GROUP BY MONTH(c.createdAt)",  nativeQuery = true)
    public List<Object[]> sumTotalByMonthAndStatus(Integer status);

    public List<OrderDetail> findByUserId(Integer id);
}
