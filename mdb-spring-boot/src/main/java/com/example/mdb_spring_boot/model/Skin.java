package com.example.mdb_spring_boot.model;

public class Skin {
    private String name;
    private String rarity;
    private double odds;

    public Skin(String name, String rarity, double odds) {
        this.name = name;
        this.rarity = rarity;
        this.odds = odds;
    }
}
