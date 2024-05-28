package com.example.mdb_spring_boot.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document("logs")
public class Log {
    @Id
    private String id;

    private LogType type;
    @Field("user_id")
    private ObjectId userId;
    private String date;
    @Field("chest_id")
    private ObjectId chestId;
    private Detail details;

    public Log(LogType type, ObjectId userId, String date, ObjectId chestId, Detail detail){
        this.type = type;
        this.userId = userId;
        this.date = date;
        this.chestId = chestId;
        this.details = detail;
    }

    public String getId(){
        return id;
    }

    public LogType getType(){
        return type;
    }

    public ObjectId getUserId(){
        return userId;
    }

    public String getDate(){
        return date;
    }

    public ObjectId getChestId(){
        return chestId;
    }

    public Detail getDetail(){
        return details;
    }
}
