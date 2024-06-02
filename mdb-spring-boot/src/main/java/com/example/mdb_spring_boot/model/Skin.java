package com.example.mdb_spring_boot.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

public class Skin {
    private ObjectId id;
    private String name;
    private String type;
    private String rarity;
    private double odds;
    @Field("base_price")
    private double basePrice;

    public Skin(String name, String type, String rarity, double odds, double basePrice) {
        this.id = new ObjectId();
        this.name = name;
        this.rarity = rarity;
        this.type = type;
        this.odds = odds;
        this.basePrice = basePrice;
    }

    public ObjectId getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public String getRarity(){
        return rarity;
    }

    public double getOdds(){
        return odds;
    }

    public double getBasePrice(){
        return basePrice;
    }
}
