package com.example.mdb_spring_boot.model;

public abstract class Detail {
    private final String description;

    public Detail(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public abstract String getDetailType();
}
