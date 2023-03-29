package com.main.online_clothing_store.services;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.online_clothing_store.models.Coupon;
import com.main.online_clothing_store.models.Product;
import com.main.online_clothing_store.repositories.CouponRepository;
import com.main.online_clothing_store.repositories.ProductRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class CouponService {
    CouponRepository couponRepository;

    @Autowired
    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }
    @Transactional
    public Coupon create(Coupon coupon) throws Exception {
        Date currentTime = new Date();
        coupon.setCreatedAt(currentTime);
        coupon.setModifiedAt(currentTime);
        return couponRepository.save(coupon);
    }
    public Optional<Coupon> findById(Integer id) {
        return couponRepository.findById(id);
    }
    @Transactional
    public Coupon update(Coupon coupon) throws IllegalArgumentException, IOException {
        Optional<Coupon> couponUpdateOptional = couponRepository
                .findById(coupon.getId());
        if (couponUpdateOptional.isPresent()) {
            Date currentTime = new Date();
            coupon.setCreatedAt(currentTime);
            coupon.setModifiedAt(currentTime);
            return couponRepository.save(coupon);
        }
        return coupon;
    }
}
