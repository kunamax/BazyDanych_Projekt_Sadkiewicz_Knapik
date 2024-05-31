package com.example.mdb_spring_boot.service;

import com.example.mdb_spring_boot.model.*;
import com.example.mdb_spring_boot.controller.LogController;
import com.example.mdb_spring_boot.repository.LogRepository;
import com.example.mdb_spring_boot.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final LogRepository logRepository;

    @Autowired
    public UserService(UserRepository userRepository, LogRepository logRepository){
        this.userRepository = userRepository;
        this.logRepository = logRepository;
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User addChestToUser(String userId, UserChest chest) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.addChest(chest);

            Detail detail = new DetailPurchase(100, chest.getQuantity(), "Chest purchase");
            Log log = new Log(LogType.CHEST_OPEN, new ObjectId(userId), new Date().toString(), chest.getChestId(), detail);
            logRepository.save(log);

            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public User addSkinToUser(String userId, UserSkin skin) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.addSkin(skin);
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public User getUserById(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
