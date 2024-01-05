package com.ravekidd.service.helpers;

import com.ravekidd.model.User;
import com.ravekidd.service.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import static com.ravekidd.consts.Messages.*;

/**
 * Helper class for performing common actions related to user authentication and retrieval.
 */
@Component
public class ActionHelper {

    /**
     * Authenticates the user based on the provided authentication object.
     *
     * @param authentication The authentication object.
     * @throws SecurityException if the user is not authenticated.
     */
    public void authenticate(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new SecurityException(UNSUCCESSFUL_AUTHENTICATION.getMessage());
        }
    }

    /**
     * Finds a user by their username.
     *
     * @param username       The username of the user to find.
     * @param userRepository The repository for user entities.
     * @return The founded user.
     * @throws EntityNotFoundException if the user is not found.
     */
    public User findUserByUsername(String username, UserRepository userRepository) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(UNSUCCESSFUL_FIND_USER_BY_USERNAME.getMessage().formatted(username)));
    }

    /**
     * Finds a user by their ID.
     *
     * @param id             The ID of the user to find.
     * @param userRepository The repository for user entities.
     * @return The founded user.
     * @throws EntityNotFoundException if the user is not found.
     */
    public User findUserById(Long id, UserRepository userRepository) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(UNSUCCESSFUL_FIND_USER_BY_ID.getMessage().formatted(id)));
    }
}
