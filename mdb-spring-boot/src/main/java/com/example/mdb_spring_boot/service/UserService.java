package com.example.mdb_spring_boot.service;

import com.example.mdb_spring_boot.exception.InsufficientFundsException;
import com.example.mdb_spring_boot.model.*;
import com.example.mdb_spring_boot.repository.ChestRepository;
import com.example.mdb_spring_boot.repository.LogRepository;
import com.example.mdb_spring_boot.repository.UserRepository;
import com.example.mdb_spring_boot.util.DrawingMachine;
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
    private final ChestRepository chestRepository;

    private final DrawingMachine drawingMachine = new DrawingMachine();

    @Autowired
    public UserService(UserRepository userRepository, LogRepository logRepository, ChestRepository chestRepository){
        this.userRepository = userRepository;
        this.logRepository = logRepository;
        this.chestRepository = chestRepository;
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User addChestToUser(String userId, UserChest userChest) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            Optional<Chest> optionalChest = chestRepository.findAll().stream()
                    .filter(c -> c.getId().equals(userChest.getChestId().toString()))
                    .findFirst();

            if (optionalChest.isPresent()) {
                Chest chest = optionalChest.get();
                double totalPrice = userChest.getQuantity() * chest.getPrice();
                if (user.getDeposit() > totalPrice) {
                    user.addChest(userChest);
                    user.removeFromDeposit(totalPrice);

                    Detail detail = new DetailPurchase(chest.getPrice(), userChest.getQuantity(), "Chest purchase");
                    Log log = new Log(LogType.CHEST_PURCHASE, new ObjectId(userId), new Date().toString(), userChest.getChestId(), detail);
                    logRepository.save(log);

                    return userRepository.save(user);
                }
                else{
                    throw new InsufficientFundsException("User does not have enough money to buy chest or chests");
                }
            }
            else {
                throw new RuntimeException("Chest does not exist");
            }
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

    public User openChest(String userId, String chestId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Tutaj musimy znaleźć skrzynkę użytkownika
            Optional<UserChest> optionalUserChest = user.getChests().stream()
                    .filter(c -> c.getChestId().toString().equals(chestId))
                    .findFirst();

            if (optionalUserChest.isPresent()) {
                UserChest userChest = optionalUserChest.get();
                Optional<Chest> optionalChest = chestRepository.findAll().stream()
                        .filter(c -> c.getId().toString().equals(chestId))
                        .findFirst();

                if (optionalChest.isPresent()){
                    Chest chest = optionalChest.get();
                    UserSkin skin = drawingMachine.getRandomSkin(chest);
                    user.addSkin(skin);

                    // Tworzymy log otwierania skrzynki
                    Detail detail = new DetailOpen(skin.getId(), "Opened skin");
                    Log log = new Log(LogType.CHEST_OPEN, new ObjectId(userId), new Date().toString(), new ObjectId(chestId), detail);
                    logRepository.save(log);

                    user.afterOpeningChest(userChest);

                    // Zapisujemy zmiany w użytkowniku
                    return userRepository.save(user);
                }
                else{
                    throw new RuntimeException("Chest does not exist");
                }
            } else {
                throw new RuntimeException("Chest not found for user");
            }
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
