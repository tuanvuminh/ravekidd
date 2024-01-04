package com.ravekidd.consts;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.time.Duration;

public interface Constants {

    // Post queries
    String QUERY_POST_ID = "id";
    String QUERY_POST_USER = "user";
    String QUERY_POST_DATE = "date";

    // User queries
    String QUERY_USER_ID = "id";
    String QUERY_USER_USERNAME = "username";

    // Security
    Duration JWT_EXPIRATION = Duration.ofDays(7);
    Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    // Roles
    String ROLE_USER = "USER";
    String ROLE_ADMIN = "ADMIN";

    // Bundle files
    String BUNDLE_MESSAGES = "messages";
}
