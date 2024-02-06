package com.chrisgalhur.dice_game.service;

import com.chrisgalhur.dice_game.model.AuthResponseDTO;
import com.chrisgalhur.dice_game.model.SessionPlayerDTO;

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
    AuthResponseDTO authenticateRegisterPlayer(SessionPlayerDTO sessionPlayerDTO);

    AuthResponseDTO authenticateLoginPlayer(SessionPlayerDTO sessionPlayerDTO);
    //endregion AUTHENTICATE PLAYER
}
