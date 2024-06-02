package com.example.mdb_spring_boot.service;

import com.example.mdb_spring_boot.model.User;
import com.example.mdb_spring_boot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

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
}
