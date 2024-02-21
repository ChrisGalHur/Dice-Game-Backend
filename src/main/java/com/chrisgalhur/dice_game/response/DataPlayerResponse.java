package com.chrisgalhur.dice_game.response;

import com.chrisgalhur.dice_game.entity.DataPlayer;
import lombok.Getter;
import java.util.List;

/**
 * Class to manage the data player response.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
@Getter
public class DataPlayerResponse {

    //region ATTRIBUTES
    private final String message;
    List<DataPlayer> dataPlayerList;
    //endregion ATTRIBUTES

    //region CONSTRUCTORS
    /**
     * Constructor of the class.
     *
     * @param message The message.
     * @param dataPlayerList The list of data players.
     */
    public DataPlayerResponse(String message, List<DataPlayer> dataPlayerList) {
        this.message = message;
        this.dataPlayerList = dataPlayerList;
    }
    //endregion CONSTRUCTORS
}
