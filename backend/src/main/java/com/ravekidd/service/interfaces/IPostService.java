package com.ravekidd.service.interfaces;

import com.ravekidd.model.Post;
import com.ravekidd.model.PostComment;
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
     */
    List<Post> getPosts(String query, String parameter, Authentication authentication);

    /**
     * Creates a new post with the given description and link.
     *
     * @param post           Content of the new post.
     * @param authentication Authentication of the user.
     * @return The created post.
     */
    Post createPost(Post post, Authentication authentication);

    /**
     * Updates an existing post with the provided details.
     *
     * @param post           Updated content of the post.
     * @param authentication Authentication object representing the current user.
     * @return The post with the updated content.
     */
    Post updatePost(Post post, Authentication authentication);

    /**
     * Deletes a post with the given ID for the authenticated user.
     *
     * @param postId         ID of the post to be deleted.
     * @param authentication Authentication of the user.
     * @return The deleted post.
     */
    Post deletePost(Long postId, Authentication authentication);

    /**
     * Likes a post.
     *
     * @param postId         ID of the post to be liked.
     * @param authentication Authentication of the user.
     * @return The liked post.
     */
    Post likePost(Long postId, Authentication authentication);

    /**
     * Unlikes a post.
     *
     * @param postId         ID of the post to unlike.
     * @param authentication Authentication of the user.
     * @return The unliked post.
     */
    Post unlikePost(Long postId, Authentication authentication);

    /**
     * Adds a comment to a post.
     *
     * @param comment        Content of the comment.
     * @param authentication Authentication of the user.
     * @return The post with added comment.
     */
    Post addComment(Long postId, PostComment comment, Authentication authentication);

    /**
     * Updates a comment on a post.
     *
     * @param comment        Updated content of the comment.
     * @param authentication Authentication of the user.
     * @return The post with the updated comment.
     */
    Post updateComment(Long postId, PostComment comment, Authentication authentication);

    /**
     * Deletes a comment from a post.
     *
     * @param commentId      ID of the comment to be deleted.
     * @param authentication Authentication of the user.
     * @return The post with deleted comment.
     */
    Post deleteComment(Long postId, Long commentId, Authentication authentication);


    /**
     * Likes a comment on a post.
     *
     * @param postId         ID of the post containing the comment.
     * @param commentId      ID of the comment to be liked.
     * @param authentication Authentication of the user.
     * @return The post with the liked comment.
     */
    Post likeComment(Long postId, Long commentId, Authentication authentication);

    /**
     * Unlikes a comment on a post.
     *
     * @param postId         ID of the post containing the comment.
     * @param commentId      ID of the comment to be unliked.
     * @param authentication Authentication of the user.
     * @return The post with the unliked comment.
     */
    Post unlikeComment(Long postId, Long commentId, Authentication authentication);
}
