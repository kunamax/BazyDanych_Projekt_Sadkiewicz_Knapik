package com.example.mdb_spring_boot.controller;

import com.example.mdb_spring_boot.model.Chest;
import com.example.mdb_spring_boot.model.Skin;
import com.example.mdb_spring_boot.service.ChestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/{chestId}/skins")
    public Chest addSkinToChest(@PathVariable String chestId, @RequestBody Skin skin) {
        return chestService.addSkinToChest(chestId, skin);
    }

    @GetMapping
    public List<Chest> getAllChests() {
        return chestService.getAllChests();
    }

    @GetMapping("/{chestId}")
    public Chest getChestById(@PathVariable String chestId) {
        return chestService.getChestById(chestId);
    }
}
