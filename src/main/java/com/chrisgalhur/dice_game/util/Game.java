package com.chrisgalhur.dice_game.util;

import com.chrisgalhur.dice_game.entity.DataPlayer;
import lombok.experimental.UtilityClass;

import java.util.Random;

/**
 * Utility class to manage the game.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
public class Game {

    //region ATTRIBUTES
    private static final Random random = new Random();
    private static final String WINNER = "WINNER";
    private static final String LOSER = "LOSER";
    //endregion ATTRIBUTES

    //region CONSTRUCTOR
    /**
     * Constructor to avoid instantiating the class as it is a util class.
     *
     * @throws IllegalStateException If the class is instantiated.
     */
    private Game() {
        throw new IllegalStateException("Utility class");
    }
    //endregion CONSTRUCTOR

    //region ROLL
    /**
     * Method to roll the dice and get the result.
     *
     * @return DataPlayer with the result of the game (Dices and result).
     */
    public static DataPlayer roll() {
        String result = "";

        byte[] game = {(byte) (random.nextInt(6) + 1), (byte) (random.nextInt(6) + 1)};

        //todo: add new possible result
        if (game[0] + game[1] == 7) {
            result = WINNER;
        } else {
            result = LOSER;
        }

        return new DataPlayer(game[0], game[1], result);
    }
    //endregion ROLL
}
