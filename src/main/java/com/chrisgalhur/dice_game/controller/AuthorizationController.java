package com.chrisgalhur.dice_game.controller;

import com.chrisgalhur.dice_game.exception.InvalidCredentialsException;
import com.chrisgalhur.dice_game.model.AuthResponseDTO;
import com.chrisgalhur.dice_game.model.SessionPlayerDTO;
import com.chrisgalhur.dice_game.security.CustomAuthenticationManager;
import com.chrisgalhur.dice_game.security.JWTGenerator;
import com.chrisgalhur.dice_game.service.AuthorizationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
 * @author ChrisGalHur
 */

@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {

    //region INJECTIONS
    private final CustomAuthenticationManager customAuthenticationManager;
    private final JWTGenerator jwtGenerator;
    private final AuthorizationServiceImpl authorizationService;

    /**
     * Constructor of the class.0
     *
     * @param customAuthenticationManager Custom authentication manager.
     * @param jwtGenerator JWT generator.
     * @param authorizationService Authorization service implementation.
     *  */
    @Autowired
    public AuthorizationController(CustomAuthenticationManager customAuthenticationManager, JWTGenerator jwtGenerator, AuthorizationServiceImpl authorizationService) {
        this.customAuthenticationManager = customAuthenticationManager;
        this.jwtGenerator = jwtGenerator;
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
    public ResponseEntity<AuthResponseDTO> register(@RequestBody SessionPlayerDTO sessionPlayerDTO) {
        try{
            AuthResponseDTO response = authorizationService.authenticateRegisterPlayer(sessionPlayerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (InvalidCredentialsException e){
            return ResponseEntity.badRequest().body(new AuthResponseDTO(null, e.getMessage()));
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
    public ResponseEntity<AuthResponseDTO> login(@RequestBody SessionPlayerDTO sessionPlayerDTO) {
        try{
            AuthResponseDTO response = authorizationService.authenticateLoginPlayer(sessionPlayerDTO);
            return ResponseEntity.ok().body(response);
        }catch (InvalidCredentialsException e){
            return ResponseEntity.badRequest().body(new AuthResponseDTO(null, e.getMessage()));
        }
    }
    //endregion LOGIN
}
