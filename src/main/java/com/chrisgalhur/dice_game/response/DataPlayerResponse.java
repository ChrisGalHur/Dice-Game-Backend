package com.chrisgalhur.dice_game.response;

import com.chrisgalhur.dice_game.entity.DataPlayer;
import lombok.Getter;

import java.util.List;

@Getter
public class DataPlayerResponse {

    //region ATTRIBUTES
    private final String message;
    List<DataPlayer> dataPlayerList;
    //endregion ATTRIBUTES

    //region CONSTRUCTORS
    public DataPlayerResponse(String message, List<DataPlayer> dataPlayerList) {
        this.message = message;
        this.dataPlayerList = dataPlayerList;
    }
    //endregion CONSTRUCTORS
}
