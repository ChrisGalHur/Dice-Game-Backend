package com.chrisgalhur.dice_game.service;

import com.chrisgalhur.dice_game.dto.PlayerDTO;
import com.chrisgalhur.dice_game.entity.DataPlayer;
import com.chrisgalhur.dice_game.entity.Player;
import com.chrisgalhur.dice_game.exception.InvalidPlayerException;
import com.chrisgalhur.dice_game.repository.PlayerRepository;
import com.chrisgalhur.dice_game.response.GameResponse;
import com.chrisgalhur.dice_game.util.Game;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Class to manage the game service implementation.
 * @see GameService for the interface.
 *
 * @version 1.0
 * @author ChrisGalHur
 */

@Service
public class GameServiceImpl implements GameService{

    //region DEPENDENCY INJECTION
    private final PlayerRepository playerRepository;

    /**
     * Constructor of the class to inject the dependencies.
     *
     * @param playerRepository The player repository.
     */
    public GameServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    //endregion DEPENDENCY INJECTION

    //region PLAY
    /**
     * Method to play the game.
     * This method is responsible for:
     * - Check the player id.
     * - Send player token to authenticate the request.
     * - Make a dice roll.
     *
     * @param playerDTO The player DTO.
     * @return GameResponse The game response.
     */
    @Override
    public GameResponse play(PlayerDTO playerDTO) {
        try{
            Player playerPlaying = validateGame(playerDTO);

            //make a dice roll
            DataPlayer savingGame = Game.roll();

            //save the game
            playerPlaying.getDataPlayer().add(savingGame);
            playerRepository.save(playerPlaying);

            return new GameResponse(savingGame.getResult(), (byte) savingGame.getNumDice1(), (byte) savingGame.getNumDice2());
        }catch (InvalidPlayerException e){
            return new GameResponse(null, (byte) 0, (byte) 0);
        }
    }

    private Player validateGame(PlayerDTO playerDTO) {
        try {
            if (playerDTO == null) {
                throw new InvalidPlayerException("Error: The player is required.");
                //todo: manejar error por codigos?
            }

            // Get the player name from the security context to find the player
            String playerName = SecurityContextHolder.getContext().getAuthentication().getName();

            // Check the player id.
            if (!playerRepository.existsByName(playerName)) {
                throw new InvalidPlayerException("Error: This player does not exist.");
                //todo: manejar error por codigos?
            } else {
                // Get the player
                Player playerPlaying = playerRepository.findByName(playerName).orElse(null);

                if (playerPlaying != null) {
                    if (playerPlaying.getDataPlayer() == null) {
                        playerPlaying.setDataPlayer(new ArrayList<>());
                        return playerPlaying;
                    }
                    return playerPlaying;
                }
                throw new InvalidPlayerException("Error: The player does not exist.");
                //todo: manejar error por codigos?
            }
        } catch (InvalidPlayerException e) {
            throw new InvalidPlayerException("error authenticating the player");
        }
    }
    //endregion PLAY
}
