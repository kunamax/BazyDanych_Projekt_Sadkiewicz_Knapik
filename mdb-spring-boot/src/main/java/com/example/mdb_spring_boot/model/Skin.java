package com.example.mdb_spring_boot.model;

import org.bson.types.ObjectId;

import java.util.UUID;

public class Skin {
    private ObjectId id;
    private String name;
    private String rarity;
    private double odds;

    public Skin(String name, String rarity, double odds) {
        this.id = new ObjectId();
        this.name = name;
        this.rarity = rarity;
        this.odds = odds;
    }

    public ObjectId getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getRarity(){
        return rarity;
    }

    public double getOdds(){
        return odds;
    }
}
