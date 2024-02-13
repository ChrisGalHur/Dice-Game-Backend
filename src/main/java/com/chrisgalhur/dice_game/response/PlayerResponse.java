package com.chrisgalhur.dice_game.response;

import lombok.Data;

/**
 * Class to manage the player response.
 *
 * @version 1.0
 * @author ChrisGalHur
 */

@Data
public class PlayerResponse{

    //region ATTRIBUTES
    private String message;
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
