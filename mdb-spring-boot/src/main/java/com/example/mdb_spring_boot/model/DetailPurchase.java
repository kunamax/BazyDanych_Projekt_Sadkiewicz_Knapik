package com.example.mdb_spring_boot.model;

public class DetailPurchase extends Detail {
    private final float chestPrice;
    private final int quantity;

    public DetailPurchase(float chestPrice, int quantity, String description){
        super(description);
        this.chestPrice = chestPrice;
        this.quantity = quantity;
    }
}
