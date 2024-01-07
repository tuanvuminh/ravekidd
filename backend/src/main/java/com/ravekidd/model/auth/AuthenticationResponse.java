package com.ravekidd.model.auth;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private String message;

    private String accessToken;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String message, String accessToken) {
        this.message = message;
        this.accessToken = accessToken;
    }
}
