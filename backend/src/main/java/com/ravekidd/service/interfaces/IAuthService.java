package com.ravekidd.service.interfaces;

import com.ravekidd.model.auth.AuthResponse;
import com.ravekidd.model.auth.AuthRequest;
import com.ravekidd.model.auth.RegisterRequest;
import com.ravekidd.model.auth.RegisterResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

/**
 * Service interface for managing authentication-related operations.
 */
public interface IAuthService {

    /**
     * Registers a user with the given register request.
     *
     * @param registerRequest Register request containing user information.
     * @return ResponseEntity containing the registration result.
     */
    ResponseEntity<RegisterResponse> register(RegisterRequest registerRequest);

    /**
     * Logs in the user with the provided login request.
     *
     * @param authRequest Login request containing the user's credentials.
     * @return Authentication response containing a bearer token.
     */
    ResponseEntity<AuthResponse> login(AuthRequest authRequest);

    /**
     * Validates a token.
     *
     * @param request HttpServletRequest object containing the token to be validated.
     * @return ResponseEntity containing the result of the token validation.
     */
    ResponseEntity<AuthResponse> validateToken(HttpServletRequest request);
}
