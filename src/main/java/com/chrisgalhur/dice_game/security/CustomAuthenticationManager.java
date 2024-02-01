package com.chrisgalhur.dice_game.security;

import com.chrisgalhur.dice_game.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * CustomAuthenticationManager is responsible for authenticating users by username and password.
 * It implements the AuthenticationManager interface and is used to authenticate user credentials.
 * The authentication process involves validating the provided username and password against stored credentials.
 *
 * <p>
 * Responsibilities:
 * - Authenticate users based on the provided username and password.
 * - Utilizes the CustomUserDetailsService to load user details.
 * - Compares the provided password with the stored hashed password using the configured password encoder.
 * </p>
 *
 * @version 1.0
 * @since 2024-01-30
 * @author ChrisGalHur
 */
@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    private PlayerServiceImpl playerServiceImpl;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Authenticates the user by username and password and returns an authentication token if the authentication is successful or throws an exception if the authentication fails.
     *
     * @param authentication Authentication object containing the username and password.
     * @return Authentication token if the authentication is successful.
     * @throws AuthenticationException If the authentication fails.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final UserDetails userDetail = customUserDetailsService.loadUserByUsername(authentication.getName());
        if (!securityConfig.passwordEncoder().matches(authentication.getCredentials().toString(), userDetail.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }
        return new UsernamePasswordAuthenticationToken(userDetail.getUsername(), userDetail.getPassword(), userDetail.getAuthorities());
    }
}
