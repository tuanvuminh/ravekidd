package com.ravekidd.model.auth;

import com.ravekidd.model.User;
import lombok.Data;

@Data
public class RegisterResponse {

    private String message;

    private User registeredUser;

    public RegisterResponse() {
    }

    public RegisterResponse(String message, User registeredUser) {
        this.message = message;
        this.registeredUser = registeredUser;
    }
}
