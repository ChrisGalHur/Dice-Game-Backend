package com.chrisgalhur.dice_game.service;

import com.chrisgalhur.dice_game.model.SessionPlayer;
import com.chrisgalhur.dice_game.model.SessionPlayerDTO;
import com.chrisgalhur.dice_game.repository.SessionPlayerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Class to manage the player service.
 * Implements PlayerService interface.
 *
 * @see PlayerService
 * @version 1.0
 * @since 2024-01-29
 * @author ChrisGalHur
 */
@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService{

    //region DEPENDENCY INJECTION
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final SessionPlayerRepository sessionPlayerRepository;
    //endregion DEPENDENCY INJECTION

    //region METHOD VERIFICATION
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
    //endregion METHOD VERIFICATION

    //region METHODS: SAVE, UPDATE / PLAYER
    /**
     * Registers a new player in the database.
     * Change the class of the player to PlayerEntity.
     *
     * @param playerDTO Player to register.
     * @return SessionPlayerDTO Player registered.
     */
    @Override
    public SessionPlayerDTO registerNewUser(SessionPlayerDTO playerDTO) {
        SessionPlayer player = modelMapper.map(playerDTO, SessionPlayer.class);
        player.setPassword(passwordEncoder.encode(playerDTO.getPassword()));

        //TODO: Add role to player

        SessionPlayer playerRegistered = sessionPlayerRepository.save(player);
        return modelMapper.map(playerRegistered, SessionPlayerDTO.class);
    }
}
