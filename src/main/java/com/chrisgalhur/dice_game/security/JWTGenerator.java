package com.chrisgalhur.dice_game.security;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * Handles JWT generation and validation.
 *
 * @version 1.0
 * @since 2021-01-30
 * @author ChrisGalHur
 */
@Component
public class JWTGenerator {

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
                    .signWith(SignatureAlgorithm.HS256, SecurityConstants.JWT_SECRET)
                    .compact();
        }catch (ExpiredJwtException | MalformedJwtException ex){
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect.");
        }
        
        return token;
    }

    /**
     * Extracts the username from a JWT token.
     *
     * @param token JWT token.
     * @return Username from the JWT token.
     * @throws AuthenticationCredentialsNotFoundException If the JWT token is invalid.
     */
    public String getUserNameFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    /**
     * Validates a JWT token.
     *
     * @param token JWT token.
     * @return True if the JWT token is valid, false otherwise.
     * @throws AuthenticationCredentialsNotFoundException If the JWT token is invalid.
     */
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token).getBody();
            return true;
        }catch (Exception ex){
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect.");
        }
    }
}
