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
     * Authorized URL for JWT authentication.
     */
    public static final String AUTHORIZED_URL = "/api/auth";

    /**
     * Secret key for JWT generation and validation.
     *
     * @see Keys#secretKeyFor(SignatureAlgorithm)
     */
    public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    //endregion CONSTANTS

    //region PRIVATE CONSTRUCTOR
    /**
     * Private constructor to prevent instantiation of this class.
     */
    private SecurityConstants() {
        throw new IllegalStateException("No instance allowed for this class.");
    }
}
