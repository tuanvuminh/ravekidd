package com.ravekidd.v1.service.interfaces;

import com.ravekidd.v1.exception.ServerException;
import com.ravekidd.v1.model.Post;
import com.ravekidd.v1.model.PostComment;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * Service interface for managing posts.
 */
public interface IPostService {

    /**
     * Retrieves a list of posts based on the given query and parameter.
     *
     * @return List of posts matching the criteria.
     * @throws ServerException If an error occurs during the operation.
     */
    List<Post> getPosts(String query, String parameter, Authentication authentication) throws ServerException;

    /**
     * Creates a new post with the given description and link.
     *
     * @param post           Content of the new post.
     * @param authentication Authentication of the user.
     * @return The created post.
     * @throws ServerException If an error occurs during the operation.
     */
    Post createPost(Post post, Authentication authentication) throws ServerException;

    /**
     * Updates an existing post with the provided details.
     *
     * @param post           Updated content of the post.
     * @param authentication Authentication object representing the current user.
     * @return The post with the updated content.
     * @throws ServerException If an error occurs during the operation.
     */
    Post updatePost(Post post, Authentication authentication) throws ServerException;

    /**
     * Deletes a post with the given ID for the authenticated user.
     *
     * @param postId         ID of the post to be deleted.
     * @param authentication Authentication of the user.
     * @return The deleted post.
     * @throws ServerException If an error occurs during the operation.
     */
    Post deletePost(Long postId, Authentication authentication) throws ServerException;

    /**
     * Likes a post.
     *
     * @param postId         ID of the post to be liked.
     * @param authentication Authentication of the user.
     * @return The liked post.
     * @throws ServerException If an error occurs during the operation.
     */
    Post likePost(Long postId, Authentication authentication) throws ServerException;

    /**
     * Unlikes a post.
     *
     * @param postId         ID of the post to unlike.
     * @param authentication Authentication of the user.
     * @return The unliked post.
     * @throws ServerException If an error occurs during the operation.
     */
    Post unlikePost(Long postId, Authentication authentication) throws ServerException;

    /**
     * Adds a comment to a post.
     *
     * @param comment        Content of the comment.
     * @param authentication Authentication of the user.
     * @return The post with added comment.
     * @throws ServerException If an error occurs during the operation.
     */
    Post addComment(Long postId, PostComment comment, Authentication authentication) throws ServerException;

    /**
     * Updates a comment on a post.
     *
     * @param comment        Updated content of the comment.
     * @param authentication Authentication of the user.
     * @return The post with the updated comment.
     * @throws ServerException If an error occurs during the operation.
     */
    Post updateComment(Long postId, PostComment comment, Authentication authentication) throws ServerException;

    /**
     * Deletes a comment from a post.
     *
     * @param commentId      ID of the comment to be deleted.
     * @param authentication Authentication of the user.
     * @return The post with deleted comment.
     * @throws ServerException If an error occurs during the operation.
     */
    Post deleteComment(Long postId, Long commentId, Authentication authentication) throws ServerException;


    /**
     * Likes a comment on a post.
     *
     * @param postId         ID of the post containing the comment.
     * @param commentId      ID of the comment to be liked.
     * @param authentication Authentication of the user.
     * @return The post with the liked comment.
     * @throws ServerException If an error occurs during the operation.
     */
    Post likeComment(Long postId, Long commentId, Authentication authentication) throws ServerException;

    /**
     * Unlikes a comment on a post.
     *
     * @param postId         ID of the post containing the comment.
     * @param commentId      ID of the comment to be unliked.
     * @param authentication Authentication of the user.
     * @return The post with the unliked comment.
     * @throws ServerException If an error occurs during the operation.
     */
    Post unlikeComment(Long postId, Long commentId, Authentication authentication) throws ServerException;
}
