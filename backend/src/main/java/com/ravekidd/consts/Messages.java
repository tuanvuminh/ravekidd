package com.ravekidd.consts;

import java.util.Locale;
import java.util.ResourceBundle;

public enum Messages {

    // Successful messages
    SUCCESSFUL_REGISTER,
    SUCCESSFUL_LOGIN,
    SUCCESSFUL_TOKEN_VALIDATION,
    SUCCESSFUL_USERNAME_CHANGE,

    // Unsuccessful messages
    UNSUCCESSFUL_REGISTER,
    UNSUCCESSFUL_LOGIN,
    UNSUCCESSFUL_TOKEN_VALIDATION,
    UNSUCCESSFUL_FIND_USER_BY_ID,
    UNSUCCESSFUL_FIND_USER_BY_USERNAME,
    UNSUCCESSFUL_AUTHENTICATION;

    final ResourceBundle bundle = ResourceBundle.getBundle(Constants.BUNDLE_MESSAGES, Locale.ENGLISH);

    /**
     * Retrieves the message associated with the enum from the resource bundle.
     *
     * @return The message associated with the key
     */
    public String getMessage() {
        return bundle.getString(this.name());
    }
}
