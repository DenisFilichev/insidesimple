package com.example.insideapp.repository;

import com.example.insideapp.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link User}.
 *
 * @author Denis Filichev
 * @version 1.0
 */

public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByUsername(String username);

}
