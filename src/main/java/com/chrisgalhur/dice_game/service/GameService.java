package com.chrisgalhur.dice_game.service;

import com.chrisgalhur.dice_game.dto.PlayerDTO;
import com.chrisgalhur.dice_game.response.GameResponse;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Interface to manage the game service.
 *
 * @version 1.0
 * @author ChrisGalHur
 */

public interface GameService {

    GameResponse play(PlayerDTO playerDTO);
}
