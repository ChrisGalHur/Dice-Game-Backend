package com.chrisgalhur.dice_game.service;

import com.chrisgalhur.dice_game.dto.PlayerDTO;
import com.chrisgalhur.dice_game.response.PlayerResponse;

/**
 * Interface for the player service.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
public interface PlayerService {
    PlayerResponse updateName(PlayerDTO playerDTO);
}
