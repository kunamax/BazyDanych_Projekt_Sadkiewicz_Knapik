package com.example.mdb_spring_boot.service;

import com.example.mdb_spring_boot.model.User;
import com.example.mdb_spring_boot.model.UserChest;
import com.example.mdb_spring_boot.model.UserSkin;
import com.example.mdb_spring_boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User addChestToUser(String userId, UserChest chest) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.addChest(chest);
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
}
