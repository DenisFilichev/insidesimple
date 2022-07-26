package com.example.insideapp.dto;

import com.example.insideapp.dto.util.TypeMessage;
import com.example.insideapp.model.User;

import javax.validation.constraints.*;

/**
 * DTO class for validation (message) request.
 *
 * @author Denis Filichev
 * @version 1.0
 */

public class MessageRequestDto {
    @NotNull(message = "name can't be empty")
    @Size(min = 1, message = "name can't be empty")
    private String name;
    @NotNull(message = "message can't be empty")
    @Size(min = 1, message = "message can't be empty")
    private String message;
    private TypeMessage typeMessage;
    private int countMessage;
    private User user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }

    public int getCountMessage() {
        return countMessage;
    }

    public void setCountMessage(int countMessage) {
        this.countMessage = countMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
