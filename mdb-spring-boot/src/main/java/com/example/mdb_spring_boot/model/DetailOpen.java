package com.example.mdb_spring_boot.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

public class DetailOpen extends Detail {
    @Field("skin_opened_id")
    private final ObjectId skinId;

    public DetailOpen(ObjectId skinId, String description){
        super(description);
        this.skinId = skinId;
    }

    public ObjectId getSkinId(){
        return skinId;
    }

    @Override
    public String getDetailType(){
        return "CHEST_OPEN";
    }

    @Override
    public String toString(){
        return "DetailOpen{" +
                "skinId=" + skinId +
                ", description='" + getDescription() + '\'' +
                '}';
    }
}
