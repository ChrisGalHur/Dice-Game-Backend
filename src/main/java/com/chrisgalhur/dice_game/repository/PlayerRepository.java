package com.chrisgalhur.dice_game.repository;

import com.chrisgalhur.dice_game.service.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Manages the communication with the database for requests related and their games, except for registration and login.
 *
 * @see com.chrisgalhur.dice_game.service.Player
 * @version 1.0
 * @since 2024-01-30
 * @author ChrisGalHur
 */
@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {

}
