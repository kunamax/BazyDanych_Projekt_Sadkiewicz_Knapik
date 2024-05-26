package com.example.mdb_spring_boot.model;

public class UserChest {

    private String chestId;
    private int quantity;

    public UserChest(String chestId){
        this(chestId, 0);
    }

    public UserChest(String chestId, int quantity){
        this.chestId = chestId;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
