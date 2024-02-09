package com.chrisgalhur.dice_game.service;

import com.chrisgalhur.dice_game.entity.Player;
import com.chrisgalhur.dice_game.entity.Role;
import com.chrisgalhur.dice_game.entity.SessionPlayer;
import com.chrisgalhur.dice_game.dto.SessionPlayerDTO;
import com.chrisgalhur.dice_game.exception.InvalidCredentialsException;
import com.chrisgalhur.dice_game.repository.PlayerRepository;
import com.chrisgalhur.dice_game.repository.SessionPlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

/**
 * Class to manage the player service.
 * Implements SessionPlayerService interface.
 *
 * @see SessionPlayerService
 *
 * @version 1.0
 * @author ChrisGalHur
 */

@Service
public class SessionPlayerServiceImpl implements SessionPlayerService {

    //region DEPENDENCY INJECTION
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final SessionPlayerRepository sessionPlayerRepository;
    private final PlayerRepository playerRepository;

    /**
     * Implements classes that are required for the player service.
     *
     * @param modelMapper             ModelMapper to map objects from one class to another.
     * @param passwordEncoder         Password encoder to encode the password of the player.
     * @param sessionPlayerRepository SessionPlayer repository to access session player data from the database.
     * @param playerRepository        Player repository to access player data from the database.
     */
    @Autowired
    public SessionPlayerServiceImpl(ModelMapper modelMapper, PasswordEncoder passwordEncoder, SessionPlayerRepository sessionPlayerRepository, PlayerRepository playerRepository) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.sessionPlayerRepository = sessionPlayerRepository;
        this.playerRepository = playerRepository;
    }
    //endregion DEPENDENCY INJECTION

    //region REGISTER NEW USER

    /**
     * Registers a new player in the database.
     * Change the class of the sessionPlayer to SessionPlayerEntity.
     * Encrypt the password of the sessionPlayer.
     * Transform the sessionPlayerDTO to a PlayerEntity, set a default role for the player, and save it and the sessionPlayer in the database.
     * Returns the sessionPlayerDTO has saved in the database.
     *
     * @see SessionPlayer The class to manage the session player.
     * @see Player The class to manage the player.
     * @param sessionPlayerDTO Player to register.
     * @return SessionPlayerDTO Player registered.
     */
    @Override
    public SessionPlayerDTO registerNewUser(SessionPlayerDTO sessionPlayerDTO){
        try {
            //Manage sessionPlayer
            SessionPlayer sessionPlayer = modelMapper.map(sessionPlayerDTO, SessionPlayer.class);
            sessionPlayer.setPassword(passwordEncoder.encode(sessionPlayer.getPassword()));
            sessionPlayer.setId(UUID.randomUUID());

            //Manage player
            Player player = modelMapper.map(sessionPlayerDTO, Player.class);
            player.setId(sessionPlayer.getId());
            designRoles(player);
            playerRepository.save(player);


            SessionPlayer playerRegistered = sessionPlayerRepository.save(sessionPlayer);
            return modelMapper.map(playerRegistered, SessionPlayerDTO.class);
        }catch (Exception e){
            throw new InvalidCredentialsException("Error: The player could not be registered.");
        }
    }

    private void designRoles(Player playerToDesign) {
        playerToDesign.setRoles(Collections.singletonList(new Role("USER")));
        //todo: other roles can be added here
    }
    //endregion REGISTER NEW USER

    //region LOGIN USER
    /**
     * Method to log in a player.
     * Change the class of the player to PlayerEntity.
     * Search the player by name in the database.
     * Check if the password matches the password in the database.
     * If the player exists and the password matches, return the player information.
     * If the player does not exist or the password does not match, return null.
     *
     * @param sessionPlayerDTO SessionPlayerDTO with the player information.
     * @return AuthResponseDTO with the authentication response.
     */
    @Override
    public SessionPlayerDTO loginUser(SessionPlayerDTO sessionPlayerDTO) {
        SessionPlayer loggedSessionPlayer = sessionPlayerRepository.findByName(sessionPlayerDTO.getName()).orElse(null);

        if (loggedSessionPlayer != null && passwordEncoder.matches(sessionPlayerDTO.getPassword(), loggedSessionPlayer.getPassword())) {
            return modelMapper.map(loggedSessionPlayer, SessionPlayerDTO.class);
        }
        return null;
    }

    //region EXIST BY NAME

    /**
     * Verifies if the player exists by name in the database.
     *
     * @param name The name of the player.
     * @return True if the player exists in the database, false if not.
     */
    @Override
    public boolean existsByName(String name) {
        return sessionPlayerRepository.existsByName(name);
    }
    //endregion METHODS EXIST BY NAME
}














