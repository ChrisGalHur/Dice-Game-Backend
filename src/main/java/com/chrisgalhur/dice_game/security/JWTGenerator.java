package com.chrisgalhur.dice_game.security;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.UUID;

/**
 * Handles JWT generation and validation.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
@Component
public class JWTGenerator {

    //region GENERATE TOKEN
    /**
     * Generates a JWT token from an authentication object.
     * The generated JWT token includes the subject (username), issue date, and expiration date.
     *
     * @param idPlayer ID of the player.
     * @return JWT token.
     * @throws AuthenticationCredentialsNotFoundException If the authentication object is invalid.
     */
    public String generateToken(UUID idPlayer) throws AuthenticationCredentialsNotFoundException{
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);

        String token;
        try{
            token = Jwts.builder()
                    .setSubject(idPlayer.toString())
                    .setIssuedAt(new Date())
                    .setExpiration(expirationDate)
                    .signWith(SecurityConstants.SECRET_KEY, SignatureAlgorithm.HS256)
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
                .setSigningKey(SecurityConstants.SECRET_KEY)
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
            Jwts.parser().setSigningKey(SecurityConstants.SECRET_KEY).parseClaimsJws(token).getBody();
            return true;
        }catch (Exception ex){
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect.");
        }
    }
    //endregion VALIDATE TOKEN
}
