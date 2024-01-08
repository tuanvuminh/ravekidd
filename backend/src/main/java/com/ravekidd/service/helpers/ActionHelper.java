package com.ravekidd.service.helpers;

import com.ravekidd.exception.ServerException;
import com.ravekidd.model.Post;
import com.ravekidd.model.User;
import com.ravekidd.service.repositories.PostRepository;
import com.ravekidd.service.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public void authenticate(Authentication authentication) throws ServerException {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ServerException(UNSUCCESSFUL_AUTHENTICATION.get());
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
    public User findUserByUsername(String username, UserRepository userRepository) throws ServerException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ServerException(UNSUCCESSFUL_FIND_USER_BY_USERNAME.get().formatted(username)));
    }

    /**
     * Finds a user by their ID.
     *
     * @param id             The ID of the user to find.
     * @param userRepository The repository for user entities.
     * @return The founded user.
     * @throws EntityNotFoundException if the user is not found.
     */
    public User findUserById(Long id, UserRepository userRepository) throws ServerException {
        return userRepository.findById(id)
                .orElseThrow(() -> new ServerException(UNSUCCESSFUL_FIND_USER_BY_ID.get().formatted(id)));
    }

    /**
     * Retrieves a post by its ID.
     *
     * @param postId         The ID of the post to retrieve.
     * @param postRepository The repository for post entities.
     * @return The found post.
     * @throws EntityNotFoundException if the post is not found.
     */
    public Post findPost(Long postId, PostRepository postRepository) throws ServerException {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ServerException(UNSUCCESSFUL_FIND_POST_BY_ID.get().formatted(postId)));
    }

    /**
     * Retrieves a list of posts by their IDs.
     *
     * @param ids            The IDs of the posts to retrieve.
     * @param postRepository The repository for post entities.
     * @return The list of found posts.
     * @throws EntityNotFoundException if no posts are found for the given ID.
     */
    public List<Post> getPostsByIds(String[] ids, PostRepository postRepository) throws ServerException {

        List<Long> postIds = Arrays.stream(ids).map(Long::parseLong).collect(Collectors.toList());
        List<Post> posts = postRepository.findAllById(postIds);

        if (posts == null || posts.isEmpty()) {
            LOG.debug("No posts found for the provided IDs: {}", postIds);
            throw new ServerException(UNSUCCESSFUL_FIND_POSTS_BY_IDS.get() + Arrays.toString(ids));
        }
        return posts;
    }

    /**
     * Retrieves a list of posts by their user ID.
     *
     * @param ids            The user IDs for which to retrieve posts.
     * @param postRepository The repository for post entities.
     * @return The list of found posts.
     * @throws EntityNotFoundException if no posts are found for the given user ID.
     */
    public List<Post> getPostsByUserIds(String[] ids, PostRepository postRepository) throws ServerException {

        List<Long> userIds = Arrays.stream(ids).map(Long::parseLong).collect(Collectors.toList());
        List<Post> posts = postRepository.findByUserIdIn(userIds);

        if (posts == null || posts.isEmpty()) {
            LOG.debug("No posts found for the provided user IDs: {}", userIds);
            throw new ServerException(UNSUCCESSFUL_FIND_POSTS_BY_USER_IDS.get() + " " + Arrays.toString(ids));
        }
        return posts;
    }

    /**
     * Retrieves a list of posts by date range.
     *
     * @param dateFrom       The start of the date range.
     * @param dateTo         The end of the date range.
     * @param postRepository The repository for post entities.
     * @return The list of found posts.
     * @throws EntityNotFoundException if no posts are found within the specified date range.
     */
    public List<Post> getPostsByDates(LocalDateTime dateFrom, LocalDateTime dateTo, PostRepository postRepository)
            throws ServerException {

        List<Post> posts = postRepository.findByDateBetween(dateFrom, dateTo);

        if (posts == null || posts.isEmpty()) {
            LOG.debug("Posts between dates %s and %s were not found.".formatted(dateFrom.toString(), dateTo.toString()));
            throw new ServerException(UNSUCCESSFUL_FIND_POSTS_BETWEEN_DATES.get().formatted(
                    dateFrom.toString(), dateTo.toString()));
        }
        return posts;
    }

    /**
     * Retrieves a list of users by their ID.
     *
     * @param ids            The IDs of the users to retrieve.
     * @param userRepository The repository for user entities.
     * @return The list of found users.
     * @throws EntityNotFoundException if no users are found for the given ID.
     */
    public List<User> getUsersByIds(String[] ids, UserRepository userRepository) throws ServerException {

        List<Long> userIds = Arrays.stream(ids).map(Long::parseLong).collect(Collectors.toList());
        List<User> users = userRepository.findAllById(userIds);

        if (users == null || users.isEmpty()) {
            LOG.debug("No users found for the provided IDs: {}", userIds);
            throw new ServerException(UNSUCCESSFUL_FIND_USERS_BY_IDS.get() + " " + Arrays.toString(ids));
        }
        return users;
    }

    /**
     * Retrieves a list of users by their username.
     *
     * @param usernames      The usernames of the users to retrieve.
     * @param userRepository The repository for user entities.
     * @return The list of found users.
     * @throws EntityNotFoundException if no users are found for the given username.
     */
    public List<User> getUsersByUsernames(String[] usernames, UserRepository userRepository) throws ServerException {

        List<User> users = userRepository.findByUsernameIn(Arrays.asList(usernames));

        if (users == null || users.isEmpty()) {
            LOG.debug("No users found for the provided usernames: {}", Arrays.toString(usernames));
            throw new ServerException(UNSUCCESSFUL_FIND_USERS_BY_USERNAMES.get() + " " + Arrays.toString(usernames));
        }
        return users;
    }
}
