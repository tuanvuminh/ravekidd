package com.ravekidd.service.services;

import com.ravekidd.model.Role;
import com.ravekidd.model.User;
import com.ravekidd.model.auth.AuthResponse;
import com.ravekidd.model.auth.LoginRequest;
import com.ravekidd.model.auth.RegisterRequest;
import com.ravekidd.security.token.JWTProvider;
import com.ravekidd.service.interfaces.IAuthService;
import com.ravekidd.service.repositories.RoleRepository;
import com.ravekidd.service.repositories.UserRepository;
import com.ravekidd.consts.Constants;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static com.ravekidd.consts.Messages.*;

/**
 * Service class for handling user authentication and authorization.
 */
@Service
@Transactional
public class AuthService implements IAuthService {

    private static final Logger LOG = LogManager.getLogger(AuthService.class);
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTProvider jwtProvider;

    /**
     * Constructor for AuthService.
     *
     * @param authenticationManager The authentication manager for handling user authentication.
     * @param userRepository        The repository for managing user data.
     * @param roleRepository        The repository for managing role data.
     * @param passwordEncoder       The password encoder for securing user passwords.
     * @param jwtProvider           The provider for handling JWT (JSON Web Token) operations.
     */
    @Autowired
    public AuthService(AuthenticationManager authenticationManager,
                       UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder,
                       JWTProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    /**
     * @inheritDoc
     */
    @Override
    public ResponseEntity<String> register(RegisterRequest registerRequest) {

        LOG.debug("Received registration request for username: {}", registerRequest.username());

        if (userRepository.existsByUsername(registerRequest.username())) {
            LOG.warn("Username {} is already taken.", registerRequest.username());
            return new ResponseEntity<>(UNSUCCESSFUL_REGISTER.getMessage(), HttpStatus.BAD_REQUEST);
        }
        Role roles = roleRepository.findByName(Constants.ROLE_USER).get();

        User user = new User();
        user.setUsername(registerRequest.username());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        LOG.debug("User registered with username: {}", registerRequest.username());
        return ResponseEntity.status(HttpStatus.OK).body(SUCCESSFUL_REGISTER.getMessage());
    }

    /**
     * @inheritDoc
     */
    @Override
    public ResponseEntity<AuthResponse> login(LoginRequest loginRequest) {

        LOG.debug("Received login request for username: {}", loginRequest.username());
        AuthResponse response = new AuthResponse();

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.username(),
                    loginRequest.password())
            );
//            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtProvider.generateToken(authentication);

            LOG.debug("User: {} successfully logged in.", loginRequest.username());
            response.setMessage(SUCCESSFUL_LOGIN.getMessage());
            response.setAccessToken(token);

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (AuthenticationException e) {

            LOG.warn("Failed login attempt for username: {}", loginRequest.username(), e);
            response.setMessage(UNSUCCESSFUL_LOGIN.getMessage());
            response.setAccessToken(null);

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public ResponseEntity<AuthResponse> validateToken(HttpServletRequest request) {

        LOG.debug("Received a request from client to validate token.");

        AuthResponse response = new AuthResponse();
        String token = jwtProvider.getJWTFromRequest(request);

        if (token != null && jwtProvider.validateToken(token)) {

            LOG.debug("Token is valid.");
            response.setMessage(SUCCESSFUL_TOKEN_VALIDATION.getMessage());
            response.setAccessToken(token);

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } else {

            LOG.warn("Token is invalid or expired.");
            response.setMessage(UNSUCCESSFUL_TOKEN_VALIDATION.getMessage());
            response.setAccessToken(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
