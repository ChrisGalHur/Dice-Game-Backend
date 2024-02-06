package com.chrisgalhur.dice_game.security;

import com.chrisgalhur.dice_game.model.Role;
import com.chrisgalhur.dice_game.model.SessionPlayer;
import com.chrisgalhur.dice_game.repository.PlayerRepository;
import com.chrisgalhur.dice_game.repository.SessionPlayerRepository;
import com.chrisgalhur.dice_game.service.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Custom implementation of UserDetailsService of Spring Security.
 * This service is responsible for loading the user details when during the authentication process.
 *
 * @version 1.0
 * @author ChrisGalHur
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    //region DEPENDENCY INJECTION
    private final PlayerRepository playerRepository;

    private final SessionPlayerRepository sessionPlayerRepository;

    /**
     * Injects the player repository and the session player repository to access player data from the database.
     *
     * @param playerRepository Player repository to access player data from the database.
     * @param sessionPlayerRepository Session player repository to access session player data from the database.
     */
    @Autowired
    public CustomUserDetailsService(PlayerRepository playerRepository, SessionPlayerRepository sessionPlayerRepository) {
        this.playerRepository = playerRepository;
        this.sessionPlayerRepository = sessionPlayerRepository;
    }
    //endregion DEPENDENCY INJECTION

    //region LOAD USER BY USERNAME
    /**
     * Load user details by username.
     * This method is called during the authentication process.
     *
     * @param username Username of the player.
     * @return User details containing payer information.
     * @throws UsernameNotFoundException If the username is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Player player = playerRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        SessionPlayer sessionPlayer = sessionPlayerRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return new User(player.getName(), sessionPlayer.getPassword(), mapRolesToAuthorities(player.getRoles()));
    }
    //endregion LOAD USER BY USERNAME

    //region MAP ROLES TO AUTHORITIES
    /**
     * Map the roles of the player to Spring Security authorities.
     *
     * @param roles List of roles assigned to the player.
     * @return Collection of authorities.
     */
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }
    //endregion MAP ROLES TO AUTHORITIES
}
