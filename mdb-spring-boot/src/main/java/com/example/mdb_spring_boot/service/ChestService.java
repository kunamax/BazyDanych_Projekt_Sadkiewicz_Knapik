package com.example.mdb_spring_boot.service;

import com.example.mdb_spring_boot.model.Chest;
import com.example.mdb_spring_boot.model.Skin;
import com.example.mdb_spring_boot.repository.ChestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChestService {
    private final ChestRepository chestRepository;

    @Autowired
    public ChestService(ChestRepository chestRepository){
        this.chestRepository = chestRepository;
    }

    public Chest addChest(Chest chest){
        return chestRepository.save(chest);
    }

    public Chest getChestById(String chestId){
        return chestRepository.findById(chestId).orElseThrow(() -> new RuntimeException("Chest not found"));
    }

    public Chest addSkinToChest(String chestId, Skin skin){
        Chest chest = chestRepository.findById(chestId).orElseThrow(() -> new RuntimeException("Chest not found"));
        chest.addSkin(skin);
        return chestRepository.save(chest);
    }
}
