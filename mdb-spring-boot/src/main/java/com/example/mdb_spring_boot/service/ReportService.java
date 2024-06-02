package com.example.mdb_spring_boot.service;

import ch.qos.logback.core.joran.util.AggregationAssessor;
import com.mongodb.BasicDBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.model.*;
import org.bson.BsonDateTime;
import org.bson.types.ObjectId;
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

    public Iterable<Document> generateUsersTotalSkinsValues(){
        AggregateIterable<Document> result = mongoTemplate.getCollection("users").aggregate(Arrays.asList(
                Aggregates.unwind("$skins"),
                Aggregates.group(
                        new Document("_id", "$_id").append("name", "$name"),
                        Accumulators.sum("totalValue", "$skins.price")
                ),
                Aggregates.sort(Sorts.descending("totalValue"))
        ));
        for (Document doc : result) {
            System.out.println(doc.toJson());
        }

        return result;
    }

    public Iterable<Document> generateUserTotalSpending(ObjectId userId){
        AggregateIterable<Document> result = mongoTemplate.getCollection("logs").aggregate(Arrays.asList(
                Aggregates.match(Filters.eq("user_id", userId)),
                Aggregates.match(Filters.eq("type", "CHEST_PURCHASE")),
                Aggregates.project(
                        Projections.fields(
                                Projections.include("user_id", "type"),
                                Projections.computed("totalAmount",
                                        new Document("$multiply", Arrays.asList("$details.chest_price", "$details.quantity")))
                        )
                ),
                Aggregates.group(userId,
                        Accumulators.sum("totalAmount", "$totalAmount")
                )
        ));

        for (Document doc : result) {
            System.out.println(doc.toJson());
        }

        return result;
    }

    public Iterable<Document> generateSkinReport() {
        AggregateIterable<Document> result = mongoTemplate.getCollection("users").aggregate(Arrays.asList(
                // Rozwinięcie listy skinów
                new Document("$unwind", "$skins"),
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
