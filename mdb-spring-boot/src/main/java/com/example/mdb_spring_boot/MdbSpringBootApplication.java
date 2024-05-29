package com.example.mdb_spring_boot;

import com.example.mdb_spring_boot.model.*;
import com.example.mdb_spring_boot.repository.ChestRepository;
import com.example.mdb_spring_boot.repository.LogRepository;
import com.example.mdb_spring_boot.repository.UserRepository;

import com.example.mdb_spring_boot.service.UserService;
import com.example.mdb_spring_boot.service.ChestService;
import com.example.mdb_spring_boot.service.LogService;
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
    private final LogRepository logRepository;

	private UserService userService;
    private ChestService chestService;
    private LogService logService;

	@Autowired
	public MdbSpringBootApplication(UserRepository userRepository, ChestRepository chestRepository, LogRepository logRepository,
                                    UserService userService, ChestService chestService, LogService logService) {
		this.userRepository = userRepository;
		this.chestRepository = chestRepository;
        this.logRepository = logRepository;
		this.userService = userService;
        this.chestService = chestService;
        this.logService = logService;
	}

	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(MdbSpringBootApplication.class, args);

		// Sprawdzanie, czy repozytoria są dostępne
		MdbSpringBootApplication application = context.getBean(MdbSpringBootApplication.class);
		application.testDatabaseConnection();


//		Detail detail = new DetailOpen(new ObjectId("6654cfb53a9a4d464cd31154"), "Opened skin");

//        Log logToAdd = new Log(LogType.CHEST_OPEN, new ObjectId("6654cfb53a9a4d464cd31150"), "2024-05-27T13:00:00Z",
//                new ObjectId("6654cfb53a9a4d464cd31153"), detail
//        );
//        Log log = application.logService.addLog(logToAdd);

//		Detail detail = new DetailPurchase(2137, 420, "O PANIEEEE...");
//		Log logToAdd = new Log(LogType.CHEST_PURCHASE, new ObjectId(
//				"6654cfb53a9a4d464cd31150"),
//				"2024-05-27T13:00:00Z",
//				new ObjectId("6654cfb53a9a4d464cd31153"),
//				detail
//		);
//
//		Log log = application.logService.addLog(logToAdd);


//		User userToAdd = new User("John", "Doe", "john.doe@example.com", 100.0);
//        Skin skin1 = new Skin("M4A4 | Howl", "Covert", 0.01);
//        Skin skin2 = new Skin("AK-47 | Fire Serpent", "Classified", 0.02);
//        Chest chestToAdd = new Chest("Bravo Case", 3.00, List.of(skin1, skin2));
//
//        Chest chest = application.chestService.addChest(chestToAdd);

//        Chest chest = application.chestService.getChestById("665625a5f1523b46b784ae89");
//        System.out.println(chest.getName());


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