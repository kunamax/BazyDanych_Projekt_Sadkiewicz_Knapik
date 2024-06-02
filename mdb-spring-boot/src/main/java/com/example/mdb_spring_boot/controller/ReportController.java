package com.example.mdb_spring_boot.controller;

import com.example.mdb_spring_boot.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.bson.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/skinType")
    public Iterable<Document> generateSkinTypeReport() {
        Iterable<Document> report = reportService.generateSkinReport();
        return report;
    }
}
