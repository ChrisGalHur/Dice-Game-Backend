package com.chrisgalhur.dice_game.security;

import com.chrisgalhur.dice_game.model.Role;
import com.chrisgalhur.dice_game.model.SessionPlayer;
import com.chrisgalhur.dice_game.repository.SessionPlayerRepository;
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
 * @since 2024-01-30
 * @author ChrisGalHur
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private SessionPlayerRepository sessionPlayerRepository;

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
        SessionPlayer player = sessionPlayerRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return new User(player.getName(), player.getPassword(), mapRolesToAuthorities(player.getRoles()));
    }

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
}
