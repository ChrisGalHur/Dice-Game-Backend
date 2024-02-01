package com.chrisgalhur.dice_game.security;

/**
 * Stores security constants.
 *
 * @version 1.0
 * @since 2021-01-30
 * @author ChrisGalHur
 */
public class SecurityConstants {

    /**
     * JWT expiration time in milliseconds (1 hour = 3600000 milliseconds).
     * It's a convenient value for playing game and expire.
     */
    public static final long JWT_EXPIRATION = 3600000;

    /**
     * Autorized URL.
     */
    public static final String AUTHORIZED_URL = "/api/auth";

}
