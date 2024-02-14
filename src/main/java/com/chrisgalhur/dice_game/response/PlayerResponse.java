package com.chrisgalhur.dice_game.response;


import lombok.Getter;

/**
 * Class to manage the player response.
 *
 * @version 1.0
 * @author ChrisGalHur
 */

@Getter
public class PlayerResponse{

    //region ATTRIBUTES
    private final String message;
    //endregion ATTRIBUTES

    //region CONSTRUCTOR
    /**
     * Constructor of the class.
     *
     * @param message The message.
     */
    public PlayerResponse(String message) {
        this.message = message;
    }
    //endregion CONSTRUCTOR
}
