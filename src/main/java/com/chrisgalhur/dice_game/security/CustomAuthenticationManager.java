package com.chrisgalhur.dice_game.security;

import com.chrisgalhur.dice_game.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

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
 * @author ChrisGalHur
 */

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    //region DEPENDENCY INJECTION
    private final SecurityConfig securityConfig;

    private final CustomUserDetailsService customUserDetailsService;

    /**
     * Implements classes that are required for the authentication process.
     *
     * @param securityConfig Security configuration for managing the authentication process.
     * @param customUserDetailsService Custom user details service for retrieving user details during the authentication process.
     */
    @Autowired
    public CustomAuthenticationManager(SecurityConfig securityConfig, CustomUserDetailsService customUserDetailsService) {
        this.securityConfig = securityConfig;
        this.customUserDetailsService = customUserDetailsService;
    }
    //endregion DEPENDENCY INJECTION

    //region AUTHENTICATE
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

        if (userDetail == null) {
            throw new BadCredentialsException("Username not found");
        }

        //todo : pruebas
        String enteredPass = (String) authentication.getCredentials();
        Logger.getLogger("CustomAuthenticationManager").info("Entered pass: " + enteredPass);
        Logger.getLogger("CustomAuthenticationManager").info("Stored pass: " + userDetail.getPassword());

        if (!securityConfig.passwordEncoder().matches(enteredPass, userDetail.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }

        return new UsernamePasswordAuthenticationToken(userDetail.getUsername(), userDetail.getPassword(), userDetail.getAuthorities());
    }
    //endregion AUTHENTICATE
}
