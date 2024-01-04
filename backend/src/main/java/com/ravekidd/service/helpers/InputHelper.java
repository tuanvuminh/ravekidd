package com.ravekidd.service.helpers;

import com.ravekidd.model.Post;
import com.ravekidd.model.PostComment;
import com.ravekidd.model.Role;
import com.ravekidd.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Helper class for handling input-related operations.
 */
@Component
public class InputHelper {

    /**
     * Initializes the input parameters, setting them to empty strings if they are null.
     *
     * @param query     The query string
     * @param parameter The parameter string
     * @return An array containing the initialized query and parameter
     */
    public String[] initInputQuery(String query, String parameter) {

        if (query == null) {
            query = "";
        }

        if (parameter == null) {
            parameter = "";
        }

        return new String[]{query, parameter};
    }

    /**
     * Initializes the properties of a User object, setting them to default values if they are null.
     *
     * @param input The User object to initialize
     */
    public void initInputUser(User input) {

        if (input.getId() == null) {
            input.setId(0L);
        }

        if (input.getUsername() == null) {
            input.setUsername("");
        }

        if (input.getImage() == null) {
            input.setImage("");
        }

        if (input.getPassword() == null) {
            input.setPassword("");
        }

        if (input.getPosts() == null) {
            input.setPosts(new ArrayList<>());
        }

        if (input.getRoles() == null) {
            input.setRoles(new ArrayList<>());
        }
    }

    /**
     * Initializes the properties of a Post object, setting them to default values if they are null.
     *
     * @param input The Post object to initialize
     */
    public void initInputPost(Post input) {

        if (input.getId() == null) {
            input.setId(0L);
        }

        if (input.getUser() == null) {
            input.setUser(new User());
        }

        initInputUser(input.getUser());

        if (input.getDescription() == null) {
            input.setDescription("");
        }

        if (input.getLink() == null) {
            input.setLink("");
        }

        if (input.getDate() == null) {
            input.setDate(LocalDateTime.now());
        }

        if (input.getLikes() == null) {
            input.setLikes(new HashSet<>());
        }

        if (input.getComments() == null) {
            input.setComments(new ArrayList<>());
        }
    }

    /**
     * Initializes the properties of a PostComment object, setting them to default values if they are null.
     *
     * @param input The PostComment object to initialize
     */
    public void initInputPostComment(PostComment input) {

        if (input.getId() == null) {
            input.setId(0L);
        }

        if (input.getPost() == null) {
            input.setPost(new Post());
        }

        initInputPost(input.getPost());

        if (input.getUser() == null) {
            input.setUser(new User());
        }

        initInputUser(input.getUser());

        if (input.getContent() == null) {
            input.setContent("");
        }

        if (input.getDate() == null) {
            input.setDate(LocalDateTime.now());
        }
    }

    /**
     * Initializes the properties of a Role object, setting them to default values if they are null.
     *
     * @param input The Role object to initialize
     */
    public void initInputRoles(Role input) {

        if (input.getId() == null) {
            input.setId(0L);
        }

        if (input.getName() == null) {
            input.setName("");
        }
    }

    /**
     * Patches a Post object with values from another Post object, ignoring null values.
     *
     * @param postToUpdate The Post object to update
     * @param input         The Post object containing values to patch
     */
    public void patchPost(Post postToUpdate, Post input) {

        if (input.getDescription() != null) {
            postToUpdate.setDescription(input.getDescription());
        }

        if (input.getLink() != null) {
            postToUpdate.setLink(input.getLink());
        }
    }

    /**
     * Transforms a string in the format "dd.MM.yyyy" to a LocalDateTime object.
     *
     * @param dateString The string representation of a date in "dd.MM.yyyy" format
     * @return A LocalDateTime object representing the input date
     */
    public LocalDateTime transformStringToDateTime(String dateString) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);

        return localDate.atStartOfDay();
    }
}
