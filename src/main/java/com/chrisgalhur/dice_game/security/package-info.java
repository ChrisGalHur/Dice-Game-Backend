/**
 * Manages security for the application using JWT (JSON Web Tokens).
 * It includes classes for configuring security, handling authentication, and generating JWT tokens.
 *
 * <ul>
 *     <li>The classes in this package are:
 *         <ul>
 *             <li>{@link com.chrisgalhur.dice_game.security.CustomAuthenticationManager}: Responsible for the authentication process of users.</li>
 *             <li>{@link com.chrisgalhur.dice_game.security.JWTAuthenticationFilter}: Manages the JWT authentication process.</li>
 *             <li>{@link com.chrisgalhur.dice_game.security.JWTAuthEntryPoint}: Manages the authentication entry point of the API.</li>
 *             <li>{@link com.chrisgalhur.dice_game.security.JWTGenerator}: Generates the JWT token.</li>
 *             <li>{@link com.chrisgalhur.dice_game.security.SecurityConfig}: Configures the security settings.</li>
 *             <li>{@link com.chrisgalhur.dice_game.security.SecurityConstants}: Contains constants related to security.</li>
 *         </ul>
 *     </li>
 * </ul>
 *
 * @version 1.0
 * @author ChrisGalHur
 */

package com.chrisgalhur.dice_game.security;