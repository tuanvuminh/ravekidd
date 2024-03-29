package com.ravekidd.v1.controller.graphQL;

import com.ravekidd.v1.exception.ServerException;
import com.ravekidd.v1.model.Post;
import com.ravekidd.v1.model.PostComment;
import com.ravekidd.v1.service.interfaces.IPostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * GraphQL Controller handling post-related operations.
 */
@Controller
public class PostController {

    /**
     * Service for GraphQL post interface
     */
    private final IPostService service;

    /**
     * Constructor for PostController.
     *
     * @param service An implementation of the IPostService interface.
     */
    @Autowired
    public PostController(IPostService service) {
        this.service = service;
    }

    /**
     * GraphQL Query mapping. Finds posts based on the provided query and parameter.
     *
     * @param query          The search query.
     * @param parameter      Additional parameter for filtering.
     * @param authentication Authentication object representing the current user.
     * @return List of posts matching the criteria.
     * @throws ServerException If an error occurs during the operation.
     */
    @QueryMapping
    public List<Post> getPosts(@Pattern(regexp = "^(id|user|date)$", message = "Allowed queries: [id, user, date]")
                               @Argument @RequestParam(required = false) String query,
                               @Argument @RequestParam(required = false) String parameter,
                               Authentication authentication) throws ServerException {

        return service.getPosts(query, parameter, authentication);
    }

    /**
     * GraphQL Mutation mapping. Creates a new post.
     *
     * @param post           The new incoming instance of the Post object.
     * @param authentication Authentication object representing the current user.
     * @return The newly created post.
     * @throws ServerException If an error occurs during the operation.
     */
    @MutationMapping
    public Post createPost(@Valid @Argument Post post, Authentication authentication) throws ServerException {
        return service.createPost(post, authentication);
    }

    /**
     * GraphQL Mutation mapping. Updates an existing post.
     *
     * @param post           The updated instance of the Post object.
     * @param authentication Authentication object representing the current user.
     * @return The post with the updated content.
     * @throws ServerException If an error occurs during the operation.
     */
    @MutationMapping
    public Post updatePost(@Valid @Argument Post post, Authentication authentication) throws ServerException {
        return service.updatePost(post, authentication);
    }

    /**
     * GraphQL Mutation mapping. Deletes a post by its ID.
     *
     * @param postId         The ID of the post to be deleted.
     * @param authentication Authentication object representing the current user.
     * @return The deleted post.
     * @throws ServerException If an error occurs during the operation.
     */
    @MutationMapping
    public Post deletePost(@Min(value = 1, message = "ID must be at least 1.")
                           @NotNull(message = "ID cannot be null.") @Argument Long postId,
                           Authentication authentication) throws ServerException {

        return service.deletePost(postId, authentication);
    }

    /**
     * GraphQL Mutation mapping. Likes a post by its ID.
     *
     * @param postId         The ID of the post to be liked.
     * @param authentication Authentication object representing the current user.
     * @return The post with the updated like status.
     * @throws ServerException If an error occurs during the operation.
     */
    @MutationMapping
    public Post likePost(@Min(value = 1, message = "ID must be at least 1.")
                         @NotNull(message = "ID cannot be null.") @Argument Long postId,
                         Authentication authentication) throws ServerException {

        return service.likePost(postId, authentication);
    }

    /**
     * GraphQL Mutation mapping. Unlikes a post by its ID.
     *
     * @param postId         The ID of the post to be unliked.
     * @param authentication Authentication object representing the current user.
     * @return The post with the updated like status.
     * @throws ServerException If an error occurs during the operation.
     */
    @MutationMapping
    public Post unlikePost(@Min(value = 1, message = "ID must be at least 1.")
                           @NotNull(message = "ID cannot be null.") @Argument Long postId,
                           Authentication authentication) throws ServerException {

        return service.unlikePost(postId, authentication);
    }

    /**
     * GraphQL Mutation mapping. Adds a comment to a post.
     *
     * @param comment        The new incoming instance of the PostComment object.
     * @param authentication Authentication object representing the current user.
     * @return The post with the added comment.
     * @throws ServerException If an error occurs during the operation.
     */
    @MutationMapping
    public Post addComment(@Min(value = 1, message = "ID must be at least 1.")
                           @NotNull(message = "ID cannot be null.") @Argument Long postId,
                           @Valid @Argument PostComment comment, Authentication authentication) throws ServerException {

        return service.addComment(postId, comment, authentication);
    }

    /**
     * GraphQL Mutation mapping. Updates a comment on a post.
     *
     * @param comment        The updated instance of the PostComment object.
     * @param authentication Authentication object representing the current user.
     * @return The post with the updated comment.
     * @throws ServerException If an error occurs during the operation.
     */
    @MutationMapping
    public Post updateComment(@Min(value = 1, message = "ID must be at least 1.")
                              @NotNull(message = "ID cannot be null.") @Argument Long postId,
                              @Valid @Argument PostComment comment, Authentication authentication)
            throws ServerException {

        return service.updateComment(postId, comment, authentication);
    }

    /**
     * GraphQL Mutation mapping. Deletes a comment from a post.
     *
     * @param commentId      The ID of the comment to be deleted.
     * @param authentication Authentication object representing the current user.
     * @return The post with the deleted comment.
     * @throws ServerException If an error occurs during the operation.
     */
    @MutationMapping
    public Post deleteComment(@Min(value = 1, message = "ID must be at least 1.")
                              @NotNull(message = "ID cannot be null.") @Argument Long postId,
                              @Min(value = 1, message = "ID must be at least 1.")
                              @NotNull(message = "ID cannot be null.") @Argument Long commentId,
                              Authentication authentication) throws ServerException {

        return service.deleteComment(postId, commentId, authentication);
    }

    /**
     * GraphQL Mutation mapping. Likes a comment by its ID.
     *
     * @param postId         The ID of the post.
     * @param commentId      The ID of the comment to be liked.
     * @param authentication Authentication object representing the current user.
     * @return The post with the updated comment like status.
     * @throws ServerException If an error occurs during the operation.
     */
    @MutationMapping
    public Post likeComment(@Min(value = 1, message = "ID must be at least 1.")
                            @NotNull(message = "ID cannot be null.") @Argument Long postId,
                            @Min(value = 1, message = "ID must be at least 1.")
                            @NotNull(message = "ID cannot be null.") @Argument Long commentId,
                            Authentication authentication) throws ServerException {

        return service.likeComment(postId, commentId, authentication);
    }

    /**
     * GraphQL Mutation mapping. Unlikes a comment by its ID.
     *
     * @param postId         The ID of the post.
     * @param commentId      The ID of the comment to be unliked.
     * @param authentication Authentication object representing the current user.
     * @return The post with the updated comment like status.
     * @throws ServerException If an error occurs during the operation.
     */
    @MutationMapping
    public Post unlikeComment(@Min(value = 1, message = "ID must be at least 1.")
                              @NotNull(message = "ID cannot be null.") @Argument Long postId,
                              @Min(value = 1, message = "ID must be at least 1.")
                              @NotNull(message = "ID cannot be null.") @Argument Long commentId,
                              Authentication authentication) throws ServerException {

        return service.unlikeComment(postId, commentId, authentication);
    }
}