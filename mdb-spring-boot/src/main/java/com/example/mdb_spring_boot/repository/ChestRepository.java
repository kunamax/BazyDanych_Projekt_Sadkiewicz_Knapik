package com.example.mdb_spring_boot.repository;

import com.example.mdb_spring_boot.model.Chest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChestRepository extends MongoRepository<Chest, String> {
}
