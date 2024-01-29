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
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private SessionPlayerRepository sessionPlayerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SessionPlayer player = sessionPlayerRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return new User(player.getName(), player.getPassword(), mapRolesToAuthorities(player.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }
}
