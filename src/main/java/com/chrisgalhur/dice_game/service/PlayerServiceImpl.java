package com.chrisgalhur.dice_game.service;

import com.chrisgalhur.dice_game.model.Role;
import com.chrisgalhur.dice_game.model.SessionPlayer;
import com.chrisgalhur.dice_game.model.SessionPlayerDTO;
import com.chrisgalhur.dice_game.repository.PlayerRepository;
import com.chrisgalhur.dice_game.repository.SessionPlayerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

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
public class PlayerServiceImpl implements PlayerService{

    //region DEPENDENCY INJECTION
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final SessionPlayerRepository sessionPlayerRepository;
    private final RoleServiceImpl roleService;
    private final PlayerRepository playerRepository;
    //endregion DEPENDENCY INJECTION

    //region CONSTRUCTOR
    /**
     * Constructor of the class.
     *
     * @param modelMapper ModelMapper object.
     * @param passwordEncoder PasswordEncoder object.
     * @param sessionPlayerRepository SessionPlayerRepository object.
     * @param roleService RoleService object.
     * @param playerRepository PlayerRepository object.
     */
    @Autowired
    public PlayerServiceImpl(ModelMapper modelMapper, PasswordEncoder passwordEncoder, SessionPlayerRepository sessionPlayerRepository, RoleServiceImpl roleService, PlayerRepository playerRepository) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.sessionPlayerRepository = sessionPlayerRepository;
        this.roleService = roleService;
        this.playerRepository = playerRepository;
    }
    //endregion CONSTRUCTOR

    //region METHODS EXIST: BYNAME
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
    //endregion METHODS EXIST: BYNAME

    //region METHODS: REGISTER
    /**
     * Registers a new player in the database.
     * Change the class of the player to PlayerEntity.
     *
     * @param playerDTO Player to register.
     * @return SessionPlayerDTO Player registered.
     */
    @Override
    public SessionPlayerDTO registerNewUser(SessionPlayerDTO playerDTO) {
        //Manage sessionPlayer
        SessionPlayer sessionPlayer = modelMapper.map(playerDTO, SessionPlayer.class);
        sessionPlayer.setPassword(passwordEncoder.encode(playerDTO.getPassword()));

        //Manage player
        Player player = modelMapper.map(playerDTO, Player.class);
        Role roleUser = roleService.findByName("USER").get();
        player.setRole(Collections.singletonList(roleUser));
        playerRepository.save(player);


        SessionPlayer playerRegistered = sessionPlayerRepository.save(sessionPlayer);
        return modelMapper.map(playerRegistered, SessionPlayerDTO.class);
    }
    //endregion METHODS: REGISTER
}
