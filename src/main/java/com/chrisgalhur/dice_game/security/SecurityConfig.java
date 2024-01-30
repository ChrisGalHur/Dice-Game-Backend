package com.chrisgalhur.dice_game.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

/**
 * Configuration class for Spring Security.
 *
 * @version 1.0
 * @since 2024-01-30
 * @author ChrisGalHur
 */
@Service
@Configuration
@EnableWebSecurity
public class SecurityConfig{
    @Autowired
    private JWTAuthEntryPoint jwtAuthEntryPoint;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    /**
     * Configures the security filter chain to handle authentication and authorize http to enter the API.
     * The authentication is managed by the {@link JWTAuthenticationFilter}.
     * The authentication entry point is provided by the {@link JWTAuthEntryPoint}.
     * The authentication manager is implemented in {@link CustomUserDetailsService}.
     * The password encoder is provided by {@link BCryptPasswordEncoder}.
     *
     *
     * @param http HTTP security.
     * @return Security filter chain.
     * @throws Exception If an exception occurs during the configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(jwtAuthEntryPoint))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * Creates an authentication manager responsible for authentication.
     *
     * @param authenticationConfiguration Authentication configuration.
     * @return Authentication manager.
     * @throws Exception If an exception occurs during the creation of the authentication manager.
     */
    @Bean
    @Primary
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Creates a password encoder provided by Spring Security in this case.
     *
     * @return Password encoder.
     */
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates a JWT authentication filter.
     *
     * @return JWT authentication filter.
     */
    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(){
        return new JWTAuthenticationFilter();
    }
}
