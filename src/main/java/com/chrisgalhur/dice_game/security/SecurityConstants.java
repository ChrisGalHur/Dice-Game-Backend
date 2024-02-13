package com.chrisgalhur.dice_game.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

/**
 * Stores security constants.
 *
 * @version 1.0
 * @author ChrisGalHur
 */

public class SecurityConstants {

    //region CONSTANTS
    /**
     * JWT expiration time in milliseconds (1 hour = 3600000 milliseconds).
     * It's a convenient value for playing game and expire.
     */
    public static final long JWT_EXPIRATION = 3600000;

    /**
     * Autorized URL.
     */
    public static final String AUTHORIZED_URL = "/api/auth";
    //endregion CONSTANTS

    /**
     * Secret key for JWT generation and validation.
     *
     * @see Keys#secretKeyFor(SignatureAlgorithm)
     */
    public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
}
