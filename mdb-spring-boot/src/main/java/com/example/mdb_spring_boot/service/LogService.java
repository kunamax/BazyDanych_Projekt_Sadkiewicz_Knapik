package com.example.mdb_spring_boot.service;

import com.example.mdb_spring_boot.model.Detail;
import com.example.mdb_spring_boot.model.DetailOpen;
import com.example.mdb_spring_boot.model.DetailPurchase;
import com.example.mdb_spring_boot.repository.LogRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mdb_spring_boot.model.Log;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LogService {
    private final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public Log addLog(Log log) {
        log.setDate(new Date().toString());
        return logRepository.save(log);
    }

    public void removeLog(Log log){
        logRepository.delete(log);
    }

    public List<JsonObject> getAllLogs() {
        List<Log> logs = logRepository.findAll();
        return logs.stream()
                .map(this::convertLogToJson)
                .collect(Collectors.toList());
    }

    private JsonObject convertLogToJson(Log log) {
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
        jsonBuilder.add("id", log.getId());
        jsonBuilder.add("type", log.getType().toString());
        jsonBuilder.add("userId", log.getUserId().toHexString());
        jsonBuilder.add("date", log.getDate());
        jsonBuilder.add("chestId", log.getChestId().toHexString());
        jsonBuilder.add("details", convertDetailToJson(log.getDetail()));
        return jsonBuilder.build();
    }

    private JsonObject convertDetailToJson(Detail details) {
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
        if (details instanceof DetailPurchase) {
            DetailPurchase detailPurchase = (DetailPurchase) details;
            jsonBuilder.add("chestPrice", String.valueOf(detailPurchase.getChestPrice()));
            jsonBuilder.add("quantity", String.valueOf(detailPurchase.getQuantity()));
            jsonBuilder.add("description", detailPurchase.getDescription());
        } else if (details instanceof DetailOpen) {
            DetailOpen detailOpen = (DetailOpen) details;
            jsonBuilder.add("skinOpenedId", detailOpen.getSkinId().toHexString());
            jsonBuilder.add("description", detailOpen.getDescription());
        }
        return jsonBuilder.build();
    }

    public Log getLogById(String logId) {
        Optional<Log> optionalLog = logRepository.findById(logId);
        if (optionalLog.isPresent()) {
            return optionalLog.get();
        } else {
            throw new RuntimeException("Log not found");
        }
    }
}
