package com.example.mdb_spring_boot.model;

import java.util.UUID;

public class UserSkin {
    private UUID id;
    private String name;
    private String type;
    private double wear;
    private int pattern;
    private double price;
    private UUID skinId;

    public UserSkin(String name, String type, double wear, int pattern, int price, UUID skinId){
        this.id = UUID.randomUUID();
        this.name = name;
        this.type = type;
        this.wear = wear;
        this.pattern = pattern;
        this.price = price;
        this.skinId = skinId;
    }
}
