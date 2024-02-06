package com.chrisgalhur.dice_game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class of the application.
 * This class is responsible for starting the Spring Boot application.
 *
 * @version 1.0
 * @author ChrisGalHur
 */

@SpringBootApplication
public class DiceGameApplication {

	/**
	 * Main method of the application.
	 * This method starts the Spring Boot application.
	 *
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(DiceGameApplication.class, args);
	}
}
