package com.example.mdb_spring_boot.model;

import java.util.UUID;

public class Skin {
    private UUID id;
    private String name;
    private String rarity;
    private double odds;

    public Skin(String name, String rarity, double odds) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.rarity = rarity;
        this.odds = odds;
    }
}
