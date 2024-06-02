package com.example.mdb_spring_boot.service;

import com.example.mdb_spring_boot.model.Chest;
import com.example.mdb_spring_boot.model.Skin;
import com.example.mdb_spring_boot.repository.ChestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<JsonObject> getAllChests() {
        List<Chest> chests = chestRepository.findAll();
        return chests.stream()
                .map(this::convertChestToJson)
                .collect(Collectors.toList());
    }

    private JsonObject convertChestToJson(Chest chest) {
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
        jsonBuilder.add("id", Json.createObjectBuilder().add("chars", chest.getId()).add("string", chest.getId()).add("valueType", "STRING"));
        jsonBuilder.add("name", chest.getName());
        jsonBuilder.add("price", String.valueOf(chest.getPrice()));

        JsonArrayBuilder skinsBuilder = Json.createArrayBuilder();
        chest.getSkins().forEach(skin -> {
            JsonObjectBuilder skinBuilder = Json.createObjectBuilder();
            skinBuilder.add("name", skin.getName());
            skinBuilder.add("rarity", skin.getRarity());
            skinBuilder.add("odds", Json.createObjectBuilder().add("chars", String.valueOf(skin.getOdds())).add("string", String.valueOf(skin.getOdds())).add("valueType", "NUMBER"));
            skinsBuilder.add(skinBuilder);
        });
        jsonBuilder.add("skins", skinsBuilder);

        return jsonBuilder.build();
    }
}
