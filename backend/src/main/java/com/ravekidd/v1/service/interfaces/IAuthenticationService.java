package com.ravekidd.v1.service.interfaces;

import com.ravekidd.v1.model.auth.AuthenticationResponse;
import com.ravekidd.v1.model.auth.AuthenticationRequest;
import com.ravekidd.v1.model.auth.RegisterRequest;
import com.ravekidd.v1.model.auth.RegisterResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

/**
 * Service interface for managing authentication-related operations.
 */
public interface IAuthenticationService {

    /**
     * Registers a user with the given register request.
     *
     * @param request Register request containing user information.
     * @return ResponseEntity containing the registration result.
     */
    ResponseEntity<RegisterResponse> register(RegisterRequest request);

    /**
     * Logs in the user with the provided login request.
     *
     * @param request Login request containing the user's credentials.
     * @return Authentication response containing a bearer token.
     */
    ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request);

    /**
     * Validates a token.
     *
     * @param request HttpServletRequest object containing the token to be validated.
     * @return ResponseEntity containing the result of the token validation.
     */
    ResponseEntity<AuthenticationResponse> validateToken(HttpServletRequest request);
}
