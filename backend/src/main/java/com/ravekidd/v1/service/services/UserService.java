package com.ravekidd.v1.service.services;

import com.ravekidd.v1.exception.ServerException;
import com.ravekidd.v1.model.User;
import com.ravekidd.v1.model.auth.AuthenticationResponse;
import com.ravekidd.v1.security.token.JWTProvider;
import com.ravekidd.v1.service.helpers.ActionHelper;
import com.ravekidd.v1.service.helpers.InputHelper;
import com.ravekidd.v1.service.interfaces.IUserService;
import com.ravekidd.v1.service.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static com.ravekidd.v1.consts.Constants.*;
import static com.ravekidd.v1.consts.Messages.*;

/**
 * Service class for managing users and related actions.
 */
@Service
@Transactional
public class UserService implements IUserService {

    private static final Logger LOG = LogManager.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final JWTProvider jwtProvider;
    private final ActionHelper actionHelper;
    private final InputHelper inputHelper;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for UserService.
     *
     * @param userRepository The repository for managing user data.
     * @param jwtProvider    The provider for handling JWT (JSON Web Token) operations.
     * @param actionHelper   Helper class for performing common actions.
     * @param inputHelper    Helper class for handling nullable attributes.
     */
    @Autowired
    public UserService(UserRepository userRepository,
                       JWTProvider jwtProvider,
                       ActionHelper actionHelper,
                       InputHelper inputHelper,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.actionHelper = actionHelper;
        this.inputHelper = inputHelper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<User> getUsers(String query, String parameter, Authentication authentication) throws ServerException {

        LOG.debug("Received a getUsers request.");
        actionHelper.authenticate(authentication);
        String[] initializedValues = inputHelper.initInputQuery(query, parameter);

        query = initializedValues[0];
        parameter = initializedValues[1];

        List<User> users;

        switch (query) {

            case QUERY_USER_ID -> {
                String[] ids = parameter.split(", ");
                LOG.debug("Finding users by ids: {}...", Arrays.toString(ids));
                users = actionHelper.getUsersByIds(ids, userRepository);
                return users;
            }
            case QUERY_USER_USERNAME -> {
                String[] usernames = parameter.split(", ");
                LOG.debug("Finding users by usernames: {}...", Arrays.toString(usernames));
                users = actionHelper.getUsersByUsernames(usernames, userRepository);
                return users;
            }
            default -> {
                LOG.debug("Retrieving all users...");
                users = userRepository.findAll();
                return users;
            }
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public User deleteUser(Long id, Authentication authentication) throws ServerException {

        LOG.debug("Received a deleteUser request.");
        actionHelper.authenticate(authentication);

        try {
            User user = actionHelper.findUserById(id, userRepository);
            user.getRoles().clear();
            userRepository.delete(user);

            LOG.debug("User `{}` has been deleted.", user.getUsername());
            return user;

        } catch (ServerException exception) {
            LOG.debug(exception.getLocalizedMessage());
            throw exception;
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public AuthenticationResponse changeUsername(String newUsername, Authentication authentication)
            throws ServerException {

        LOG.debug("Received an changeUsername request.");
        actionHelper.authenticate(authentication);
        AuthenticationResponse response = new AuthenticationResponse();

        try {
            User user = actionHelper.findUserByUsername(authentication.getName(), userRepository);
            user.setUsername(newUsername);
            userRepository.save(user);

            String newToken = jwtProvider.generateToken(
                    new UsernamePasswordAuthenticationToken(newUsername, null)
            );
            LOG.debug("User `{}` has changed their username to `{}`.", authentication.getName(), newUsername);

            response.setMessage(SUCCESSFUL_USERNAME_CHANGE.get());
            response.setAccessToken(newToken);
            return response;

        } catch (ServerException exception) {
            LOG.debug("Failed to change username for user: {} ,", authentication.getName(), exception);
            throw exception;
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public User changeImage(String newImage, Authentication authentication) throws ServerException {

        LOG.debug("Received a changeImage request.");
        actionHelper.authenticate(authentication);

        try {
            User user = actionHelper.findUserByUsername(authentication.getName(), userRepository);
            user.setImage(newImage);

            LOG.debug("Image was changed.");
            return userRepository.save(user);

        } catch (ServerException exception) {
            LOG.debug(exception.getLocalizedMessage());
            throw exception;
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public User changePassword(String newPassword, Authentication authentication) throws ServerException {

        LOG.debug("Received a changePassword request.");
        actionHelper.authenticate(authentication);

        try {
            User user = actionHelper.findUserByUsername(authentication.getName(), userRepository);
            user.setPassword(passwordEncoder.encode(newPassword));

            LOG.debug("User `{}` has successfully changed their password.", authentication.getName());
            return userRepository.save(user);

        } catch (ServerException exception) {
            LOG.debug("Failed to change password for user: {} ,", authentication.getName(), exception);
            throw exception;
        }
    }
}
