package com.ravekidd.v1.model.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Username cannot be blank.")
        String username,
        @NotBlank(message = "Password cannot be blank.")
        @Size(min = 6, message = "Password must be at least 6 characters long.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$", message = "Password must contain at least one letter and one digit.")
        String password
) {
}
