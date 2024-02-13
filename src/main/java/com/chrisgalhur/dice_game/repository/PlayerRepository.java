package com.chrisgalhur.dice_game.repository;

import com.chrisgalhur.dice_game.dto.PlayerDTO;
import com.chrisgalhur.dice_game.entity.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Manages the communication with the database for requests related and their games, except for registration and login.
 *
 * @version 1.0
 * @author ChrisGalHur
 */

@Repository
public interface PlayerRepository extends MongoRepository<Player, UUID> {

    //region FIND BY NAME
    /**
     * Find a player by his name.
     *
     * @param username Name of the player to find.
     * @return Player found or empty.
     */
    Optional<Player> findByName(String username);
    //endregion FIND BY NAME

    //region FIND BY ID
    /**
     * Find a player by his id.
     *
     * @param id Id of the player to find.
     * @return Player found or empty.
     */
    Optional<Player> findById(UUID id);
    //endregion FIND BY ID

    //region EXISTS BY NAME
    /**
     * Check if a player exists by his name.
     *
     * @param playerName Name of the player to check.
     * @return True if the player exists, false otherwise.
     */
    boolean existsByName(String playerName);
    //endregion EXISTS BY NAME
}
