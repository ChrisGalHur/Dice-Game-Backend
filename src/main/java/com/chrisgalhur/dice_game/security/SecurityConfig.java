package com.chrisgalhur.dice_game.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Configuration class for Spring Security.
 *
 * @version 1.0
 * @since 2024-01-29
 * @author ChrisGalHur
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JWTAuthEntryPoint jwtAuthEntryPoint;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

}
