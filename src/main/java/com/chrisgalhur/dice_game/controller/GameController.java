package com.chrisgalhur.dice_game.controller;

import com.chrisgalhur.dice_game.dto.PlayerDTO;
import com.chrisgalhur.dice_game.exception.InvalidPlayerException;
import com.chrisgalhur.dice_game.response.DataPlayerResponse;
import com.chrisgalhur.dice_game.response.GameResponse;
import com.chrisgalhur.dice_game.response.PlayerResponse;
import com.chrisgalhur.dice_game.service.GameService;
import com.chrisgalhur.dice_game.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling the game endpoints.
 * This class is responsible for handling the incoming requests for the game.
 * - POST /play: Play the game.
 * - PUT /update: Update the player name.
 * - DELETE /deleteHistory: Delete the player history.
 * - GET /getHistoryPlayer: Get the player history.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
@Controller
@Slf4j // Lombok annotation to auto-generate the log field.
@RequestMapping("/game")
public class GameController {

    //region INJECTIONS
    private final GameService gameService;
    private final PlayerService playerService;

    /**
     * Constructor of the class to inject the dependencies.
     *
     * @param gameService The game service.
     * @param playerService The player service.
     */
    @Autowired
    public GameController(GameService gameService, PlayerService playerService) {
        this.gameService = gameService;
        this.playerService = playerService;
    }
    //endregion INJECTIONS

    //region PLAY
    /**
     * Method to play the game.
     * This method handles POST requests to play the game and returns the game response.
     * - Play the game.
     * - Return the game response.
     *
     * @param playerDTO The player DTO containing a player name.
     * @return ResponseEntity<GameResponse> The game response.
     * @throws InvalidPlayerException To handle the invalid player exception.
     */
    @ExceptionHandler(InvalidPlayerException.class)
    @PostMapping("/play")
    public ResponseEntity<GameResponse> play(@RequestBody PlayerDTO playerDTO) {
        log.info("endpoint /game/play called");

        try{
            GameResponse gameResponse = gameService.play(playerDTO);
            return ResponseEntity.ok(gameResponse);
        }catch (InvalidPlayerException e){
            return ResponseEntity.badRequest().body(new GameResponse(e.getMessage(), (byte) 0, (byte) 0));
        }
    }
    //endregion PLAY

    //region UPDATE NAME
    /**
     * Method to update the player name.
     * This method is responsible for:
     * - Update the player name.
     * - Return the player response.
     *
     * @param playerDTO The player DTO with only the player name.
     * @return ResponseEntity<PlayerResponse> The player response.
     * @throws InvalidPlayerException To handle the invalid player exception.
     */
    @ExceptionHandler(InvalidPlayerException.class)
    @PutMapping("/update")
    public ResponseEntity<PlayerResponse> updateName(@RequestBody PlayerDTO playerDTO) {
        log.info("endpoint /game/update called");

        try{
            PlayerResponse playerResponse = playerService.updateName(playerDTO);
            return ResponseEntity.ok(playerResponse);
        }catch (InvalidPlayerException e){
            return ResponseEntity.badRequest().body(new PlayerResponse(e.getErrorMessage()));
        }
    }
    //endregion UPDATE NAME

    //region DELETE PLAYER HISTORY
    /**
     * Method to delete the player history.
     * This method is responsible for:
     * - Delete the player history.
     *
     * @param playerDTO The player DTO with only the player name.
     * @return ResponseEntity<PlayerResponse> The player response.
     * @throws InvalidPlayerException To handle the invalid player exception.
     */
    @ExceptionHandler(InvalidPlayerException.class)
    @DeleteMapping("/deleteHistory")
    public ResponseEntity<PlayerResponse> deleteHistory(@RequestBody PlayerDTO playerDTO) {
        log.info("endpoint /game/delete called");

        try{
            PlayerResponse playerResponse = playerService.deletePlayerHistory();
            return ResponseEntity.ok(playerResponse);
        }catch (InvalidPlayerException e){
            return ResponseEntity.badRequest().body(new PlayerResponse(e.getErrorMessage()));
        }
    }
    //endregion DELETE PLAYER HISTORY

    //region GET PLAYER HISTORY
    /**
     * Method to get the player history.
     * This method is responsible for:
     * - Get the player history.
     *
     * @return ResponseEntity<DataPlayerResponse> The player history response.
     * @throws InvalidPlayerException To handle the invalid player exception.
     */
    @ExceptionHandler(InvalidPlayerException.class)
    @GetMapping("/getHistoryPlayer")
    public ResponseEntity<DataPlayerResponse> getPlayerHistory() {
        log.info("endpoint /game/history called");

        try{
            DataPlayerResponse playerResponse = playerService.getPlayerHistory();
            return ResponseEntity.ok(playerResponse);
        }catch (InvalidPlayerException e){
            return ResponseEntity.badRequest().body(new DataPlayerResponse(e.getErrorMessage(), null));
        }
    }
    //endregion GET PLAYER HISTORY
}
