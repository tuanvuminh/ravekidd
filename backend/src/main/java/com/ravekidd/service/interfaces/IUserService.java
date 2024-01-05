package com.ravekidd.service.interfaces;

import com.ravekidd.model.User;
import com.ravekidd.model.auth.AuthResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * Service interface for managing users.
 */
public interface IUserService {

    /**
     * Retrieves a list of users based on the provided query and parameter.
     *
     * @return A list of users matching the criteria.
     */
    List<User> getUsers(String query, String param, Authentication authentication);

    /**
     * Deletes a user with the given ID.
     *
     * @param id ID of the user to be deleted.
     * @return The deleted UserEntity object.
     */
    User deleteUser(Long id, Authentication authentication);

    /**
     * Changes the username of the authenticated user.
     *
     * @param newUsername    New username to set.
     * @param authentication Authentication of the user.
     * @return Authentication response containing a new bearer token.
     */
    AuthResponse changeUsername(String newUsername, Authentication authentication);

    /**
     * Change the image of a user.
     *
     * @param newImage       New image URL.
     * @param authentication Authentication of the user.
     * @return The updated user.
     */
    User changeImage(String newImage, Authentication authentication);
}
