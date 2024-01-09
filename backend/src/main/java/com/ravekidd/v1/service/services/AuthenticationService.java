package com.ravekidd.v1.service.services;

import com.ravekidd.v1.model.User;
import com.ravekidd.v1.model.auth.AuthenticationResponse;
import com.ravekidd.v1.model.auth.AuthenticationRequest;
import com.ravekidd.v1.model.auth.RegisterRequest;
import com.ravekidd.v1.model.auth.RegisterResponse;
import com.ravekidd.v1.security.token.JWTProvider;
import com.ravekidd.v1.service.helpers.InputHelper;
import com.ravekidd.v1.service.interfaces.IAuthenticationService;
import com.ravekidd.v1.service.repositories.RoleRepository;
import com.ravekidd.v1.service.repositories.UserRepository;
import com.ravekidd.v1.consts.Constants;
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

import static com.ravekidd.v1.consts.Messages.*;

/**
 * Service class for handling user authentication and authorization.
 */
@Service
@Transactional
public class AuthenticationService implements IAuthenticationService {

    private static final Logger LOG = LogManager.getLogger(AuthenticationService.class);
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTProvider jwtProvider;
    private final InputHelper inputHelper;

    /**
     * Constructor for AuthenticationService.
     *
     * @param authenticationManager The authentication manager for handling user authentication.
     * @param userRepository        The repository for managing user data.
     * @param roleRepository        The repository for managing role data.
     * @param passwordEncoder       The password encoder for securing user passwords.
     * @param jwtProvider           The provider for handling JWT (JSON Web Token) operations.
     */
    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager,
                                 UserRepository userRepository,
                                 RoleRepository roleRepository,
                                 PasswordEncoder passwordEncoder,
                                 JWTProvider jwtProvider,
                                 InputHelper inputHelper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.inputHelper = inputHelper;
    }

    /**
     * @inheritDoc
     */
    @Override
    public ResponseEntity<RegisterResponse> register(RegisterRequest request) {

        LOG.debug("Received a registration request for username: {}", request.username());
        RegisterResponse response = new RegisterResponse();

        if (userRepository.existsByUsername(request.username())) {

            LOG.debug("Username {} is already taken.", request.username());
            response.setMessage(UNSUCCESSFUL_REGISTER.get());
            response.setRegisteredUser(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));

        if (request.username().equals("ravekidd-admin")) {
            user.setRoles(Collections.singletonList(roleRepository.findByName(Constants.ROLE_ADMIN)));
        } else {
            user.setRoles(Collections.singletonList(roleRepository.findByName(Constants.ROLE_USER)));
        }
        inputHelper.initInputUser(user);
        userRepository.save(user);

        response.setMessage(SUCCESSFUL_REGISTER.get());
        response.setRegisteredUser(user);

        LOG.debug("User registered with username: {}", request.username());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * @inheritDoc
     */
    @Override
    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request) {

        LOG.debug("Received a login request for username: {}", request.username());
        AuthenticationResponse response = new AuthenticationResponse();

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.username(),
                    request.password())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtProvider.generateToken(authentication);

            LOG.debug("User: {} successfully logged in.", request.username());
            response.setMessage(SUCCESSFUL_LOGIN.get());
            response.setAccessToken(token);

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (AuthenticationException e) {

            LOG.debug("Failed login attempt for username: {} ,", request.username(), e);
            response.setMessage(UNSUCCESSFUL_LOGIN.get());
            response.setAccessToken(null);

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public ResponseEntity<AuthenticationResponse> validateToken(HttpServletRequest request) {

        LOG.debug("Received a request from client to validate token.");

        AuthenticationResponse response = new AuthenticationResponse();
        String token = jwtProvider.getJWTFromRequest(request);

        if (token != null && jwtProvider.validateToken(token)) {

            LOG.debug("Token is valid.");
            response.setMessage(SUCCESSFUL_TOKEN_VALIDATION.get());
            response.setAccessToken(token);

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } else {

            LOG.debug("Token is invalid or expired.");
            response.setMessage(UNSUCCESSFUL_TOKEN_VALIDATION.get());
            response.setAccessToken(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
