package com.example.mdb_spring_boot.service;

import com.example.mdb_spring_boot.exception.InsufficientFundsException;
import com.example.mdb_spring_boot.model.*;
import com.example.mdb_spring_boot.repository.UserRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private ChestService chestService;

    @Autowired
    private LogService logService;

    @Test
    public void testAddUserValid() {
        User user = new User("Name", "Surname", "test@example.pl", 100);

        User savedUser = userService.addUser(user);

        assertNotNull(savedUser);
        assertEquals(user.getName(), savedUser.getName());
        assertEquals(user.getSurname(), savedUser.getSurname());
        assertEquals(user.getEmail(), savedUser.getEmail());

        userService.removeUser(user);
    }

    @Test
    public void testAddUserError(){
        User user = new User("Name", "Surname", "testexample.pl", 100);

        assertThrows(DataIntegrityViolationException.class, () -> {
            userService.addUser(user);
        });
    }

    private Chest createTestChest(){
        Skin skin1 = new Skin("Name1", "Type1", "Rarity1", 0.5,100.0);
        Skin skin2 = new Skin("Name2", "Type2", "Rarity2", 0.5,100.0);
        return new Chest("ChestName", 100, List.of(skin1, skin2));
    }

    @Test
    public void testAddChestToUserValid(){
        // preparing test environment
        User user = new User("Name", "Surname", "teste@xample.pl", 100);
        User savedUser = userService.addUser(user);

        Chest chest = createTestChest();
        chestService.addChest(chest);

        UserChest userChest = new UserChest(new ObjectId(chest.getId()), 1);

        // adding chest to user
        User updatedUser = userService.addChestToUser(savedUser.getId(), userChest);

        // actual tests
        List<UserChest> chestsList = updatedUser.getChests();
        assertEquals(1, chestsList.size());
        assertEquals(chest.getId(), chestsList.get(0).getChestId().toString());
        assertEquals(userChest.getQuantity(), chestsList.get(0).getQuantity());

        List<Log> logList = logService.getLogsByUserId(user.getId());
        assertEquals(1, logList.size());

        // cleaning test environment
        userService.removeUser(user);
        chestService.removeChest(chest);
        logService.removeLog(logList.get(0));
    }

    @Test
    public void testAddChestToUserError(){
        // preparing test environment
        User user = new User("Name", "Surname", "teste@xample.pl", 50);
        User savedUser = userService.addUser(user);

        Chest chest = createTestChest();
        chestService.addChest(chest);

        UserChest userChest = new UserChest(new ObjectId(chest.getId()), 1);

        // adding chest to user
        assertThrows(InsufficientFundsException.class, () -> {
            userService.addChestToUser(savedUser.getId(), userChest);
        });
        userService.removeUser(user);
        chestService.removeChest(chest);
    }

    @Test
    public void testOpenChestToUserValid(){
        // preparing test environment
        User user = new User("Name", "Surname", "teste@xample.pl", 100);
        User savedUser = userService.addUser(user);

        Chest chest = createTestChest();
        chestService.addChest(chest);

        UserChest userChest = new UserChest(new ObjectId(chest.getId()), 1);

        // adding chest to user
        userService.addChestToUser(savedUser.getId(), userChest);

        // opening chest
        User updatedUser = userService.openChest(savedUser.getId(), chest.getId());

        // actual tests
        List<UserSkin> listUserSkin = updatedUser.getSkins();
        assertEquals(1, listUserSkin.size());

        List<Skin> skinList = chest.getSkins();

        assertTrue(listUserSkin.get(0).getSkinId().equals(skinList.get(0).getId()) || listUserSkin.get(0).getSkinId().equals(skinList.get(1).getId()));

        List<Log> logList = logService.getLogsByUserId(user.getId());
        assertEquals(2, logList.size());

        // cleaning test environment
        userService.removeUser(user);
        chestService.removeChest(chest);
        logService.removeLog(logList.get(0));
        logService.removeLog(logList.get(1));
    }
}
