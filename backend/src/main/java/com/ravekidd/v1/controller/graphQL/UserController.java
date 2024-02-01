package com.ravekidd.v1.controller.graphQL;

import com.ravekidd.v1.exception.ServerException;
import com.ravekidd.v1.model.User;
import com.ravekidd.v1.model.auth.AuthenticationResponse;
import com.ravekidd.v1.service.interfaces.IUserService;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.ravekidd.v1.consts.Constants.*;

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
     * @param query          The search query.
     * @param parameter      Additional parameter for filtering.
     * @param authentication Authentication object representing the current user.
     * @return List of users matching the criteria.
     * @throws ServerException If an error occurs during the operation.
     */
    @QueryMapping
    public List<User> getUsers(
            @Pattern(regexp = "^(id|username)$", message = "Allowed queries: [id, username]")
            @Argument @RequestParam(required = false) String query,
            @Argument @RequestParam(required = false) String parameter, Authentication authentication)
            throws ServerException {

        return service.getUsers(query, parameter, authentication);
    }

    /**
     * GraphQL Mutation mapping. Deletes a user by their ID. Requires ADMIN role.
     *
     * @param id             The ID of the user to delete.
     * @param authentication Authentication object representing the current user.
     * @return The deleted user.
     * @throws ServerException If an error occurs during the operation.
     */
    @MutationMapping
    @Secured(ROLE_ADMIN)
    public User deleteUser(@Min(value = 1, message = "ID must be at least 1.")
                           @NotNull(message = "ID cannot be null.")
                           @Argument Long id, Authentication authentication) throws ServerException {

        return service.deleteUser(id, authentication);
    }

    /**
     * GraphQL Mutation mapping. Changes the username for the authenticated user.
     *
     * @param newUsername    The new username to set.
     * @param authentication Authentication object representing the current user.
     * @return AuthResponse indicating the success of the operation.
     * @throws ServerException If an error occurs during the operation.
     */
    @MutationMapping
    public AuthenticationResponse changeUsername(@Argument @NotBlank(message = "Username cannot be blank.")
                                                 String newUsername, Authentication authentication)
            throws ServerException {

        return service.changeUsername(newUsername, authentication);
    }

    /**
     * GraphQL Mutation mapping. Changes the image for the authenticated user.
     *
     * @param newImage       The new image URL to set.
     * @param authentication Authentication object representing the current user.
     * @return The user with the updated image.
     * @throws ServerException If an error occurs during the operation.
     */
    @MutationMapping
    public User changeImage(@Pattern(regexp = ".*\\.(jpg|jpeg|png)$", message = "Allowed formats: [jpg, jpeg, png]")
                            @Argument String newImage, Authentication authentication) throws ServerException {

        return service.changeImage(newImage, authentication);
    }

    /**
     * GraphQL Mutation mapping. Changes the password of the authenticated user.
     *
     * @param newPassword    The new password to be set.
     * @param authentication The authentication object representing the current user.
     * @return The user with the updated password.
     * @throws ServerException If an error occurs during the operation.
     */
    @MutationMapping
    public User changePassword(@NotBlank(message = "Password cannot be blank.")
                               @Size(min = 6, message = "Password must be at least 6 characters long.")
                               @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$",
                                       message = "Password must contain at least one letter and one digit.")
                               @Argument String newPassword, Authentication authentication) throws ServerException {

        return service.changePassword(newPassword, authentication);
    }
}
