package com.chrisgalhur.dice_game.security;

import com.chrisgalhur.dice_game.service.CustomUserDetailsService;
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
 * Configuration class for Spring Security to handle authentication and authorization with JWT (JSON Web Tokens) to enter the API.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
@Service
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //region DEPENDENCY INJECTION
    private final JWTAuthEntryPoint jwtAuthEntryPoint;

    /**
     * Dependency injection of the custom user details service.
     *
     * @param jwtAuthEntryPoint JWT authentication entry point.
     */
    @Autowired
    public SecurityConfig(JWTAuthEntryPoint jwtAuthEntryPoint){
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
    }
    //endregion DEPENDENCY INJECTION

    //region FILTER CHAIN
    /**
     * Configures the security filter chain to handle authentication and authorize http to enter the API.
     * Security filter is created:
     * <ul>
     *     <li>Disables CSRF (Cross-Site Request Forgery) protection.</li>
     *     <li>Handles authentication entry point exception related to JWT authentication.</li>
     *     <li>Authorizes HTTP requests to enter the API.</li>
     *     <li>Adds the JWT authentication filter before the username and password authentication filter.</li>
     *     <li>Enables HTTP basic authentication with default settings.</li>
     * </ul>
     *
     * The authentication is managed by the {@link JWTAuthenticationFilter}.
     * The authentication entry point is provided by the {@link JWTAuthEntryPoint}.
     * The authentication manager is implemented in {@link CustomUserDetailsService}.
     * The password encoder is provided by {@link BCryptPasswordEncoder}.
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
                        .requestMatchers(SecurityConstants.AUTHORIZED_URL + "/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
    //endregion FILTER CHAIN

    //region AUTHENTICATION MANAGER BEAN
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
    //endregion AUTHENTICATION MANAGER BEAN

    //region PASSWORD ENCODER BEAN
    /**
     * Creates a password encoder provided by Spring Security in this case.
     *
     * @return Password encoder.
     */
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //endregion PASSWORD ENCODER BEAN

    //region JWT AUTHENTICATION FILTER BEAN
    /**
     * Creates a JWT authentication filter.
     *
     * @return JWT authentication filter.
     */
    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(){
        return new JWTAuthenticationFilter();
    }
    //endregion JWT AUTHENTICATION FILTER BEAN
}
