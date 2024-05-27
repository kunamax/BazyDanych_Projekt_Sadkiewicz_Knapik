package com.example.mdb_spring_boot.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
    private List<Detail> details;

    public Log(LogType type, ObjectId userId, String date, ObjectId chestId){
        this.type = type;
        this.userId = userId;
        this.date = date;
        this.chestId = chestId;
    }

    public void addDetail(Detail detail){
        details.add(detail);
    }

    public void removeDetail(Detail detail){
        details.remove(detail);
    }
}
