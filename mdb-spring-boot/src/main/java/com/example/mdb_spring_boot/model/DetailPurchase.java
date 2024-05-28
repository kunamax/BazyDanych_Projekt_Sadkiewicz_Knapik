package com.example.mdb_spring_boot.model;

import org.springframework.data.mongodb.core.mapping.Field;

public class DetailPurchase extends Detail {
    @Field("chest_price")
    private final double chestPrice;
    private final int quantity;

    public DetailPurchase(double chestPrice, int quantity, String description){
        super(description);
        this.chestPrice = chestPrice;
        this.quantity = quantity;
    }

    public double getChestPrice(){
        return chestPrice;
    }

    public int getQuantity(){
        return quantity;
    }

    @Override
    public String getDetailType(){
        return "CHEST_PURCHASE";
    }

    @Override
    public String toString(){
        return "DetailPurchase{" +
                "chestPrice=" + chestPrice +
                ", quantity=" + quantity +
                ", description='" + getDescription() + '\'' +
                '}';
    }
}
