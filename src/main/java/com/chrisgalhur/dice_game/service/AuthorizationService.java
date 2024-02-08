package com.chrisgalhur.dice_game.service;

import com.chrisgalhur.dice_game.response.AuthResponse;
import com.chrisgalhur.dice_game.dto.SessionPlayerDTO;

/**
 * Interface to manage the authorization service.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
public interface AuthorizationService {

    //region AUTHENTICATE PLAYER
    /**
     * Method to authenticate a player.
     *
     * @param sessionPlayerDTO SessionPlayerDTO with the player information.
     * @return AuthResponseDTO with the authentication response.
     */
    AuthResponse authenticateRegisterPlayer(SessionPlayerDTO sessionPlayerDTO);
    //endregion AUTHENTICATE PLAYER

    //region AUTHENTICATE LOGIN PLAYER
    /**
     * Method to authenticate the login of a player.
     *
     * @param sessionPlayerDTO Session of the player to log in.
     * @return AuthResponseDTO Authentication response.
     */
    AuthResponse authenticateLoginPlayer(SessionPlayerDTO sessionPlayerDTO);
    //endregion AUTHENTICATE LOGIN PLAYER
}
