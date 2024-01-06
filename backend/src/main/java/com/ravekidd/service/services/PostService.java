package com.ravekidd.service.services;

import com.ravekidd.model.Post;
import com.ravekidd.model.PostComment;
import com.ravekidd.model.User;
import com.ravekidd.service.helpers.ActionHelper;
import com.ravekidd.service.helpers.InputHelper;
import com.ravekidd.service.interfaces.IPostService;
import com.ravekidd.service.repositories.PostRepository;
import com.ravekidd.service.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.ravekidd.consts.Constants.*;
import static com.ravekidd.consts.Messages.*;

/**
 * Service class for managing posts and related actions.
 */
@Service
@Transactional
public class PostService implements IPostService {

    private static final Logger LOG = LogManager.getLogger(PostService.class);
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ActionHelper actionHelper;
    private final InputHelper inputHelper;

    /**
     * Constructor for PostService.
     *
     * @param postRepository The repository for managing post data.
     * @param userRepository The repository for managing user data.
     * @param actionHelper   Helper class for performing common actions.
     * @param inputHelper    Helper class for handling nullable attributes.
     */
    @Autowired
    public PostService(PostRepository postRepository,
                       UserRepository userRepository,
                       ActionHelper actionHelper,
                       InputHelper inputHelper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.actionHelper = actionHelper;
        this.inputHelper = inputHelper;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Post> getPosts(String query, String parameter, Authentication authentication) {

        LOG.debug("Received a getPosts request.");
        actionHelper.authenticate(authentication);
        String[] initializedValues = inputHelper.initInputQuery(query, parameter);

        query = initializedValues[0];
        parameter = initializedValues[1];

        List<Post> posts;

        switch (query) {
            case QUERY_POST_ID -> {
                LOG.debug("Finding post by id {}...", parameter);
                posts = postRepository.findById(Long.parseLong(parameter));

                if (posts.isEmpty()) {
                    throw new EntityNotFoundException(UNSUCCESSFUL_FIND_POST_BY_ID.get().formatted(Long.parseLong(parameter)));
                }
                return posts;
            }
            case QUERY_POST_USER -> {
                LOG.debug("Finding post by userId {}...", parameter);
                posts = postRepository.findByUserId(Long.parseLong(parameter));

                if (posts.isEmpty()) {
                    throw new EntityNotFoundException(UNSUCCESSFUL_FIND_POST_BY_USER_ID.get().formatted(Long.parseLong(parameter)));
                }
                return posts;
            }
            case QUERY_POST_DATE -> {
                String[] dates = parameter.split(" x ");

                LocalDateTime dateFrom = inputHelper.transformStringToDateTime(dates[0]);
                LocalDateTime dateTo = inputHelper.transformStringToDateTime(dates[1]);

                LOG.debug("Finding posts between dates {} and {}...", dateFrom, dateTo);
                posts = postRepository.findByDateBetween(dateFrom, dateTo);

                if (posts.isEmpty()) {
                    throw new EntityNotFoundException(UNSUCCESSFUL_FIND_POST_BETWEEN_DATES.get().formatted(
                            dateFrom.toString(),
                            dateTo.toString())
                    );
                }
                return posts;
            }
            default -> {
                LOG.debug("Retrieving all posts...");
                posts = postRepository.findAll();

                if (posts.isEmpty()) {
                    throw new EntityNotFoundException(UNSUCCESSFUL_FIND_POSTS.get().formatted(parameter));
                }
                return posts;
            }
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public Post createPost(Post post, Authentication authentication) {

        LOG.debug("Received a createPost request.");
        inputHelper.initInputPost(post);
        actionHelper.authenticate(authentication);

        String username = authentication.getName();
        User user = actionHelper.findUserByUsername(username, userRepository);
        post.setUser(user);

        LOG.debug("Post was created.");
        return postRepository.save(post);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Post updatePost(Post updatedPost, Authentication authentication) {

        LOG.debug("Received an updatePost request.");
        actionHelper.authenticate(authentication);

        Optional<Post> optionalPost = Optional.ofNullable(
                postRepository.findById(updatedPost.getId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                UNSUCCESSFUL_FIND_POST_BY_ID.get().formatted(updatedPost.getId())))
        );
        String username = authentication.getName();
        User user = actionHelper.findUserByUsername(username, userRepository);
        Post post = optionalPost.get();

        if (post.getUser().getId().equals(user.getId())) {
            inputHelper.patchPost(post, updatedPost);
            LOG.debug("Post was successfully updated by '{}'.", username);
            return postRepository.save(post);
        }
        LOG.debug("Post could not be updated.");
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Post deletePost(Long postId, Authentication authentication) {

        LOG.debug("Received a deletePost request.");
        actionHelper.authenticate(authentication);

        Optional<Post> optionalPost = Optional.ofNullable(
                postRepository.findById(postId)
                        .orElseThrow(() -> new EntityNotFoundException(
                                UNSUCCESSFUL_FIND_POST_BY_ID.get().formatted(postId)))
        );
        String username = authentication.getName();
        User user = actionHelper.findUserByUsername(username, userRepository);
        Post post = optionalPost.get();

        if (post.getUser().getId().equals(user.getId())) {
            postRepository.delete(post);
            LOG.debug("Post was deleted.");
            return post;
        }
        LOG.debug("Post could not be deleted.");
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Post likePost(Long postId, Authentication authentication) {

        LOG.debug("Received a likePost request.");
        actionHelper.authenticate(authentication);

        Optional<Post> optionalPost = Optional.ofNullable(
                postRepository.findById(postId)
                        .orElseThrow(() -> new EntityNotFoundException(
                                UNSUCCESSFUL_FIND_POST_BY_ID.get().formatted(postId)))
        );
        String username = authentication.getName();
        User user = actionHelper.findUserByUsername(username, userRepository);
        Post post = optionalPost.get();

        if (!post.getLikes().contains(user)) {
            post.addLike(user);
            LOG.debug("Post was liked by '{}'.", username);
            return postRepository.save(post);
        }
        LOG.debug("Post could not be liked.");
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Post unlikePost(Long postId, Authentication authentication) {

        LOG.debug("Received an unlikePost request.");
        actionHelper.authenticate(authentication);

        Optional<Post> optionalPost = Optional.ofNullable(
                postRepository.findById(postId)
                        .orElseThrow(() -> new EntityNotFoundException(
                                UNSUCCESSFUL_FIND_POST_BY_ID.get().formatted(postId)))
        );
        String username = authentication.getName();
        User user = actionHelper.findUserByUsername(username, userRepository);
        Post post = optionalPost.get();

        if (post.getLikes().contains(user)) {
            post.removeLike(user);
            LOG.debug("Like was removed by '{}'.", username);
            return postRepository.save(post);
        }
        LOG.debug("Like could not be removed from the post.");
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Post addComment(Long postId, PostComment inputComment, Authentication authentication) {

        LOG.debug("Received an addComment request.");
        inputHelper.initInputPostComment(inputComment);
        actionHelper.authenticate(authentication);

        Optional<Post> optionalPost = Optional.ofNullable(
                postRepository.findById(postId)
                        .orElseThrow(() -> new EntityNotFoundException(
                                UNSUCCESSFUL_FIND_POST_BY_ID.get().formatted(postId)))
        );
        String username = authentication.getName();
        User user = actionHelper.findUserByUsername(username, userRepository);
        Post post = optionalPost.get();

        PostComment comment = new PostComment(post, user, inputComment.getContent(), inputComment.getDate());
        post.addComment(comment);

        LOG.debug("Post was commented by '{}'.", username);
        return postRepository.save(post);
    }

    @Override
    public Post updateComment(Long postId, PostComment inputComment, Authentication authentication) {

        LOG.debug("Received an updateComment request.");
        inputHelper.initInputPostComment(inputComment);
        actionHelper.authenticate(authentication);

        Optional<Post> optionalPost = Optional.ofNullable(
                postRepository.findById(postId)
                        .orElseThrow(() -> new EntityNotFoundException(
                                UNSUCCESSFUL_FIND_POST_BY_ID.get().formatted(postId)))
        );
        String username = authentication.getName();
        User user = actionHelper.findUserByUsername(username, userRepository);
        Post post = optionalPost.get();

        for (PostComment comment : post.getComments()) {

            if (comment.getId().equals(inputComment.getId()) && comment.getUser().getId().equals(user.getId())) {

                comment.setContent(inputComment.getContent());
                LOG.debug("Comment was updated by '{}'.", username);
                return postRepository.save(post);
            }
        }
        LOG.debug("Comment could not be updated.");
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Post deleteComment(Long postId, Long commentId, Authentication authentication) {

        LOG.debug("Received a deleteComment request.");
        actionHelper.authenticate(authentication);

        Optional<Post> optionalPost = Optional.ofNullable(
                postRepository.findById(postId)
                        .orElseThrow(() -> new EntityNotFoundException(
                                UNSUCCESSFUL_FIND_POST_BY_ID.get().formatted(postId)))
        );
        String username = authentication.getName();
        User user = actionHelper.findUserByUsername(username, userRepository);
        Post post = optionalPost.get();

        for (PostComment comment : post.getComments()) {

            if (comment.getId().equals(commentId) && comment.getUser().getId().equals(user.getId())) {

                post.removeComment(comment);
                LOG.debug("Comment was deleted by '{}'.", username);
                return postRepository.save(post);
            }
        }
        LOG.debug("Comment could not be deleted.");
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Post likeComment(Long postId, Long commentId, Authentication authentication) {

        LOG.debug("Received a likeComment request.");
        actionHelper.authenticate(authentication);

        Optional<Post> optionalPost = Optional.ofNullable(
                postRepository.findById(postId)
                        .orElseThrow(() -> new EntityNotFoundException(
                                UNSUCCESSFUL_FIND_POST_BY_ID.get().formatted(postId)))
        );
        String username = authentication.getName();
        User user = actionHelper.findUserByUsername(username, userRepository);
        Post post = optionalPost.get();

        for (PostComment comment : post.getComments()) {

            if (comment.getId().equals(commentId) && comment.getUser().getId().equals(user.getId())) {

                comment.addLike(user);
                LOG.debug("Comment was liked by '{}'.", username);
                return postRepository.save(post);
            }
        }
        LOG.debug("Comment could not be liked.");
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Post unlikeComment(Long postId, Long commentId, Authentication authentication) {

        LOG.debug("Received an unlikeComment request.");
        actionHelper.authenticate(authentication);

        Optional<Post> optionalPost = Optional.ofNullable(
                postRepository.findById(postId)
                        .orElseThrow(() -> new EntityNotFoundException(
                                UNSUCCESSFUL_FIND_POST_BY_ID.get().formatted(postId)))
        );
        String username = authentication.getName();
        User user = actionHelper.findUserByUsername(username, userRepository);
        Post post = optionalPost.get();

        for (PostComment comment : post.getComments()) {

            if (comment.getId().equals(commentId) && comment.getUser().getId().equals(user.getId())) {

                comment.removeLike(user);
                LOG.debug("Like was removed by '{}'.", username);
                return postRepository.save(post);
            }
        }
        LOG.debug("Like could not be removed from the comment.");
        return null;
    }
}
