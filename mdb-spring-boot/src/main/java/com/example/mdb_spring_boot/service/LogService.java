package com.example.mdb_spring_boot.service;

import com.example.mdb_spring_boot.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mdb_spring_boot.model.Log;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public List<Log> getAllLogs() {
        return logRepository.findAll();
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
