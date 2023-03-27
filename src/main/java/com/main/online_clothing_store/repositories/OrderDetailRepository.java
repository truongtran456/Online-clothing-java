package com.main.online_clothing_store.repositories;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.main.online_clothing_store.models.OrderDetail;


@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query(value = "SELECT  COALESCE(SUM(c.total), 0) FROM OrderDetails c WHERE c.status = :status", nativeQuery = true)
    public Long sumTotalByStatus(Integer status);

    @Query(value ="SELECT COALESCE(SUM(c.total), 0) " +
    "FROM OrderDetails c WHERE c.status = :status AND YEAR(c.createdAt) = :year AND MONTH(c.createdAt) = :month " +
    "GROUP BY MONTH(c.createdAt)",  nativeQuery = true)
    public Optional<Integer> sumTotalByMonthAndStatus(@Param("status") int status, @Param("year") int year, @Param("month") int month);


    @Query(value = "SELECT  COALESCE(SUM(c.total), 0) FROM OrderDetails c WHERE c.status = :status and c.userId= :userId", nativeQuery = true)
    public Long sumTotalByStatusAndUserId(Integer status, Integer userId) throws SQLException;

    @Query(value = "SELECT COUNT(id) FROM OrderDetails as c WHERE c.status =2 and c.userId= :userId", nativeQuery = true)
    public Long countPurchasedByUserId(Integer userId);

    @Query(value = "SELECT COUNT(id) FROM OrderDetails as c WHERE c.status !=2 and c.userId= :userId", nativeQuery = true)
    public Long countInOrderByUserId(Integer userId);

    public List<OrderDetail> findByUserId(Integer userId) throws Exception;
}
