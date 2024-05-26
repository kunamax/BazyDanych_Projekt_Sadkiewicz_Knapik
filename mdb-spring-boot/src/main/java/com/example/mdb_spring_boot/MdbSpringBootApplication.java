package com.example.mdb_spring_boot;

import com.example.mdb_spring_boot.model.Chest;
import com.example.mdb_spring_boot.model.User;
import com.example.mdb_spring_boot.repository.ChestRepository;
import com.example.mdb_spring_boot.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
public class MdbSpringBootApplication {

	private final UserRepository userRepository;
	private final ChestRepository chestRepository;

	@Autowired
	public MdbSpringBootApplication(UserRepository userRepository, ChestRepository chestRepository) {
		this.userRepository = userRepository;
		this.chestRepository = chestRepository;
	}

	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(MdbSpringBootApplication.class, args);

		// Sprawdzanie, czy repozytoria są dostępne
		MdbSpringBootApplication application = context.getBean(MdbSpringBootApplication.class);
		application.testDatabaseConnection();
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
