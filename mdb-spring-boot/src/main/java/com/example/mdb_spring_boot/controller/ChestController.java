package com.example.mdb_spring_boot.controller;

import com.example.mdb_spring_boot.model.Chest;
import com.example.mdb_spring_boot.service.ChestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chests")
public class ChestController {
    private final ChestService chestService;

    @Autowired
    public ChestController(ChestService chestService) {
        this.chestService = chestService;
    }

    @PostMapping
    public Chest addChest(@RequestBody Chest chest) {
        return chestService.addChest(chest);
    }
}
