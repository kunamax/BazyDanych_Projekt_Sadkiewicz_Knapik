package com.example.mdb_spring_boot.controller;

import com.example.mdb_spring_boot.model.User;
import com.example.mdb_spring_boot.model.UserChest;
import com.example.mdb_spring_boot.model.UserSkin;
import com.example.mdb_spring_boot.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PostMapping("/{userId}/chests")
    public User addChestToUser(@PathVariable String userId, @RequestBody UserChest chest) {
        chest.setChestId(new ObjectId(chest.getChestId().toString()));
        return userService.addChestToUser(userId, chest);
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{userId}/skins")
    public User addSkinToUser(@PathVariable String userId, @RequestBody UserSkin skin) {
        return userService.addSkinToUser(userId, skin);
    }

    @PostMapping("/open-chest")
    public User openChest(@RequestParam String userId, @RequestParam String chestId) {
        return userService.openChest(userId, chestId);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
