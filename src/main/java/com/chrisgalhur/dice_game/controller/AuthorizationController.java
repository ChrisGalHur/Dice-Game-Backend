package com.chrisgalhur.dice_game.controller;


import com.chrisgalhur.dice_game.model.SessionPlayerDTO;
import com.chrisgalhur.dice_game.service.PlayerServiceImpl;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RESTFUL Controller class for handling only authentication operations.
 * This controller manages operations related to player entities:
 * - Register a new player.
 * - Login a player.
 *
 * @version 1.0
 * @since 2024-01-29
 * @author ChrisGalHur
 */
@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {

    //region INJECTIONS
    @Autowired
    private PlayerServiceImpl playerServiceimpl;
    //endregion INJECTIONS

    //region ENDPOINT REGISTER
    /**
     * Registers a new player using a POST request.
     * Validates the player information to ensure it is not null and meets specific conditions.
     *
     * @param playerDTO The player to register
     * @return ResponseEntity indicating the result of the registration operation.
     *        Possible HTTP status codes:
     *        - 201 Created: Player registered successfully.
     *        - 400 Bad Request: Invalid request body or player already exists.
     *  */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody SessionPlayerDTO playerDTO) {
        //validate if PlayerDTO is null
        if (playerDTO == null) {
            return new ResponseEntity<>("Invalid request body", HttpStatus.BAD_REQUEST);
        }

        //validate if name of user is null or empty
        if (StringUtils.isEmpty(playerDTO.getName())) {
            playerDTO.setName("UNKNOWN");
            SessionPlayerDTO playerRegistered = playerServiceimpl.registerNewUser(playerDTO);
            return new ResponseEntity<>("User registered with default name: " + playerRegistered.getName(), HttpStatus.CREATED);
        }

        //validate if user already exists
        if (playerServiceimpl.existsByName(playerDTO.getName())) {
            return new ResponseEntity<>("User by name " + playerDTO.getName() + " already exists./n" +
                    "Please select another name.", HttpStatus.BAD_REQUEST);
        }

        //save user
        SessionPlayerDTO playerRegistered = playerServiceimpl.registerNewUser(playerDTO);
        return new ResponseEntity<>("User registered with name: " + playerRegistered.getName(), HttpStatus.CREATED);
    }
    //endregion ENDPOINT REGISTER
}
