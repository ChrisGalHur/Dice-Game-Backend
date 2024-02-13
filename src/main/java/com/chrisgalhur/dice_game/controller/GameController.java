package com.chrisgalhur.dice_game.controller;

import com.chrisgalhur.dice_game.dto.PlayerDTO;
import com.chrisgalhur.dice_game.exception.InvalidPlayerException;
import com.chrisgalhur.dice_game.response.GameResponse;
import com.chrisgalhur.dice_game.response.PlayerResponse;
import com.chrisgalhur.dice_game.service.GameService;
import com.chrisgalhur.dice_game.service.PlayerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

/**
 * Controller class for handling the game endpoints.
 * This class is responsible for handling the incoming requests for the game.
 * - Play: Make a dice roll.
 *
 * @version 1.0
 * @author ChrisGalHur
 */

@Controller
@RequestMapping("/game")
public class GameController {

    //region INJECTIONS
    private final GameService gameService;
    private final PlayerService playerService;
    private static final Logger log = Logger.getLogger(GameController.class.getName());

    /**
     * Constructor of the class to inject the dependencies.
     *
     * @param gameService The game service.
     */
    @Autowired
    public GameController(GameService gameService, PlayerService playerService) {
        this.gameService = gameService;
        this.playerService = playerService;
    }
    //endregion INJECTIONS

    //region PLAY
    @PostMapping("/play")
    public ResponseEntity<GameResponse> play(@RequestBody PlayerDTO playerDTO, HttpServletRequest request) {

        try{
            GameResponse gameResponse = gameService.play(playerDTO);
            return ResponseEntity.ok(gameResponse);
        }catch (InvalidPlayerException e){
            return ResponseEntity.badRequest().body(new GameResponse(e.getMessage(), (byte) 0, (byte) 0));
        }
    }
    //endregion PLAY

    //region UPDATE NAME
    @PutMapping("/update")
    public ResponseEntity<PlayerResponse> updateName(@RequestBody PlayerDTO playerDTO, HttpServletRequest request) {
        Logger.getLogger(GameController.class.getName()).info("endpoint /game/update called");

        try{
            PlayerResponse playerResponse = playerService.updateName(playerDTO);
            return ResponseEntity.ok(playerResponse);
        }catch (InvalidPlayerException e){
            return ResponseEntity.badRequest().body(new PlayerResponse("Something went wrong"));
        }
    }
}
