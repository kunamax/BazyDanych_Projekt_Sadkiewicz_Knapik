package com.example.mdb_spring_boot.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

public class DetailOpen extends Detail {
    @Field("sking_id")
    private final ObjectId skinId;

    public DetailOpen(ObjectId skinId, String description){
        super(description);
        this.skinId = skinId;
    }
}
