package com.chrisgalhur.dice_game.service;

import com.chrisgalhur.dice_game.dto.PlayerDTO;
import com.chrisgalhur.dice_game.entity.DataPlayer;
import com.chrisgalhur.dice_game.entity.Player;
import com.chrisgalhur.dice_game.exception.InvalidPlayerException;
import com.chrisgalhur.dice_game.response.DataPlayerResponse;
import com.chrisgalhur.dice_game.response.PlayerResponse;
import com.chrisgalhur.dice_game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface for the player service implementation.
 * @see PlayerService for the interface.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
@Service
public class PlayerServiceImpl implements PlayerService{

    //region ATTRIBUTES
    private static final String PLAYER_NOT_FOUND = "Player not found";
    //endregion ATTRIBUTES

    //region INJECTIONS
    private final PlayerRepository playerRepository;
    private final SessionPlayerServiceImpl sessionPlayerService;

    /**
     * Constructor of the class to inject the dependencies.
     *
     * @param playerRepository The player repository.
     * @param sessionPlayerService The session player service.
     */
    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, SessionPlayerServiceImpl sessionPlayerService) {
        this.playerRepository = playerRepository;
        this.sessionPlayerService = sessionPlayerService;
    }
    //endregion INJECTIONS

    //region UPDATE NAME
    /**
     * Method to update the player name.
     * This method is responsible for:
     * - Check the player and his name is not empty or null.
     * - Check the player name is not already in use or is the same as the current name.
     * - Update the player name using the security context.
     *
     * @param playerDTO The player DTO.
     * @return PlayerResponse The player response.
     * @throws InvalidPlayerException If the player name is empty, null, already exists or is the same as the current name.
     */
    @Override
    public PlayerResponse updateName(PlayerDTO playerDTO){
            if(playerDTO == null || playerDTO.getName() == null || playerDTO.getName().isEmpty()){
                throw new InvalidPlayerException("Invalid player name");
            }

            if (playerRepository.existsByName(playerDTO.getName())) {
                throw new InvalidPlayerException("Player name already exists");
            }

            String playerName = getPlayerId();

            Player playerNewName = playerRepository.findByName(playerName).orElseThrow(() -> new InvalidPlayerException(PLAYER_NOT_FOUND));
            if(playerDTO.getName().equals(playerNewName.getName())){
                throw new InvalidPlayerException("This is already your current name");
            }else {
                playerNewName.setName(playerDTO.getName());
                sessionPlayerService.updateName(playerName, playerNewName.getName());
                playerRepository.save(playerNewName);
            }

            return new PlayerResponse("Player name updated");
    }
    //endregion UPDATE NAME

    //region DELETE PLAYER HISTORY
    /**
     * Method to delete the player history.
     * This method is responsible for:
     * - Check the player id.
     * - Send player token to authenticate the request.
     * - Delete the player history.
     *
     * @param playerDTO The player DTO.
     * @return PlayerResponse The player response.
     * @throws InvalidPlayerException If the player name is empty or null.
     */
    @Override
    public PlayerResponse deletePlayerHistory(PlayerDTO playerDTO){

        if (playerDTO == null || playerDTO.getName() == null || playerDTO.getName().isEmpty()) {
            throw new InvalidPlayerException("Invalid player name");
        }

        String playerName = getPlayerId();

        Player player = playerRepository.findByName(playerName).orElseThrow(() -> new InvalidPlayerException(PLAYER_NOT_FOUND));
        List<DataPlayer> dataPlayer = player.getDataPlayer();

        if (dataPlayer.isEmpty()) {
            throw new InvalidPlayerException("Player history is empty");
        }
        player.getDataPlayer().clear();
        playerRepository.save(player);

        return new PlayerResponse("Player history deleted");
    }
    //endregion DELETE PLAYER HISTORY

    //region GET HISTORY PLAYER
    /**
     * Method to get the player history.
     * This method is responsible for:
     * - Check the player id.
     * - Search the player history by id.
     * - Return the player history.
     *
     *
     */
    @Override
    public DataPlayerResponse getPlayerHistory(){
        String playerName = getPlayerId();

        Player player = playerRepository.findByName(playerName).orElseThrow(() -> new InvalidPlayerException(PLAYER_NOT_FOUND));
        DataPlayerResponse dataPlayer = new DataPlayerResponse("Player history", player.getDataPlayer());

        if(player.getDataPlayer().isEmpty()){
            throw new InvalidPlayerException("Player history is empty");
        }else {
            return dataPlayer;
        }
    }
    //endregion GET HISTORY PLAYER

    //region UTILS
    /**
     * Method to get the player ID from the security context.
     * This method is responsible for:
     * - Check if the player ID is in the security context.
     * - Get the player ID from the security context.
     * - Return the player ID.
     *
     * @return String The player ID.
     */
    private String getPlayerId(){
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        if(id == null || id.isEmpty()){
            throw new InvalidPlayerException(PLAYER_NOT_FOUND);
        }else {
            return id;
        }
    }
    //endregion UTILS
}
