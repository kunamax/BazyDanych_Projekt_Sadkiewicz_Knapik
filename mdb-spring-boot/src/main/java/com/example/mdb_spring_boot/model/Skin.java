package com.example.mdb_spring_boot.model;

import java.util.UUID;

public class Skin {
    private String id;
    private String name;
    private String rarity;
    private double odds;

    public Skin(String name, String rarity, double odds) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.rarity = rarity;
        this.odds = odds;
    }

    public String getId(){
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
