package com.ravekidd.security.token;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Custom implementation of Spring Security's AuthenticationEntryPoint.
 * Handles unauthorized requests and sends an HTTP 401 Unauthorized error response.
 */
@Component
public class JWTAuthEntryPoint implements AuthenticationEntryPoint {

    /**
     * Invoked when an unauthenticated user tries to access a secured resource.
     *
     * @param request       The HttpServletRequest.
     * @param response      The HttpServletResponse.
     * @param exception     The AuthenticationException.
     * @throws IOException      If an input or output exception occurs.
     * @throws ServletException If a servlet-related exception occurs.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
    }
}
