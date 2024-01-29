package com.chrisgalhur.dice_game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableMongoRepositories(basePackages = "com.chrisgalhur")
public class DiceGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiceGameApplication.class, args);
	}

}
