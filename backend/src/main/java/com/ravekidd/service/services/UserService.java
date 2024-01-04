package com.ravekidd.service.services;

import com.ravekidd.model.User;
import com.ravekidd.model.auth.AuthResponse;
import com.ravekidd.security.token.JWTProvider;
import com.ravekidd.service.helpers.ActionHelper;
import com.ravekidd.service.helpers.InputHelper;
import com.ravekidd.service.interfaces.IUserService;
import com.ravekidd.service.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ravekidd.consts.Constants.*;
import static com.ravekidd.consts.Messages.*;

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
                       InputHelper inputHelper) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.actionHelper = actionHelper;
        this.inputHelper = inputHelper;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<User> getUsers(String query, String parameter, Authentication authentication) {

        LOG.debug("Received a getUsers request.");
        actionHelper.authenticate(authentication);
        String[] initializedValues = inputHelper.initInputQuery(query, parameter);

        query = initializedValues[0];
        parameter = initializedValues[1];

        List<User> users;

        switch (query) {
            case QUERY_USER_ID -> {
                LOG.debug("Finding user by id {}...", parameter);
                users = userRepository.findUserById(Long.parseLong(parameter));
            }
            case QUERY_USER_USERNAME -> {
                LOG.debug("Finding user by username {}...", parameter);
                users = userRepository.findUserByUsername(parameter);
            }
            default -> {
                LOG.debug("Retrieving all users...");
                users = userRepository.findAll();
            }
        }
        LOG.debug("Users retrieved successfully: {}", users);
        return users;
    }

    /**
     * @inheritDoc
     */
    @Override
    public User deleteUser(Long id, Authentication authentication) {

        LOG.debug("Received a deleteUser request.");
        actionHelper.authenticate(authentication);

        User user = actionHelper.findUserById(id, userRepository);
        user.getRoles().clear();
        userRepository.delete(user);

        LOG.debug("User `{}` has been deleted.", user.getUsername());
        return user;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AuthResponse changeUsername(String newUsername, Authentication authentication) {

        LOG.debug("Received a changeUsername request.");
        actionHelper.authenticate(authentication);

        try {
            User user = actionHelper.findUserByUsername(authentication.getName(), userRepository);
            user.setUsername(newUsername);
            userRepository.save(user);

            String newToken = jwtProvider.generateToken(
                    new UsernamePasswordAuthenticationToken(newUsername, null));

            LOG.debug("User `{}` has changed their username to `{}`.", authentication.getName(), newUsername);
            return new AuthResponse(SUCCESSFUL_USERNAME_CHANGE.getMessage(), newToken);

        } catch (Exception e) {
            LOG.error("Failed to change username for user: {}", authentication.getName(), e);
            throw new RuntimeException("Failed to change username.");
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public User changeImage(String newImage, Authentication authentication) {

        LOG.debug("Received a changeImage request.");
        actionHelper.authenticate(authentication);

        User user = actionHelper.findUserByUsername(authentication.getName(), userRepository);
        user.setImage(newImage);

        LOG.debug("Image was changed.");
        return userRepository.save(user);
    }
}
