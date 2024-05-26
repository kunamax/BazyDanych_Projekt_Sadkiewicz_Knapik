package com.example.mdb_spring_boot.model;

import java.util.UUID;

public class DetailOpen extends Detail {
    private final UUID skinId;

    public DetailOpen(UUID skinId, String description){
        super(description);
        this.skinId = skinId;
    }
}
