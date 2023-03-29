package com.main.online_clothing_store.models;

public class Cart {
    public Integer id;
    public Integer productInventoryId;
    public String name;
    public String image;
    public Integer quantity;
    public Double price;

    public Cart(Integer id, Integer productInventoryId, String name, String image, Integer quantity, Double price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
        this.productInventoryId = productInventoryId;
    }
}
