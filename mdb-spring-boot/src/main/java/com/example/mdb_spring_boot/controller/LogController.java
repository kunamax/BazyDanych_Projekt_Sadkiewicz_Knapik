package com.example.mdb_spring_boot.controller;

import com.example.mdb_spring_boot.service.LogService;
import com.example.mdb_spring_boot.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {
    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping
    public Log addLog(@RequestBody Log log) {
        return logService.addLog(log);
    }

    @GetMapping("/all")
    public List<Log> getAllLogs() {
        return logService.getAllLogs();
    }

    @GetMapping("/{id}")
    public Log getLogById(@PathVariable String id) {
        return logService.getLogById(id);
    }
}
