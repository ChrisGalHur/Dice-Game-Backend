package com.chrisgalhur.dice_game.service;

import com.chrisgalhur.dice_game.dto.PlayerDTO;
import com.chrisgalhur.dice_game.entity.DataPlayer;
import com.chrisgalhur.dice_game.entity.Player;
import com.chrisgalhur.dice_game.exception.InvalidPlayerException;
import com.chrisgalhur.dice_game.repository.PlayerRepository;
import com.chrisgalhur.dice_game.response.GameResponse;
import com.chrisgalhur.dice_game.util.Game;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Class to manage the game service.
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
     * @param request  The request.
     * @return GameResponse The game response.
     */
    @Override
    public GameResponse play(PlayerDTO playerDTO, HttpServletRequest request) {
        Player playerPlaying = validateGame(playerDTO);

        //make a dice roll
        DataPlayer savingGame = Game.roll();

        //save the game
        playerPlaying.getDataPlayer().add(savingGame);
        playerRepository.save(playerPlaying);

        return new GameResponse(savingGame.getResult(), (byte) savingGame.getNumDice1(), (byte) savingGame.getNumDice2());
    }

    private Player validateGame(PlayerDTO playerDTO) {

        if(playerDTO == null){
            throw new InvalidPlayerException("Error: The player is required.");
            //todo: manejar error por codigos?
        }

        // Validate the player id.
        if(playerDTO.getId() == null || playerDTO.getId().isEmpty() || playerDTO.getId().isBlank()){
            throw new InvalidPlayerException("Error: The player id is required.");
            //todo: manejar error por codigos?
        }else {
            // Check the player id.
            if (!playerRepository.existsById(playerDTO.getId())) {
                throw new InvalidPlayerException("Error: This player does not exist.");
                //todo: manejar error por codigos?
            }else{
                // Get the player
                Player playerPlaying = playerRepository.findById(playerDTO.getId()).orElse(null);

                if(playerPlaying != null){
                    if(playerPlaying.getDataPlayer() == null){
                        playerPlaying.setDataPlayer(new ArrayList<>());

                        return playerPlaying;
                    }
                }
                throw new InvalidPlayerException("Error: The player does not exist.");
                //todo: manejar error por codigos?
            }
        }
    }
    //endregion PLAY
}
