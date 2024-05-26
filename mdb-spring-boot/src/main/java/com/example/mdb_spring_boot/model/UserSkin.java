package com.example.mdb_spring_boot.model;

public class UserSkin {
    private String name;
    private String type;
    private double wear;
    private int pattern;
    private double price;

    public UserSkin(String name, String type, double wear, int pattern, int price){
        this.name = name;
        this.type = type;
        this.wear = wear;
        this.pattern = pattern;
        this.price = price;
    }
}
