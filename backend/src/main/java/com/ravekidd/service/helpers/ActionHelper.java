package com.ravekidd.service.helpers;

import com.ravekidd.model.Post;
import com.ravekidd.model.User;
import com.ravekidd.service.repositories.PostRepository;
import com.ravekidd.service.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.ravekidd.consts.Messages.*;

/**
 * Helper class for performing common actions related to user authentication and retrieval.
 */
@Component
public class ActionHelper {

    private static final Logger LOG = LogManager.getLogger(ActionHelper.class);

    /**
     * Authenticates the user based on the provided authentication object.
     *
     * @param authentication The authentication object.
     * @throws SecurityException if the user is not authenticated.
     */
    public void authenticate(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            LOG.debug(UNSUCCESSFUL_AUTHENTICATION.get());
            throw new SecurityException(UNSUCCESSFUL_AUTHENTICATION.get());
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
        try {
            return userRepository.findByUsername(username).orElseThrow(
                    () -> new EntityNotFoundException(UNSUCCESSFUL_FIND_USER_BY_USERNAME.get().formatted(username))
            );
        } catch (EntityNotFoundException exception) {
            LOG.debug(exception.getMessage());
            throw exception;
        }
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
        try {
            return userRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException(UNSUCCESSFUL_FIND_USER_BY_ID.get().formatted(id))
            );
        } catch (EntityNotFoundException exception) {
            LOG.debug(exception.getMessage());
            throw exception;
        }
    }

    /**
     * Retrieves an optional post by its ID.
     *
     * @param postId         The ID of the post to retrieve.
     * @param postRepository The repository for post entities.
     * @return An optional containing the found post, or an empty optional if the post is not found.
     * @throws EntityNotFoundException if the post is not found.
     */
    public Optional<Post> findPost(Long postId, PostRepository postRepository) {
        try {
            return Optional.ofNullable(postRepository.findById(postId).orElseThrow(
                    () -> new EntityNotFoundException(UNSUCCESSFUL_FIND_POST_BY_ID.get().formatted(postId)))
            );
        } catch (EntityNotFoundException exception) {
            LOG.error(exception.getMessage());
            throw exception;
        }
    }
}
