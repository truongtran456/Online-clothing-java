package com.main.online_clothing_store.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.online_clothing_store.models.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    public Optional<Coupon> findByName(String name);
}
