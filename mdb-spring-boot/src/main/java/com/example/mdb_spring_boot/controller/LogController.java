package com.example.mdb_spring_boot.controller;

import com.example.mdb_spring_boot.service.LogService;
import com.example.mdb_spring_boot.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
