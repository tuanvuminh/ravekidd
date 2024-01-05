package com.ravekidd.controller.graphQL;

import com.ravekidd.model.Post;
import com.ravekidd.model.PostComment;
import com.ravekidd.service.interfaces.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

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
     * @param query            The search query.
     * @param parameter        Additional parameter for filtering.
     * @param authentication   Authentication object representing the current user.
     * @return List of posts matching the criteria.
     */
    @QueryMapping
    public List<Post> getPosts(@Argument String query, @Argument String parameter, Authentication authentication) {
        return service.getPosts(query, parameter, authentication);
    }

    /**
     * GraphQL Mutation mapping. Creates a new post.
     *
     * @param post             The new incoming instance of the Post object.
     * @param authentication   Authentication object representing the current user.
     * @return The newly created post.
     */
    @MutationMapping
    public Post createPost(@Argument Post post, Authentication authentication) {
        return service.createPost(post, authentication);
    }

    /**
     * GraphQL Mutation mapping. Updates an existing post.
     *
     * @param post             The updated instance of the Post object.
     * @param authentication   Authentication object representing the current user.
     * @return The post with the updated content.
     */
    @MutationMapping
    public Post updatePost(@Argument Post post, Authentication authentication) {
        return service.updatePost(post, authentication);
    }

    /**
     * GraphQL Mutation mapping. Deletes a post by its ID.
     *
     * @param postId           The ID of the post to be deleted.
     * @param authentication   Authentication object representing the current user.
     * @return The deleted post.
     */
    @MutationMapping
    public Post deletePost(@Argument Long postId, Authentication authentication) {
        return service.deletePost(postId, authentication);
    }

    /**
     * GraphQL Mutation mapping. Likes a post by its ID.
     *
     * @param postId           The ID of the post to be liked.
     * @param authentication   Authentication object representing the current user.
     * @return The post with the updated like status.
     */
    @MutationMapping
    public Post likePost(@Argument Long postId, Authentication authentication) {
        return service.likePost(postId, authentication);
    }

    /**
     * GraphQL Mutation mapping. Unlikes a post by its ID.
     *
     * @param postId           The ID of the post to be unliked.
     * @param authentication   Authentication object representing the current user.
     * @return The post with the updated like status.
     */
    @MutationMapping
    public Post unlikePost(@Argument Long postId, Authentication authentication) {
        return service.unlikePost(postId, authentication);
    }

    /**
     * GraphQL Mutation mapping. Adds a comment to a post.
     *
     * @param comment          The new incoming instance of the PostComment object.
     * @param authentication   Authentication object representing the current user.
     * @return The post with the added comment.
     */
    @MutationMapping
    public Post addComment(@Argument Long postId, @Argument PostComment comment, Authentication authentication) {
        return service.addComment(postId, comment, authentication);
    }

    /**
     * GraphQL Mutation mapping. Updates a comment on a post.
     *
     * @param comment          The updated instance of the PostComment object.
     * @param authentication   Authentication object representing the current user.
     * @return The post with the updated comment.
     */
    @MutationMapping
    public Post updateComment(@Argument Long postId, @Argument PostComment comment, Authentication authentication) {
        return service.updateComment(postId, comment, authentication);
    }

    /**
     * GraphQL Mutation mapping. Deletes a comment from a post.
     *
     * @param comment          The PostComment object to be deleted.
     * @param authentication   Authentication object representing the current user.
     * @return The post with the deleted comment.
     */
    @MutationMapping
    public Post deleteComment(@Argument Long postId, @Argument PostComment comment, Authentication authentication) {
        return service.deleteComment(postId, comment, authentication);
    }
}