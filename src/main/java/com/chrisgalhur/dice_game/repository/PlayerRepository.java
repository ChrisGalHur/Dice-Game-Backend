package com.chrisgalhur.dice_game.repository;

import com.chrisgalhur.dice_game.service.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Manages the communication with the database for requests related and their games, except for registration and login.
 *
 * @version 1.0
 * @author ChrisGalHur
 */

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {

    //region FIND BY NAME
    /**
     * Find a player by his name.
     *
     * @param username Name of the player to find.
     * @return Player found or empty.
     */
    Optional<Player> findByName(String username);
    //endregion FIND BY NAME
}
