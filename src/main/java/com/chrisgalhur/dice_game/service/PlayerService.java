package com.chrisgalhur.dice_game.service;

import com.chrisgalhur.dice_game.dto.PlayerDTO;
import com.chrisgalhur.dice_game.exception.InvalidPlayerException;
import com.chrisgalhur.dice_game.response.DataPlayerResponse;
import com.chrisgalhur.dice_game.response.PlayerResponse;

/**
 * Interface for the player service.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
public interface PlayerService {

    //region UPDATE NAME
    /**
     * Method to register a new player in the database.
     *
     * @param playerDTO Player to register.
     * @return PlayerResponse with the player information.
     */
    PlayerResponse updateName(PlayerDTO playerDTO);
    //endregion UPDATE NAME

    //region DELETE PLAYER
    /**
     * Method to delete a player from the database.
     *
     * @return PlayerResponse with the player information.
     * @throws InvalidPlayerException If the player is not found.
     */
    PlayerResponse deletePlayerHistory() throws InvalidPlayerException;
    //endregion DELETE PLAYER

    //region GET PLAYER HISTORY
    /**
     * Method to get the player history from the database.
     *
     * @return DataPlayerResponse with the player history.
     */
    DataPlayerResponse getPlayerHistory();
    //endregion GET PLAYER HISTORY
}
