package com.chrisgalhur.dice_game.service;

import com.chrisgalhur.dice_game.dto.SessionPlayerDTO;

/**
 * Interface to manage the player service.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
public interface SessionPlayerService {

    //region LOGIN USER
    /**
     * Method to log in a player.
     *
     * @param sessionPlayerDTO Session of the player to log in.
     * @return SessionPlayerDTO The session of the player logged in.
     */
    SessionPlayerDTO loginUser(SessionPlayerDTO sessionPlayerDTO);
    //endregion LOGIN USER

    //region EXISTS BY NAME
    /**
     * Method to verify if the player exists by name in the database.
     *
     * @param name Name of the player.
     * @return True if the player exists in the database, false if not.
     */
    boolean existsByName(String name);
    //endregion EXISTS BY NAME

    //region REGISTER NEW USER
    /**
     * Method to register a new player in the database.
     *
     * @param playerDTO Player to register.
     * @return SessionPlayerDTO with the player information.
     */
    SessionPlayerDTO registerNewUser(SessionPlayerDTO playerDTO);
    //endregion REGISTER NEW USER

    //region UPDATE NAME
    /**
     * Method to update the session player name.
     *
     * @param name The new name of the player.
     * @param newName The new name of the player.
     */
    void updateName(String name, String newName);
    //endregion UPDATE NAME
}
