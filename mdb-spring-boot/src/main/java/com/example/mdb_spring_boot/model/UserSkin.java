package com.example.mdb_spring_boot.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

public class UserSkin {
    private ObjectId id;
    private String name;
    private String type;
    private double wear;
    private int pattern;
    private double price;
    @Field("skin_id")
    private ObjectId skinId;

    public UserSkin(String name, String type, double wear, int pattern, double price, ObjectId skinId){
        this.id = new ObjectId();
        this.name = name;
        this.type = type;
        this.wear = wear;
        this.pattern = pattern;
        this.price = price;
        this.skinId = skinId;
    }
}
