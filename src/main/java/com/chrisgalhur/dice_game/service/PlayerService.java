package com.chrisgalhur.dice_game.service;

import com.chrisgalhur.dice_game.model.SessionPlayerDTO;

/**
 * Interface to manage the player service.
 *
 * @version 1.0
 * @since 2024-01-29
 * @author ChrisGalHur
 */
public interface PlayerService {

    /**
     * Method to verify if the player exists by name in the database.
     *
     * @param name Name of the player.
     * @return True if the player exists in the database, false if not.
     */
    boolean existsByName(String name);

    /**
     * Method to register a new player in the database.
     *
     * @param playerDTO Player to register.
     * @return
     */
    SessionPlayerDTO registerNewUser(SessionPlayerDTO playerDTO);
}
