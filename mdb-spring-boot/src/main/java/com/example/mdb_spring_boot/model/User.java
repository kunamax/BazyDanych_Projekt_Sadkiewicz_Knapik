package com.example.mdb_spring_boot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("users")
public class User {
    @Id
    private String id;

    private String name;
    private String surname;
    private String email;
    private double deposit;

    private List<UserChest> chests = new ArrayList<>();

    private List<UserSkin> skins = new ArrayList<>();

    public User(String name, String surname, String email, double deposit){
        super();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.deposit = deposit;
    }

    public void addSkin(UserSkin skin){
        skins.add(skin);
    }

    public void addChest(UserChest chest){
        for (UserChest ownedChest : this.chests) {
            if (chest.getChestId().equals(ownedChest.getChestId())) {
                ownedChest.setQuantity(ownedChest.getQuantity() + chest.getQuantity());
                return;
            }
        }
        this.chests.add(chest);
    }

    public void setDeposit(double deposit){
        this.deposit = deposit;
    }

    public void addToDeposit(double money){
        this.deposit += money;
    }

    public void removeFromDeposit(double money){
        this.deposit -= money;
    }

    public void afterOpeningChest(UserChest chest){
        if (chest.getQuantity() == 1)
            chests.remove(chest);
        else
            chest.setQuantity(chest.getQuantity() - 1);
    }

    public void setChests(List<UserChest> chests) {
        this.chests = chests;
    }

    public void setSkins(List<UserSkin> skins) {
        this.skins = skins;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public String getEmail(){
        return email;
    }

    public double getDeposit(){
        return deposit;
    }

    public List<UserChest> getChests(){
        return chests;
    }

    public List<UserSkin> getSkins() {
        return skins;
    }
}
