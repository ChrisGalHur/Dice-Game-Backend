package com.chrisgalhur.dice_game.response;

import lombok.Getter;

/**
 * Class to manage the game response.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
@Getter
public class GameResponse {

    //region ATTRIBUTES
    private String message;
    private byte dice1;
    private byte dice2;
    //endregion ATTRIBUTES

    //region CONSTRUCTOR
    /**
     * Constructor of the class.
     *
     * @param message The message.
     * @param dice1 The first dice.
     * @param dice2 The second dice.
     */
    public GameResponse(String message, byte dice1, byte dice2) {
        this.message = message;
        this.dice1 = dice1;
        this.dice2 = dice2;
    }
    //endregion CONSTRUCTOR
}
