package com.chrisgalhur.dice_game.controller;

import com.chrisgalhur.dice_game.exception.InvalidCredentialsException;
import com.chrisgalhur.dice_game.response.AuthResponse;
import com.chrisgalhur.dice_game.dto.SessionPlayerDTO;
import com.chrisgalhur.dice_game.service.AuthorizationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * RESTFUL Controller class for handling only authentication operations.
 * This controller manages operations related to player entities:
 * - Register a new player.
 * - Login a player.
 *
 * @version 1.0
 * @author ChrisGalHur
 */

@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {

    //region INJECTIONS and ATTRIBUTES
    private final AuthorizationServiceImpl authorizationService;
    private static final Logger log = Logger.getLogger(AuthorizationController.class.getName());

    /**
     * Constructor of the class.0
     *
     * @param authorizationService Authorization service implementation.
     *  */
    @Autowired
    public AuthorizationController(AuthorizationServiceImpl authorizationService) {
        this.authorizationService = authorizationService;
    }
    //endregion INJECTIONS

    //region REGISTER
    /**
     * Registers a new player using a POST request.
     * Validates the player information to ensure it is not null and meets specific conditions.
     *
     * @param sessionPlayerDTO The player to register
     * @return ResponseEntity indicating the result of the registration operation.
     *        Possible HTTP status codes:
     *        - 201 Created: Player registered successfully.
     *        - 400 Bad Request: Invalid request body or player already exists.
     *  */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody SessionPlayerDTO sessionPlayerDTO) {
        log.info("endpoint /api/auth/register called");

        try{
            AuthResponse response = authorizationService.authenticateRegisterPlayer(sessionPlayerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (InvalidCredentialsException e){
            return ResponseEntity.badRequest().body(new AuthResponse(null, e.getMessage()));
        }
    }
    //endregion REGISTER

    //region LOGIN
    /**
     * Logs in a player using a POST request.
     * Validates the player information to ensure it is not null and meets specific conditions.
     *
     * @param sessionPlayerDTO The player to login
     * @return ResponseEntity indicating the result of the login operation.
     *        Possible HTTP status codes:
     *        - 200 OK: Player logged in successfully.
     *        - 400 Bad Request: Invalid request body or player does not exist.
     *  */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody SessionPlayerDTO sessionPlayerDTO) {
        log.info("endpoint /api/auth/login called");

        try{
            AuthResponse response = authorizationService.authenticateLoginPlayer(sessionPlayerDTO);
            return ResponseEntity.ok().body(response);
        }catch (InvalidCredentialsException e){
            return ResponseEntity.badRequest().body(new AuthResponse(null, e.getMessage()));
        }
    }
    //endregion LOGIN
}
