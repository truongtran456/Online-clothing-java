package com.main.online_clothing_store.models;

public class Wish {
    public Integer productId;
    public String name;
    public String image;
    public Double price;

    public Wish(Integer productId, String name, String image, Double price) {
        this.productId = productId;
        this.name = name;
        this.image = image;
        this.price = price;
    }
}
