package com.example.mdb_spring_boot.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

public class UserChest {

    @Field("chest_id")
    private ObjectId chestId;
    private int quantity;

    public UserChest(){}

    public UserChest(ObjectId chestId){
        this(chestId, 0);
    }

    public UserChest(ObjectId chestId, int quantity){
        this.chestId = chestId;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
