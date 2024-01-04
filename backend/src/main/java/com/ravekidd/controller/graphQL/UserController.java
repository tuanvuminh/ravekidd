package com.ravekidd.controller.graphQL;

import com.ravekidd.model.User;
import com.ravekidd.model.auth.AuthResponse;
import com.ravekidd.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.ravekidd.consts.Constants.*;

/**
 * GraphQL Controller handling user-related operations.
 */
@Controller
public class UserController {

    /**
     * Service for GraphQL user interface
     */
    private final IUserService service;

    /**
     * Constructor for UserController.
     *
     * @param service An implementation of the IUserService interface.
     */
    @Autowired
    public UserController(IUserService service) {
        this.service = service;
    }

    /**
     * GraphQL Query mapping. Retrieves a list of users based on the provided query and parameter.
     *
     * @param query            The search query.
     * @param parameter        Additional parameter for filtering.
     * @param authentication   Authentication object representing the current user.
     * @return List of users matching the criteria.
     */
    @QueryMapping
    public List<User> getUsers(@Argument String query, @Argument String parameter, Authentication authentication) {
        return service.getUsers(query, parameter, authentication);
    }

    /**
     * GraphQL Mutation mapping. Deletes a user by their ID. Requires ADMIN role.
     *
     * @param id               The ID of the user to delete.
     * @param authentication   Authentication object representing the current user.
     * @return The deleted user.
     */
    @MutationMapping
    @Secured(ROLE_ADMIN)
    public User deleteUser(@Argument Long id, Authentication authentication) {
        return service.deleteUser(id, authentication);
    }

    /**
     * GraphQL Mutation mapping. Changes the username for the authenticated user.
     *
     * @param newUsername      The new username to set.
     * @param authentication   Authentication object representing the current user.
     * @return AuthResponse indicating the success of the operation.
     */
    @MutationMapping
    public AuthResponse changeUsername(@Argument String newUsername, Authentication authentication) {
        return service.changeUsername(newUsername, authentication);
    }

    /**
     * GraphQL Mutation mapping. Changes the image for the authenticated user.
     *
     * @param newImage         The new image URL to set.
     * @param authentication   Authentication object representing the current user.
     * @return The user with the updated image.
     */
    @MutationMapping
    public User changeImage(@Argument String newImage, Authentication authentication) {
        return service.changeImage(newImage, authentication);
    }
}
