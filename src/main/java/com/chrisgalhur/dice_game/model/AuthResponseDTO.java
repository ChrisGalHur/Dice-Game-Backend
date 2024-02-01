package com.chrisgalhur.dice_game.model;

import lombok.Data;

/**
 * Class to manage the authentication response.
 *
 * @version 1.0
 * @since 2021-01-31
 * @author ChrisGalHur
 */
@Data
public class AuthResponseDTO {

    private String accessToken;

    private String tokenType = "Bearer ";

    private String message;

    /**
     * Constructor of the class.
     *
     * @param accessToken The access token.
     */
    public AuthResponseDTO(String accessToken, String message) {
        this.accessToken = accessToken;
        this.message = message;
    }
}
