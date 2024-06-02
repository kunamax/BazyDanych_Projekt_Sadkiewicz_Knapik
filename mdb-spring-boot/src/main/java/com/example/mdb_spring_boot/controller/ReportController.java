package com.example.mdb_spring_boot.controller;

import com.example.mdb_spring_boot.service.ReportService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.bson.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.json.JsonObject;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonObject;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/users-total-skins-values")
    public List<JsonObject> getUsersTotalSkinsValues() {
        return reportService.generateUsersTotalSkinsValues();
    }

    @GetMapping("/user-total-spending/{userId}")
    public List<JsonObject> getUserTotalSpending(@PathVariable String userId) {
        return reportService.generateUserTotalSpending(new ObjectId(userId));
    }

    @GetMapping("/skin-report")
    public List<JsonObject> getSkinReport() {
        return reportService.generateSkinReport();
    }
}
