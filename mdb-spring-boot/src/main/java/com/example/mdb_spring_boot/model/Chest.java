package com.example.mdb_spring_boot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("chests")
public class Chest {
    @Id
    private String id;

    private String name;
    private double price;
    private final List<Skin> skins;

    public Chest(String name, double price, List<Skin> skins){
        this.name = name;
        this.price = price;
        this.skins = skins;
    }

    public void addSkin(Skin skin){
        this.skins.add(skin);
    }
    public void removeSkin(Skin skin){
        this.skins.remove(skin);
    }
}
