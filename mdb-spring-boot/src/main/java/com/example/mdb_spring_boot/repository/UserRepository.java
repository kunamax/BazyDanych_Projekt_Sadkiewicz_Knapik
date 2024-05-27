package com.example.mdb_spring_boot.repository;

import com.example.mdb_spring_boot.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
