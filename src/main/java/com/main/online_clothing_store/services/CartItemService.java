package com.main.online_clothing_store.services;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.online_clothing_store.models.CartItem;
import com.main.online_clothing_store.models.ProductInventory;
import com.main.online_clothing_store.models.composite_primary_keys.CartItemId;
import com.main.online_clothing_store.repositories.CartItemRepository;
import com.main.online_clothing_store.repositories.ProductInventoryRepository;

@Service
public class CartItemService {
    CartItemRepository cartItemRepository;
    ProductInventoryRepository productInventoryRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository,
            ProductInventoryRepository productInventoryRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productInventoryRepository = productInventoryRepository;
    }

    public Boolean addToCart(Integer userId, Integer id, String size, String color, Integer quantity) {
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
                    CartItem cartItem = new CartItem();
                    cartItem.setId(new CartItemId(userId, id));
                    cartItem.setQuantity(quantity);
                    cartItem.setCreatedAt(currentTime);
                    cartItem.setModifiedAt(currentTime);
                    cartItemRepository.save(cartItem);
                    //cartItemRepository.insertIntoCartItems(userId, id, quantity, currentTime.toString(), currentTime.toString());
                    return true;
                }
            }
            throw new NoSuchElementException("Product not found");
        }
    }
}
