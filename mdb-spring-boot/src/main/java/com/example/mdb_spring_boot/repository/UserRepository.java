package com.example.mdb_spring_boot.repository;

import com.example.mdb_spring_boot.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
