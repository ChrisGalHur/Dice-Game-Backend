package com.chrisgalhur.dice_game.model;

import lombok.Data;
import lombok.Setter;

/**
 * Class to manage the authentication response.
 *
 * @version 1.0
 * @author ChrisGalHur
 */

@Data
public class AuthResponseDTO {

    //region ATTRIBUTES
    private String accessToken;

    private String tokenType = "Bearer ";

    private String message;
    //endregion ATTRIBUTES

    //region CONSTRUCTOR
    /**
     * Constructor of the class.
     *
     * @param accessToken The access token.
     * @param message The message.
     */
    public AuthResponseDTO(String accessToken, String message) {
        this.accessToken = accessToken;
        this.message = message;
    }
    //endregion CONSTRUCTOR
}
