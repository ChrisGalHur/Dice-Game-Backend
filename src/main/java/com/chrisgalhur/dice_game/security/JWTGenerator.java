package com.chrisgalhur.dice_game.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Handles JWT generation and validation.
 *
 * @version 1.0
 * @author ChrisGalHur
 */

@Component
public class JWTGenerator {

    //region ATTRIBUTES
    private final Key key;
    //endregion ATTRIBUTES

    //region CONSTRUCTOR
    public JWTGenerator(){
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }
    //endregion CONSTRUCTOR

    //region GENERATE TOKEN
    /**
     * Generates a JWT token from an authentication object.
     * The generated JWT token includes the subject (username), issue date, and expiration date.
     *
     * @param authentication Authentication object.
     * @return JWT token.
     * @throws AuthenticationCredentialsNotFoundException If the authentication object is invalid.
     */
    public String generateToken(Authentication authentication) throws AuthenticationCredentialsNotFoundException{
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);


        String token;
        try{
            token = Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(expirationDate)
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
        }catch (ExpiredJwtException | MalformedJwtException ex){
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect.");
        }
        
        return token;
    }
    //endregion GENERATE TOKEN

    //region GET USERNAME FROM JWT
    /**
     * Extracts the username from a JWT token.
     *
     * @param token JWT token.
     * @return Username from the JWT token.
     * @throws AuthenticationCredentialsNotFoundException If the JWT token is invalid.
     */
    public String getUserNameFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    //endregion GET USERNAME FROM JWT

    //region VALIDATE TOKEN
    /**
     * Validates a JWT token.
     *
     * @param token JWT token.
     * @return True if the JWT token is valid, false otherwise.
     * @throws AuthenticationCredentialsNotFoundException If the JWT token is invalid.
     */
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            return true;
        }catch (Exception ex){
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect.");
        }
    }
    //endregion VALIDATE TOKEN
}
