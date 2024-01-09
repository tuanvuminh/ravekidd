package com.ravekidd.v1.service.interfaces;

import com.ravekidd.v1.exception.ServerException;
import com.ravekidd.v1.model.User;
import com.ravekidd.v1.model.auth.AuthenticationResponse;
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
    List<User> getUsers(String query, String param, Authentication authentication) throws ServerException;

    /**
     * Deletes a user with the given ID.
     *
     * @param id ID of the user to be deleted.
     * @return The deleted UserEntity object.
     */
    User deleteUser(Long id, Authentication authentication) throws ServerException;

    /**
     * Changes the username of the authenticated user.
     *
     * @param newUsername    New username to set.
     * @param authentication Authentication of the user.
     * @return Authentication response containing a new bearer token.
     */
    AuthenticationResponse changeUsername(String newUsername, Authentication authentication) throws ServerException;

    /**
     * Change the image of a user.
     *
     * @param newImage       New image URL.
     * @param authentication Authentication of the user.
     * @return The updated user.
     */
    User changeImage(String newImage, Authentication authentication) throws ServerException;
}
