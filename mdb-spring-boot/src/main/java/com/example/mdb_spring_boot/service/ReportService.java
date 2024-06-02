package com.example.mdb_spring_boot.service;

import com.mongodb.BasicDBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ReportService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ReportService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<JsonObject> generateUsersTotalSkinsValues() {
        AggregateIterable<Document> result = mongoTemplate.getCollection("users").aggregate(Arrays.asList(
                new Document("$unwind", "$skins"),
                new Document("$group", new Document("_id", "$_id")
                        .append("name", new Document("$first", "$name"))
                        .append("totalValue", new Document("$sum", "$skins.price"))),
                new Document("$sort", new Document("totalValue", -1))
        ));

        return convertDocumentsToJson(result);
    }


    public List<JsonObject> generateUserTotalSpending(ObjectId userId) {
        MongoCollection<Document> collection = mongoTemplate.getCollection("logs");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                new Document("$match", new Document("user_id", userId)),
                new Document("$match", new Document("type", "CHEST_PURCHASE")),
                new Document("$project", new Document("user_id", 1).append("type", 1)
                        .append("totalAmount", new Document("$multiply", Arrays.asList("$details.chest_price", "$details.quantity")))),
                new Document("$group", new Document("_id", "$user_id").append("totalAmount", new Document("$sum", "$totalAmount")))
        ));

        return convertDocumentsToJson(result);
    }

    public List<JsonObject> generateSkinReport() {
        MongoCollection<Document> collection = mongoTemplate.getCollection("users");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
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

        return convertDocumentsToJson(result);
    }

    private List<JsonObject> convertDocumentsToJson(Iterable<Document> documents) {
        List<JsonObject> jsonObjects = new ArrayList<>();
        for (Document doc : documents) {
            JsonObjectBuilder builder = Json.createObjectBuilder();
            doc.forEach((key, value) -> {
                builder.add(key, String.valueOf(value));
            });
            jsonObjects.add(builder.build());
        }
        return jsonObjects;
    }
}
