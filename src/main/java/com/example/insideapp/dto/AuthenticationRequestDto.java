package com.example.insideapp.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 * DTO class for authentication (login) request.
 *
 * @author Denis Filichev
 * @version 1.0
 */

public class AuthenticationRequestDto {
    @NotNull(message = "username can't be empty")
    @Size(min = 5, message = "username length must be at least 5 characters")
    private String username;
    @NotNull(message = "password can't be empty")
    @Size(min = 5, message = "password length must be at least 5 characters")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
