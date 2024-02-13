package com.chrisgalhur.dice_game.service;

import com.chrisgalhur.dice_game.dto.PlayerDTO;
import com.chrisgalhur.dice_game.entity.Player;
import com.chrisgalhur.dice_game.exception.InvalidPlayerException;
import com.chrisgalhur.dice_game.response.GameResponse;
import com.chrisgalhur.dice_game.response.PlayerResponse;
import com.chrisgalhur.dice_game.repository.PlayerRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Interface for the player service implementation.
 * @see PlayerService for the interface.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
@Service
public class PlayerServiceImpl implements PlayerService{

    //region INJECTIONS
    private final PlayerRepository playerRepository;

    /**
     * Constructor of the class to inject the dependencies.
     *
     * @param playerRepository The player repository.
     */
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    //endregion INJECTIONS

    //region UPDATE NAME
    /**
     * Method to update the player name.
     * This method is responsible for:
     * - Check the player and his name is not empty or null.
     * - Check the player name is not already in use.
     * - Update the player name using the security context.
     *
     * @param playerDTO The player DTO.
     * @return PlayerResponse The player response.
     */
    @Override
    public PlayerResponse updateName(PlayerDTO playerDTO) {
        try{
            if(playerDTO == null || playerDTO.getName() == null || playerDTO.getName().isEmpty()){
                throw new InvalidPlayerException("Invalid player name");
            }

            if (playerRepository.existsByName(playerDTO.getName())) {
                throw new InvalidPlayerException("Player name already exists");
            }

            //get the player name from the security context to find the player
            String playerName = SecurityContextHolder.getContext().getAuthentication().getName();

            Player playerNewName = playerRepository.findByName(playerName).orElseThrow(() -> new InvalidPlayerException("Player not found"));
            playerNewName.setName(playerDTO.getName());
            playerRepository.save(playerNewName);

            return new PlayerResponse("Player name updated");
        }catch (InvalidPlayerException e){
            return new PlayerResponse("Something wrong. Player name not updated");
        }
    }
    //endregion UPDATE NAME
}
