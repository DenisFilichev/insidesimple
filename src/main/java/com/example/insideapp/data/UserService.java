package com.example.insideapp.data;

import com.example.insideapp.model.User;

import java.util.List;

/**
 * Service interface for class {@link User}.
 *
 * @author Denis Filichev
 * @version 1.0
 */

public interface UserService {

    User register(User user);

    List<User> getAll();
    User getUserByUsername(String username);
}
