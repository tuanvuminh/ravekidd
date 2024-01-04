package com.ravekidd.controller.rest;

import com.ravekidd.model.auth.AuthResponse;
import com.ravekidd.model.auth.LoginRequest;
import com.ravekidd.model.auth.RegisterRequest;
import com.ravekidd.service.interfaces.IAuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller handling authentication-related operations.
 */
@RestController
public class AuthController {

    /**
     * Service for REST authentication interface
     */
    private final IAuthService service;

    /**
     * Constructor for AuthController.
     *
     * @param service An implementation of the IAuthService interface.
     */
    @Autowired
    public AuthController(IAuthService service) {
        this.service = service;
    }

    /**
     * Method maps the POST method. Handles the registration of a new user.
     *
     * @param registerRequest The request body containing registration information.
     * @return JSON response indicating the registration status.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        return service.register(registerRequest);
    }

    /**
     * Method maps the POST method. Handles user login.
     *
     * @param loginRequest The request body containing login credentials.
     * @return JSON response containing authentication details.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return service.login(loginRequest);
    }

    /**
     * Method maps the POST method. Validates the authenticity of a token.
     *
     * @param request The HttpServletRequest containing the token to be validated.
     * @return JSON response indicating the validation status.
     */
    @PostMapping("/validate")
    public ResponseEntity<String> validateToken(HttpServletRequest request) {
        return service.validateToken(request);
    }
}

