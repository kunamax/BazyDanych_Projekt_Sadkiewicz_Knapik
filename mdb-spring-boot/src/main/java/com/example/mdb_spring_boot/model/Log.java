package com.example.mdb_spring_boot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("logs")
public class Log {
    @Id
    private String id;

    private LogType type;
    private Id userId;
    private String date;
    private Id chestId;
    private List<Detail> details;

    public Log(LogType type, Id userId, String date, Id chestId){
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
