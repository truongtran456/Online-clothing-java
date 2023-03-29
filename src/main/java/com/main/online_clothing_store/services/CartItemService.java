package com.main.online_clothing_store.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.online_clothing_store.models.Cart;
import com.main.online_clothing_store.models.CartItem;
import com.main.online_clothing_store.models.Coupon;
import com.main.online_clothing_store.models.Product;
import com.main.online_clothing_store.models.ProductInventory;
import com.main.online_clothing_store.models.Payment;
import com.main.online_clothing_store.models.User;
import com.main.online_clothing_store.models.Wish;
import com.main.online_clothing_store.models.Wishlist;
import com.main.online_clothing_store.models.composite_primary_keys.CartItemId;
import com.main.online_clothing_store.models.composite_primary_keys.WishlistId;

import com.main.online_clothing_store.repositories.CartItemRepository;
import com.main.online_clothing_store.repositories.CouponRepository;
import com.main.online_clothing_store.repositories.ProductRepository;
import com.main.online_clothing_store.repositories.PaymentRepository;
import com.main.online_clothing_store.repositories.ProductInventoryRepository;
import com.main.online_clothing_store.repositories.WishlistRepository;

@Service
public class CartItemService {
    CartItemRepository cartItemRepository;
    ProductInventoryRepository productInventoryRepository;
    WishlistRepository wishListRepository;
    ProductRepository productRepository;
    PaymentRepository paymentRepository;
    CouponRepository couponRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository, ProductInventoryRepository productInventoryRepository, WishlistRepository wishListRepository, ProductRepository productRepository, PaymentRepository paymentRepository, CouponRepository couponRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productInventoryRepository = productInventoryRepository;
        this.wishListRepository = wishListRepository;
        this.productRepository = productRepository;
        this.paymentRepository = paymentRepository;
        this.couponRepository = couponRepository;
    }

    public Boolean addToCart(User user, Integer id, String size, String color, Integer quantity) {
        if(quantity <= 0) {
            return false;
        }
        else{
            Optional<ProductInventory> productInventory = productInventoryRepository.findTop1ByProductIdAndSizeAndColor(id, size, color);
            if(productInventory.isPresent()){
                if(productInventory.get().getQuantity() < quantity){
                    throw new IllegalArgumentException("Not enough quantity");
                }
                else{
                    Date currentTime = new Date();
                    CartItem cartItem = new CartItem(new CartItemId(user.getId(), productInventory.get().getId()), quantity, currentTime, currentTime, user, productInventory.get());
                    cartItemRepository.save(cartItem);
                    return true;
                }
            }
            throw new NoSuchElementException("Product not found");
        }
    }

    public List<Cart> viewCart(Integer userId){
        List<CartItem> cartItems = cartItemRepository.findByIdUserId(userId);
        List<Cart> cart = new ArrayList<Cart>();
        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProductInventory().getProduct();
            cart.add(new Cart(product.getId(), cartItem.getProductInventory().getId(), product.getName() + " - " + cartItem.getProductInventory().getSize() + " - " + cartItem.getProductInventory().getColor(), product.getImage(), cartItem.getQuantity(), product.getDiscountPrice()));
        }
        return cart;
    }

    public Boolean removeCartItem(Integer userId, Integer productInventoryId){
        try{
            Optional<CartItem> cartItem = cartItemRepository.findByIdUserIdAndIdProductInventoryId(userId, productInventoryId);
            if(cartItem.isPresent()){
                cartItemRepository.delete(cartItem.get());
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
    }

    public List<Wish> viewWishlist(Integer userId){
        List<Wishlist> wishlist = wishListRepository.findByIdUserId(userId);
        List<Wish> wish = new ArrayList<Wish>();
        for (Wishlist item : wishlist) {
            Product product = item.getProduct();
            wish.add(new Wish(product.getId(), product.getName(), product.getImage(), product.getDiscountPrice()));
        }
        return wish;
    }

    public Boolean removeWishlistItem(Integer userId, Integer productId){
        try{
            Optional<Wishlist> wishlist = wishListRepository.findByIdUserIdAndIdProductId(userId, productId);
            if(wishlist.isPresent()){
                wishListRepository.delete(wishlist.get());
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
    }

    public Boolean addToWishlist(User user, Integer productId){
        try{
            if(!wishListRepository.findByIdUserIdAndIdProductId(user.getId(), productId).isPresent()){
                Optional<Product> product = productRepository.findByIdAndIsActived(productId, true);
                if(product.isPresent()){
                    Wishlist wishlist = new Wishlist(new WishlistId(user.getId(), product.get().getId()), new Date(), product.get(), user);
                    wishListRepository.save(wishlist);
                    return true;
                }
                throw new NoSuchElementException("Product not found");
            }
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public Double calSubtotal(List<Cart> cart){
        return cart.stream().map(p -> p.quantity * p.price).reduce(0.0, Double::sum);
    }

    public List<Payment> getPayments(){
        return paymentRepository.findAll();
    }

    public Optional<Coupon> findCouponByName(String name){
        Optional<Coupon> coupon = couponRepository.findByName(name);
        if(coupon.isPresent()){
            Date currentDate = new Date();
            if(coupon.get().getStartDate().after(currentDate) || coupon.get().getEndDate().before(currentDate) || !coupon.get().getIsActived()){
                throw new NoSuchElementException("Coupon not found");
            }
            else{
                return coupon;
            }
        }
        throw new NoSuchElementException("Coupon not found");
    }
    
    public Optional<Payment> findPaymentById(Integer id){
        return paymentRepository.findById(id);
    }

    public Boolean isValidCart(Integer userId){
        try{
            List<CartItem> cartItems = cartItemRepository.findByIdUserId(userId);
            for (CartItem cartItem : cartItems) {
                if(cartItem.getProductInventory().getQuantity() < cartItem.getQuantity()){
                    return false;
                }
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
