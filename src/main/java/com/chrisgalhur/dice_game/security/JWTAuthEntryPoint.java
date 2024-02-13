package com.chrisgalhur.dice_game.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.SignatureException;
import java.util.UUID;

/**
 * Handles authentication entry point exception related to JWT authentication.
 *
 * @version 1.0
 * @author ChrisGalHur
 */

@Component
public class JWTAuthEntryPoint implements AuthenticationEntryPoint {

    //region COMMENCE
    /**
     * Commences an authentication entry point by sending an authentication error response.
     *
     * @param request HTTP request.
     * @param response HTTP response.
     * @param authException Authentication exception.
     * @throws IOException If I/O error occurs while sending the response.
     * @throws ServletException If an exception occurs during the process of the request.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
    //endregion COMMENCE
}
