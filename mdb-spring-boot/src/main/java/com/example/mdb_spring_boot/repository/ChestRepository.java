package com.example.mdb_spring_boot.repository;

import com.example.mdb_spring_boot.model.Chest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChestRepository extends MongoRepository<Chest, String> {
}
