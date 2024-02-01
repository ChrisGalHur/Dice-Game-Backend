package com.chrisgalhur.dice_game.controller;

import com.chrisgalhur.dice_game.model.AuthResponseDTO;
import com.chrisgalhur.dice_game.model.SessionPlayerDTO;
import com.chrisgalhur.dice_game.security.CustomAuthenticationManager;
import com.chrisgalhur.dice_game.security.JWTGenerator;
import com.chrisgalhur.dice_game.service.PlayerServiceImpl;
import io.micrometer.common.util.StringUtils;
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
    private final PlayerServiceImpl playerServiceimpl;
    private final CustomAuthenticationManager customAuthenticationManager;
    private final JWTGenerator jwtGenerator;

    /**
     * Constructor of the class.
     *
     * @param playerServiceimpl Player service implementation.
     * @param customAuthenticationManager Custom authentication manager.
     * @param jwtGenerator JWT generator.
     */
    @Autowired
    public AuthorizationController(PlayerServiceImpl playerServiceimpl, CustomAuthenticationManager customAuthenticationManager, JWTGenerator jwtGenerator) {
        this.playerServiceimpl = playerServiceimpl;
        this.customAuthenticationManager = customAuthenticationManager;
        this.jwtGenerator = jwtGenerator;
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

        //validate if PlayerDTO is null
        if (sessionPlayerDTO == null) {
            return ResponseEntity.badRequest().body(new AuthResponseDTO(null, "Invalid request body"));
        }

        //validate if name of user is null or empty
        if (StringUtils.isEmpty(sessionPlayerDTO.getName())) {
            sessionPlayerDTO.setName("UNKNOWN");
            SessionPlayerDTO playerRegistered = playerServiceimpl.registerNewUser(sessionPlayerDTO);

            Authentication authentication = customAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(playerRegistered.getName(), playerRegistered.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponseDTO(token, "User registered with default name: " + playerRegistered.getName()));
        }

        //validate if user already exists
        if (playerServiceimpl.existsByName(sessionPlayerDTO.getName())) {
            return ResponseEntity.badRequest().body(new AuthResponseDTO(null, "User by name " + sessionPlayerDTO.getName() + " already exists.\n" +
                    "Please select another name."));
        }

        //save user and return response with token
        SessionPlayerDTO playerRegistered = playerServiceimpl.registerNewUser(sessionPlayerDTO);

        Authentication authentication = customAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(playerRegistered.getName(), playerRegistered.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return ResponseEntity.badRequest().body(new AuthResponseDTO(token, "User registered with name: " + playerRegistered.getName()));
    }
    //endregion REGISTER
}
