package com.example.mdb_spring_boot;

import com.example.mdb_spring_boot.model.Chest;
import com.example.mdb_spring_boot.model.User;
import com.example.mdb_spring_boot.model.UserChest;
import com.example.mdb_spring_boot.model.UserSkin;
import com.example.mdb_spring_boot.repository.ChestRepository;
import com.example.mdb_spring_boot.repository.UserRepository;

import com.example.mdb_spring_boot.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableMongoRepositories
public class MdbSpringBootApplication {

	private final UserRepository userRepository;
	private final ChestRepository chestRepository;

	private UserService userService;

	@Autowired
	public MdbSpringBootApplication(UserRepository userRepository, ChestRepository chestRepository, UserService userService) {
		this.userRepository = userRepository;
		this.chestRepository = chestRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(MdbSpringBootApplication.class, args);

		// Sprawdzanie, czy repozytoria są dostępne
		MdbSpringBootApplication application = context.getBean(MdbSpringBootApplication.class);
		application.testDatabaseConnection();

		User userToAdd = new User("John", "Doe", "john.doe@example.com", 100.0);

//		User user = application.userService.addUser(userToAdd);
//		User user = application.userService.getUserById("6654cfb53a9a4d464cd31150");

//		UserSkin skin = new UserSkin("M4A1-S | Hyper Beast",
//				"Rifle",
//				0.10,
//				45,
//				300.00,
//				new ObjectId()
//		);
//
//		UserChest chest = new UserChest(new ObjectId(), 2);
//
//		user = application.userService.addSkinToUser(user.getId(), skin);
//		user = application.userService.addChestToUser(user.getId(), chest);
	}

	public void testDatabaseConnection() {
		List<User> users = userRepository.findAll();
		if (users.isEmpty()) {
			System.out.println("Brak użytkowników w bazie danych.");
		} else {
			System.out.println("Liczba użytkowników w bazie danych: " + users.size());
		}

		List<Chest> chests = chestRepository.findAll();
		if (chests.isEmpty()) {
			System.out.println("Brak skrzynek w bazie danych.");
		} else {
			System.out.println("Liczba skrzynek w bazie danych: " + chests.size());
		}
	}
}