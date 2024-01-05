package com.ravekidd.model.auth;

import lombok.Data;

@Data
public class AuthResponse {

    private String message;

    private String accessToken;

    public AuthResponse() {
    }

    public AuthResponse(String message, String accessToken) {
        this.message = message;
        this.accessToken = accessToken;
    }
}
