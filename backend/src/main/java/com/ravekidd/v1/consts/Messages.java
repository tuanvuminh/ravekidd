package com.ravekidd.v1.consts;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Enumeration of messages used throughout the application.
 * Messages are stored in a ResourceBundle for localization purposes.
 */
public enum Messages {

    // Successful messages
    SUCCESSFUL_REGISTER("User is successfully registered."),
    SUCCESSFUL_LOGIN("Login was successful."),
    SUCCESSFUL_TOKEN_VALIDATION("Token is valid."),
    SUCCESSFUL_USERNAME_CHANGE("Username was successfully changed."),

    // Unsuccessful messages
    UNSUCCESSFUL_REGISTER("Username is already taken."),
    UNSUCCESSFUL_LOGIN("Login has failed."),
    UNSUCCESSFUL_TOKEN_VALIDATION("Token is invalid or expired."),

    // Users
    UNSUCCESSFUL_FIND_USER_BY_ID("User with ID %d was not found."),
    UNSUCCESSFUL_FIND_USERS_BY_IDS("No users found for the provided IDs: "),
    UNSUCCESSFUL_FIND_USER_BY_USERNAME("User %s was not found."),
    UNSUCCESSFUL_FIND_USERS_BY_USERNAMES("No users found for the provided usernames: "),
    UNSUCCESSFUL_FIND_USERS("No users found."),
    UNSUCCESSFUL_AUTHENTICATION("User was not authenticated."),

    // Posts  No posts found for the provided user IDs:
    UNSUCCESSFUL_FIND_POST_BY_ID("Post with ID %d was not found."),
    UNSUCCESSFUL_FIND_POSTS_BY_IDS("No posts found for the provided IDs: "),
    UNSUCCESSFUL_FIND_POSTS_BY_USER_IDS("No posts found for the provided user IDs: "),
    UNSUCCESSFUL_FIND_POSTS_BETWEEN_DATES("Posts between dates %s and %s were not found."),
    UNSUCCESSFUL_FIND_POSTS("No posts found."),

    ;
    private static final ResourceBundle bundle = ResourceBundle.getBundle(Constants.BUNDLE_MESSAGES, Locale.ENGLISH);

    Messages(String description) {
    }

    /**
     * Retrieves the message associated with the enum from the resource bundle.
     *
     * @return The message associated with the key
     */
    public String get() {
        return bundle.getString(this.name());
    }
}
