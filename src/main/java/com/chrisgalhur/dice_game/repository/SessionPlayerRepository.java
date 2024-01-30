package com.chrisgalhur.dice_game.repository;

import com.chrisgalhur.dice_game.model.SessionPlayer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Manages the communication with the database for player related requests regarding registration and login.
 *
 * @see Optional
 * @version 1.0
 * @since 2024-01-30
 * @author ChrisGalHur
 */
@Repository
public interface SessionPlayerRepository extends MongoRepository<SessionPlayer, String> {

    /**
     * Find a player by his name.
     *
     * @param username Name of the player to find.
     * @return Player found.
     */
    Optional<SessionPlayer> findByName(String username);

    /**
     * Check if a player exists by his name.
     *
     * @param name Name of the player to check.
     * @return True if the player exists, false if not.
     */
    boolean existsByName(String name);

    /**
     * Save a player in the database.
     *
     * @param player Player to save.
     * @return Player saved.
     */
    SessionPlayer save(SessionPlayer player);
}
