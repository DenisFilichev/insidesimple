package com.example.insideapp.dto;

/**
 * DTO class for authentication (login) request.
 *
 * @author Denis Filichev
 * @version 1.0
 */

public class AuthenticationRequestDto {
    private String username;
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
