package com.example.mdb_spring_boot.service;

import com.mongodb.BasicDBObject;
import com.mongodb.client.AggregateIterable;
import org.bson.BsonDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

@Service
public class ReportService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ReportService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Iterable<Document> generateSkinReportAfterDate(String date) {
        AggregateIterable<Document> result = mongoTemplate.getCollection("users").aggregate(Arrays.asList(
                // Rozwinięcie listy skinów
                new Document("$unwind", "$skins"),
                // Połączenie logów z odpowiadającymi im skinami na podstawie ID skina
                new Document("$lookup", new Document("from", "logs")
                        .append("let", new Document("skinId", "$skins._id"))
                        .append("pipeline", Arrays.asList(
                                new Document("$match", new Document("$expr", new Document("$eq", Arrays.asList("$details.skin_opened_id", "$$skinId"))))
                        ))
                        .append("as", "logs")
                ),
                new Document("$match", new Document("logs.type", "CHEST_OPEN").append("logs.date", new Document("$gte", date))),
                new Document("$lookup", new Document("from", "chests")
                        .append("let", new Document("skinId", "$skins.skin_id"))
                        .append("pipeline", Arrays.asList(
                                new Document("$unwind", "$skins"),
                                new Document("$match", new Document("$expr", new Document("$eq", Arrays.asList("$skins._id", "$$skinId"))))
                        ))
                        .append("as", "chestSkins")),
                new Document("$unwind", "$chestSkins"),
                new Document("$group", new Document("_id", "$skins.skin_id")
                        .append("count", new Document("$sum", 1))
                        .append("totalSpent", new Document("$sum", "$chestSkins.price")))
        ));

        for (Document doc : result) {
            System.out.println(doc.toJson());
        }
        return result;
    }
}
