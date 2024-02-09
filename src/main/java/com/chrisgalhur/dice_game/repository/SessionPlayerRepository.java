package com.chrisgalhur.dice_game.repository;

import com.chrisgalhur.dice_game.entity.SessionPlayer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

/**
 * Manages the communication with the database for player related requests regarding registration and login.
 *
 * @version 1.0
 * @author ChrisGalHur
 */

@Repository
public interface SessionPlayerRepository extends MongoRepository<SessionPlayer, UUID> {

    //region FIND BY NAME
    /**
     * Find a player by his name.
     *
     * @param username Name of the player to find.
     * @return Player found.
     */
    Optional<SessionPlayer> findByName(String username);
    //endregion FIND BY NAME

    //region EXISTS BY NAME
    /**
     * Check if a player exists by his name.
     *
     * @param name Name of the player to check.
     * @return True if the player exists, false if not.
     */
    boolean existsByName(String name);
    //endregion EXISTS BY NAME

    //region SAVE
    /**
     * Save a player in the database.
     *
     * @param player Player to save.
     * @return Player saved.
     */
    //todo: check if this is necessary
    SessionPlayer save(SessionPlayer player);
    //endregion SAVE
}
