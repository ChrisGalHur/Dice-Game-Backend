package com.chrisgalhur.dice_game.response;

import lombok.Data;

/**
 * Class to manage the authentication response.
 *
 * @version 1.0
 * @author ChrisGalHur
 */

@Data
public class AuthResponse {

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
    public AuthResponse(String accessToken, String message) {
        this.accessToken = accessToken;
        this.message = message;
    }
    //endregion CONSTRUCTOR
}
